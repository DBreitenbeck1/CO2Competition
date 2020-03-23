package co.grandcircus.CO2Competition.Controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.grandcircus.CO2Competition.ApiService;
import co.grandcircus.CO2Competition.COCalculator;
import co.grandcircus.CO2Competition.CalculationService;
import co.grandcircus.CO2Competition.Entities.Distance;
import co.grandcircus.CO2Competition.Entities.SearchResult;
import co.grandcircus.CO2Competition.Objects.Carpool;
import co.grandcircus.CO2Competition.Objects.Company;
import co.grandcircus.CO2Competition.Objects.Employee;
import co.grandcircus.CO2Competition.Repos.CarpoolRepo;
import co.grandcircus.CO2Competition.Repos.CompanyRepo;
import co.grandcircus.CO2Competition.Repos.EmployeeRepo;

@Controller
public class NeedsAttention {

	@Autowired
	ApiService apiServe;
	@Autowired
	HttpSession sesh;
	@Autowired
	EmployeeRepo emRepo;
	@Autowired
	CarpoolRepo carRepo;
	@Autowired
	COCalculator coCal;
	@Autowired
	CompanyRepo coRepo;


//
//	
//	// The item linking to this controller seems to be commented out. Will it be added later? Does this need to be cleaned up? --Sam
//	@RequestMapping("/tripdetails/{id}")
//	public ModelAndView showDetails(@PathVariable("id") Employee employee, @RequestParam String street,
//			@RequestParam String city, @RequestParam String zip, @RequestParam(value = "co") String des,
//			@RequestParam(value = "em") String username, @RequestParam String street1, @RequestParam String city1,
//			@RequestParam String zip1, RedirectAttributes redir) {
//		ModelAndView mav = new ModelAndView("details");
//		String address1 = emRepo.findByUsernameIgnoreCase(username).getStreetAddress()
//				+ emRepo.findByUsernameIgnoreCase(username).getCity()
//				+ emRepo.findByUsernameIgnoreCase(username).getZipCode();
//		String address2 = street1 + city1 + zip1;
//		address2 = emRepo.findByUsernameIgnoreCase(username).getCompany().getStreetAddress()
//				+ emRepo.findByUsernameIgnoreCase(username).getCompany().getCity()
//				+ emRepo.findByUsernameIgnoreCase(username).getCompany().getZipCode();
//		SearchResult result = apiServe.getResult(address1, address2);
//		Distance distance = apiServe.getDistance(result);
//		if (distance != null) {
//			mav.addObject("street", emRepo.findByUsernameIgnoreCase(username).getStreetAddress());
//			mav.addObject("city", emRepo.findByUsernameIgnoreCase(username).getCity());
//			mav.addObject("zip", emRepo.findByUsernameIgnoreCase(username).getZipCode());
//			mav.addObject("coName", emRepo.findByUsernameIgnoreCase(username).getCompany().getName());
//			mav.addObject("street1", emRepo.findByUsernameIgnoreCase(username).getCompany().getStreetAddress());
//			mav.addObject("city1", emRepo.findByUsernameIgnoreCase(username).getCompany().getCity());
//			mav.addObject("zip1", emRepo.findByUsernameIgnoreCase(username).getCompany().getZipCode());
//			mav.addObject("distance", distance);
//			mav.addObject("em", coCal.smallCar(distance.getValue()));
//
//			employee.setCity(city);
//			employee.setStreetAddress(street);
//			employee.setZipCode(zip);
//			employee.getCompany().getStreetAddress();
//			employee.getCompany().getStreetAddress();
//
//			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//			Date dateobj = new Date();
//			Carpool carpool = new Carpool();
//
////		    NEEDS TO BE CALCULATED AS INTEGER
////			carpool.setCo2(coCal.smallCar(distance.getValue()));
//
//			carpool.setDate(df.format(dateobj));
//			// add userId
//			// List<Employee> em = new ArrayList<>();
//			// em.add(employee);
//			carRepo.save(carpool);
//			// Employee em = new Employee();
//			// em.getEmployeeId();
//			List<Carpool> c = new ArrayList<>();
//			c.add(carpool);
//			// carpool.setEmployees(em);
//			employee.setCarpool(c);
//			carpool.getCarpoolId();
//			employee.addItem(carpool);
//
//			carRepo.save(carpool);
//			List<Employee> em = new ArrayList<>();
//			employee.addCarpool(carpool);
//			carpool.setEmployees(em);
//			carRepo.save(carpool);
//			emRepo.save(employee);
//		} else {
//			mav.addObject("invalid", "No such address");
//		}
//		return mav;
//
//	}
//
//	// search for the carpool to ride back home
//	// How does this differ from the other carpool log page? --Sam
//	@RequestMapping("/find-carpool-back/{id}")
//	public ModelAndView carpoolBack(@PathVariable("id") Employee employee, @RequestParam(required = false) String date,
//			@RequestParam(required = false) String time) {
//
//		// finding all the employees that work for this company
//		Company company = employee.getCompany();
//		List<Employee> empl = company.getEmployees();
//		empl.remove(employee);
//		List<Distance> distanceToYourHouse = new ArrayList<>();
//		List<Distance> distanceToTheirOwn = new ArrayList<>();
//
//		// calculating distance between work to rider's house and work to driver's house
//		for (Employee e : empl) {
//			SearchResult result1 = apiServe.getResult(employee.getAddress(), company.getAddress());
//			distanceToYourHouse.add(apiServe.getDistance(result1));
//			SearchResult result2 = apiServe.getResult(e.getAddress(), company.getAddress());
//			distanceToTheirOwn.add(apiServe.getDistance(result2));
//		}
//		ModelAndView mav = new ModelAndView("search-carpoolBack");
//		mav.addObject("carpools", company.getCarpool());
//		mav.addObject("employees", empl);
//		mav.addObject("company", company);
//		mav.addObject("distanceFY", distanceToYourHouse);
//		mav.addObject("distanceFT", distanceToTheirOwn);
//		mav.addObject("date", date);
//		mav.addObject("time", time);
//		mav.addObject("id", employee.getEmployeeId());
//		return mav;
//	}
//
//	// Again, how does this differ from the Carpool To Work controller?
//	// submit carpool to ride back home
//	// finding the name of the driver based on their username and sending
//	// information to jsp to show the confirmation page
//	@RequestMapping("/submit-carpool-back/{id}")
//	public ModelAndView carpoolBackS(@RequestParam(value = "carpool") String username,
//			@RequestParam(value = "date", required = false) String date,
//			@RequestParam(value = "time", required = false) String time) {
//
//		List<Employee> poolers = new ArrayList<>();
//		Employee passenger1 = (Employee) sesh.getAttribute("employee");
//		Employee passenger2 = emRepo.findByUsernameIgnoreCase(username);
//
//		poolers.add(passenger1);
//		poolers.add(passenger2);
//		Company company = passenger1.getCompany();
//
//		SearchResult result1 = apiServe.getResult(company.getAddress(), passenger1.getAddress());
//		SearchResult result2 = apiServe.getResult(company.getAddress(), passenger2.getAddress());
//		Distance d1 = apiServe.getDistance(result1);
//		Distance d2 = apiServe.getDistance(result2);
//		Distance distance;
//		if (d1.getValue() > d2.getValue()) {
//			distance = d2;
//		} else {
//			distance = d1;
//		}
//
//		Long d = distance.getValue();
//		double miles = d / 1609.344;
//		CalculationService cs = new CalculationService();
//		double saved = cs.calculateCO2(miles, "car");
//
//		double score = saved * 10;
//
//		Carpool carpool = new Carpool();
//		carpool.setCompany(company);
//		date = date + " " + time;
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		Date dateobj = new Date();
//		try {
//			dateobj = df.parse(date);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		carpool.setDate(df.format(dateobj));
//
////		NEEDS TO BE CALCULATED AS INTEGER
////		carpool.setCo2(score);
//
//		carRepo.save(carpool);
//		carpool.setEmployees(poolers);
//		for (Employee pool : poolers) {
//			pool.addCarpool(carpool);
//			emRepo.save(pool);
//		}
//		carRepo.save(carpool);
//
//		ModelAndView mav = new ModelAndView("confirmationBack");
//		mav.addObject("name", passenger2.getName());
//		mav.addObject("destination", passenger1.getAddress());
//		mav.addObject("date", date);
//		mav.addObject("time", time);
//		mav.addObject("saved", saved);
//		mav.addObject("score", score);
//		return mav;
//
//	}
//
//	// What is this controller for? I can't find anything that links to it --Sam
//	@RequestMapping("/list-of-routes")
//	public ModelAndView showList() {
//		return new ModelAndView("list-of-routes", "company", coRepo.findAll());
//	}
//
//	// This appears to be an unused controller detailing information for a summary
//	// page. Can it be deleted? --Sam
//	@RequestMapping("/carpoolsummary/{id}")
//	public ModelAndView showSummary(@PathVariable("id") Carpool carpool) {
//		ModelAndView mav = new ModelAndView("summary");
//		mav.addObject("cp", carpool);
//		mav.addObject("company", carpool.getCompany().getName());
//		return mav;
//	}
//
//	// This appears to be a controller showing employees of a given company, a page
//	// linked to
//	// from a JSP with no link pointing to it?? Is this controller still relevant?
//	// --Sam
//	@RequestMapping("/details-list/{id}")
//	public ModelAndView showDetails(@PathVariable("id") Company company) {
//		ModelAndView mav = new ModelAndView("company-details");
//		mav.addObject("info", company.getEmployees());
//		mav.addObject("cName", company.getName());
//		return mav;
//	}
//
//	//////////////////////////////////////////////////////////////////////////
//	// ALL CONTROLLERS BELOW THIS LINE
//	// Possibly outdated, can they be deleted? --Sam
//
//	@RequestMapping("/logtrip")
//	public ModelAndView logTripForm() {
//		return new ModelAndView("tripform");
//	}
//
//	@RequestMapping("/tripdetails")
//	public ModelAndView showDetails(@RequestParam String street, @RequestParam String city, @RequestParam String zip,
//			@RequestParam String street1, @RequestParam String city1, @RequestParam String zip1,
//			RedirectAttributes redir) {
//		ModelAndView mav = new ModelAndView("details");
//		String startAddress = street + city + zip;
//		String destAddress = street1 + city1 + zip1;
//		SearchResult result = apiServe.getResult(startAddress, destAddress);
//		Distance distance = apiServe.getDistance(result);
//		if (distance != null) {
//			mav.addObject("street", street);
//			mav.addObject("city", city);
//			mav.addObject("zip", zip);
//			mav.addObject("street1", street1);
//			mav.addObject("city1", city1);
//			mav.addObject("zip1", zip1);
//			mav.addObject("distance", distance);
//			mav.addObject("em", coCal.smallCar(distance.getValue()));
//		} else {
//			mav.addObject("invalid", "No such address");
//		}
//		return mav;
//	}
//
//	@RequestMapping("/oldIndex")
//	public ModelAndView showIndex(RedirectAttributes redir) {
//		// Create ModelAndView
//		ModelAndView mav = new ModelAndView("index");
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
//		return mav;
//	}
//
//	// Shows start page for creating a new carpool
//	@RequestMapping("/carpool")
//	public ModelAndView startCarpool() {
//		ModelAndView mav = new ModelAndView("carpool");
//		return mav;
//	}
//
}
