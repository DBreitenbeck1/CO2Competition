package co.grandcircus.CO2Competition.Controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.grandcircus.CO2Competition.ApiService;
import co.grandcircus.CO2Competition.COCalculator;
import co.grandcircus.CO2Competition.Entities.Distance;
import co.grandcircus.CO2Competition.Entities.SearchResult;
import co.grandcircus.CO2Competition.Objects.Carpool;
import co.grandcircus.CO2Competition.Objects.Company;
import co.grandcircus.CO2Competition.Objects.Employee;
import co.grandcircus.CO2Competition.Repos.CarpoolRepo;
import co.grandcircus.CO2Competition.Repos.CompanyRepo;
import co.grandcircus.CO2Competition.Repos.EmployeeRepo;

@Controller
public class IndexController {

	@Autowired
	HttpSession sesh;

	@Value("${api_key}")
	String apiKey;

	@Autowired
	private ApiService apiServe;

	@Autowired
	private COCalculator coCal;

	@Autowired
	private EmployeeRepo emRepo;
	
	@Autowired
	private CompanyRepo coRepo;
	
	@Autowired
	private CarpoolRepo carRepo;
	
//
//		// Declare Variables
//		String address1 = "NoviMI";
//		String address2 = "DetroitMI";
//		String address3 = "ChicagoIL";
//		String start;
//		String midway;
//		String destination;
//		Distance distance;
//		
//		// Get Search Results
//		SearchResult result = apiServe.getResult(address1, address2, address3);
//
//		 Will catch thrown exception if there were no results found and redirect
//		try {
//			start = apiServe.getStart(result, 0);
//			midway = apiServe.getStart(result, 1);
//			destination = apiServe.getDest(result, 1);
//			distance = apiServe.getDistance(result);
//		} catch (IllegalArgumentException IAE) {
//			redir.addFlashAttribute("message", IAE.getMessage());
//			return new ModelAndView("redirect:/logtrip");
//		}
//		double CO2Savings = coCal.calculateSavings(address1, address2);
//		double CO2Savings = coCal.smallCar(5.7);
//		
//		 Add Objects to ModelAndView
//		mav.addObject("co2savings", CO2Savings);
//		mav.addObject("start", start);
//		mav.addObject("midway", midway);
//		mav.addObject("destination", destination);
//		mav.addObject("distance", distance);

	// Add Objects to ModelAndView
//		String url = ( "https://maps.googleapis.com/maps/api/js?origin="
//				+ address1 + "&destination=" + address2 + "&key=" + apiKey);
//		
//		
//		mav.addObject("co2savings", CO2Savings);
//		mav.addObject("start", start);
//		mav.addObject("midway", midway);
//		mav.addObject("destination", destination);
//		mav.addObject("distance", distance);
//		mav.addObject("url", url);

	@RequestMapping("/logtrip")
	public ModelAndView logTripForm() {
		return new ModelAndView("tripform");
	}

	@RequestMapping("/tripdetails")
	public ModelAndView showDetails(@RequestParam String street, @RequestParam String city, @RequestParam String zip,
			@RequestParam String street1, @RequestParam String city1, @RequestParam String zip1,
			RedirectAttributes redir) {
		ModelAndView mav = new ModelAndView("details");
		String startAddress = street + city + zip;
		String destAddress = street1 + city1 + zip1;
		SearchResult result = apiServe.getResult(startAddress, destAddress);
		Distance distance = apiServe.getDistance(result);
		if (distance != null) {
			mav.addObject("street", street);
			mav.addObject("city", city);
			mav.addObject("zip", zip);
			mav.addObject("street1", street1);
			mav.addObject("city1", city1);
			mav.addObject("zip1", zip1);
			mav.addObject("distance", distance);
			mav.addObject("em", coCal.smallCar(distance.getValue()));
		} else {
			mav.addObject("invalid", "No such address");
		}
		return mav;

	}

	@RequestMapping("/")
	public ModelAndView showIndex() {

		List<Company> company= coRepo.findAll();

		HashMap<String, Integer> co = new HashMap <String, Integer>();
		
		ValueComparator value = new ValueComparator(co);
		
		for(Company c : company) {
			Integer total=0;
			
			for (Employee e : c.getEmployees()){
				total += e.getScore();
			}
			co.put(c.getName(), total);
		}
		TreeMap <String,Integer> cc = new TreeMap<>(value);
		cc.putAll(co);
		ModelAndView mav = new ModelAndView ("index/index");

		mav.addObject("cc",cc);
		return mav;
	}

	@RequestMapping("/dashboard")
	public ModelAndView showDesk() {
		Employee employee = new Employee();
		try {
		Employee user = (Employee) sesh.getAttribute("employee");
		employee = emRepo.findByUsernameIgnoreCase(user.getUsername());
		} catch (Exception e) {
			System.out.println("Please Login");
			return new ModelAndView("redirect:/login/login");
		}
		
		
		//defines current date
				LocalDate now = LocalDate.now();
				DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String today = now.format(dateForm) + " 00:00";		
				
				//retrieves the user's carpools and orders by date
				List<Carpool> carpools = carRepo.findByEmployeesContainingOrderByDateDesc(employee);
				List<Integer> userScore = new ArrayList<>();
				
				
				
				for(Carpool cp: carpools) {
					if (cp.getCo2()!=null) {
					int s = cp.getCo2()/cp.getEmployees().size();
					userScore.add(s);
					}
					
					
				}
		
		ModelAndView mav = new ModelAndView("index/dashboard");
		mav.addObject("noCP", "You Don't Have Any Carpools Yet!");
		mav.addObject("score", employee.getScore());
		mav.addObject("userScore", userScore);
		mav.addObject("carpools", carpools);
		mav.addObject("today",today);
		return mav;

	}
	
	@RequestMapping("/about")
	public ModelAndView showAbout() {
		return new ModelAndView ("index/about");
		
		
	}
	
//	@RequestMapping("/top-companies")
//	public ModelAndView compareCompanies() {
//		
//		List<Company> company= coRepo.findAll();
//		List<Employee> em = null;
//		for (int i = 0; i<company.size(); i++) {
//			em = emRepo.findAllEmployeeByCompanyId(company.get(i).getCompanyId());
//			System.out.println("em"+em);
//		}
//		ModelAndView mav = new ModelAndView ("top-companies");
//		mav.addObject("ems",em);
//		return mav;
//	}
}
