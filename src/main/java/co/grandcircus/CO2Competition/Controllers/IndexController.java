package co.grandcircus.CO2Competition.Controllers;

import java.util.ArrayList;
import java.util.List;

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
import co.grandcircus.CO2Competition.Objects.Company;
import co.grandcircus.CO2Competition.Objects.Employee;
import co.grandcircus.CO2Competition.Objects.Score;
import co.grandcircus.CO2Competition.Repos.CompanyRepo;
import co.grandcircus.CO2Competition.Repos.EmployeeRepo;

@Controller
public class IndexController {

	@Autowired
	HttpSession httpSesh;

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
		List<Score> em = new ArrayList<>();
		List<Score> co = new ArrayList<>();
		
		for(int i =0; i<company.size();i++) {
			co =emRepo.findScoreByTotalScore(company.get(i).getCompanyId());
			
			for (int j=0; j<co.size(); j++){
            em.add(co.get(j));
			}
		}

		ModelAndView mav = new ModelAndView ("index/index");
		mav.addObject("com",company);
		mav.addObject("em",em);
		return mav;
	}

	@RequestMapping("/dashboard")
	public ModelAndView showDesk() {
		return new ModelAndView("index/dashboard");

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
