package co.grandcircus.CO2Competition.Controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.grandcircus.CO2Competition.ApiService;
import co.grandcircus.CO2Competition.COCalculator;
import co.grandcircus.CO2Competition.CalculationService;
import co.grandcircus.CO2Competition.RouteCalculator;
import co.grandcircus.CO2Competition.Entities.Distance;
import co.grandcircus.CO2Competition.Entities.SearchResult;
import co.grandcircus.CO2Competition.Objects.Carpool;
import co.grandcircus.CO2Competition.Objects.Company;
import co.grandcircus.CO2Competition.Objects.Employee;
import co.grandcircus.CO2Competition.Repos.CarpoolRepo;
import co.grandcircus.CO2Competition.Repos.CompanyRepo;
import co.grandcircus.CO2Competition.Repos.EmployeeRepo;

@Controller
public class LoginController {
	@Autowired
	private HttpSession sesh;
	@Autowired
	private CarpoolRepo carRepo;
	@Autowired
	private EmployeeRepo emRepo;
	@Autowired 
	private CompanyRepo coRepo;
	@Autowired
	private ApiService apiServe;
	@Autowired
	private COCalculator coCal;
	@Autowired
	private CalculationService calcServe;
	@Autowired
	private RouteCalculator rCalc;
	
	@RequestMapping("/login")
	public ModelAndView showLogin() {
		return new ModelAndView("login");
	}
	
	@PostMapping("/login")
	public ModelAndView checkLogin(@RequestParam ("username") String username, @RequestParam("password") String password,
			RedirectAttributes red) {

		Employee employee = emRepo.findByUsernameIgnoreCase(username);

		if(employee == null || !password.equals(employee.getPassword())) {
			red.addFlashAttribute("message","Incorrect username or password, please try again!");
			red.addFlashAttribute("messageType","danger");
			return new ModelAndView("redirect:/login");
		}
		
		sesh.setAttribute("employee", employee);
		return new ModelAndView ("redirect:/employee");
	}
	
	@RequestMapping("/logout")
	public ModelAndView showLogout(RedirectAttributes red) {
		sesh.invalidate();
		red.addFlashAttribute("message","Successfully logged out.");
		red.addFlashAttribute("messageType","success");
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping("/employee")
	public ModelAndView showDesk() {
		return new ModelAndView ("employee-page");
	}
	
	@RequestMapping("/carpool")
	public ModelAndView showCarpool() {
		ModelAndView mav = new ModelAndView("carpool");
		return mav;
	}
	
	@RequestMapping("/tripdetailsFIXME")
	public ModelAndView showDetails(
			@RequestParam String street,
			@RequestParam String city,
			@RequestParam String zip,
			@RequestParam (value="co") String des, 
			@RequestParam (value="em") String username,
			@RequestParam String street1,
			@RequestParam String city1,
			@RequestParam String zip1,
			RedirectAttributes redir
			) {
		ModelAndView mav = new ModelAndView("details");
		String address1 = emRepo.findByUsernameIgnoreCase(username).getStreetAddress()+emRepo.findByUsernameIgnoreCase(username).getCity()+emRepo.findByUsernameIgnoreCase(username).getZipCode();
		String address2 = street1+city1+zip1;
		 address2 = emRepo.findByUsernameIgnoreCase(username).getCompany().getStreetAddress()+emRepo.findByUsernameIgnoreCase(username).getCompany().getCity()+ emRepo.findByUsernameIgnoreCase(username).getCompany().getZipCode();
		SearchResult result = apiServe.getResult(address1, address2);
		 Distance distance = apiServe.getDistance(result);
		if (distance!=null) {
	
		mav.addObject("street", emRepo.findByUsernameIgnoreCase(username).getStreetAddress());
		mav.addObject("city", emRepo.findByUsernameIgnoreCase(username).getCity());
		mav.addObject("zip", emRepo.findByUsernameIgnoreCase(username).getZipCode());
		mav.addObject("coName",emRepo.findByUsernameIgnoreCase(username).getCompany().getName());
		mav.addObject("street1",emRepo.findByUsernameIgnoreCase(username).getCompany().getStreetAddress());
		mav.addObject("city1", emRepo.findByUsernameIgnoreCase(username).getCompany().getCity());
		mav.addObject("zip1", emRepo.findByUsernameIgnoreCase(username).getCompany().getZipCode());
		mav.addObject("distance", distance);
		mav.addObject("em", coCal.smallCar(distance.getValue() ));
		
		Employee employee = (Employee) sesh.getAttribute("employee");
		employee.setCity(city);
		employee.setStreetAddress(street);
		employee.setZipCode(zip);
		employee.getCompany().getStreetAddress();	
		employee.getCompany().getStreetAddress();
		
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	    Date dateobj = new Date();
	    Carpool carpool = new Carpool();
		carpool.setCo2(coCal.smallCar(distance.getValue()));
		carpool.setDate(df.format(dateobj));
		//add userId
//		List<Employee> em = new ArrayList<>();
//		em.add(employee);
		carRepo.save(carpool);
//		Employee em = new Employee();
//		em.getEmployeeId();
		List<Carpool> c = new ArrayList<>();
		c.add(carpool);
//		carpool.setEmployees(em);
		employee.setCarpool(c);
		carpool.getCarpoolId();
		employee.addItem(carpool);
		
		carRepo.save(carpool);
		List<Employee> em = new ArrayList<>();
		employee.addCarpool(carpool);
		carpool.setEmployees(em);
		carRepo.save(carpool);
		emRepo.save(employee);
		} else {
			mav.addObject("invalid", "No such address");
		}
		return mav;
		
	}
	
	@RequestMapping("/carpoolsummary/{id}")
	public ModelAndView showSummary(@PathVariable("id") Carpool carpool) {
		ModelAndView mav = new ModelAndView("summary");
		mav.addObject("cp", carpool);
		mav.addObject("company", carpool.getCompany().getName());
		return mav;
	}
	
	//submit the carpool that the user already chose
	@RequestMapping("/submit-carpool")
	public ModelAndView submitCarpool(@RequestParam(value="carpool")String username,
			@RequestParam(required=false) String date,
			@RequestParam(required=false) String time,
			@RequestParam(value="id", required=false) Long id) {
		List<Employee> poolers = new ArrayList<>();  
		Employee passenger1 = emRepo.findById(id).orElse(null);
		Employee passenger2 = emRepo.findByUsernameIgnoreCase(username);
		
		poolers.add(passenger1);
		poolers.add(passenger2);
		Company company = passenger1.getCompany();

		SearchResult result1 =apiServe.getResult(passenger1.getAddress(), company.getAddress());
		SearchResult result2 =apiServe.getResult(passenger2.getAddress(), company.getAddress());
		Distance d1= apiServe.getDistance(result1);
		Distance d2= apiServe.getDistance(result2);
		Distance distance;
		if(d1.getValue()>d2.getValue()) {
			distance = d2;
		}else {
			distance = d1;
		}
		
		Long d = distance.getValue();
		double miles = d/1609.344;
		CalculationService cs = new CalculationService();
		double saved=cs.calculateCO2(miles, "car");
		
		double score = saved*10;
		
		Carpool carpool = new Carpool();
		carpool.setCompany(company);
		date=date+" "+time;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    Date dateobj = new Date();
	    try {
	    	dateobj=df.parse(date);
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
		carpool.setDate(df.format(dateobj));
		carpool.setCo2(score);
		carRepo.save(carpool);
		carpool.setEmployees(poolers);
		for(Employee pool: poolers) {
			pool.addCarpool(carpool);
			emRepo.save(pool);
		}
		carRepo.save(carpool);

		ModelAndView mav = new ModelAndView("confirmation");
		mav.addObject("name", passenger2.getName());
		mav.addObject("destination", company.getName());
		mav.addObject("date",date);
		mav.addObject("time",time);
		mav.addObject("saved", saved);
		mav.addObject("score", score);
		mav.addObject("id", id);
		return mav;
	}
	
	// What is this controller for? --Sam
	@RequestMapping("/list-of-routes")
	public ModelAndView showList() {
		return new ModelAndView("list-of-routes","company",coRepo.findAll());
	}
	
	// What is this controller for? --Sam
	@RequestMapping("/details-list/{id}")
	public ModelAndView showDetails(@PathVariable ("id") Company company) {
		ModelAndView mav =  new ModelAndView("company-details");
		mav.addObject("info",company.getEmployees());
		mav.addObject("cName",company.getName());
		return mav;
	}
	
	@RequestMapping("/ride{method}")
	public ModelAndView showRideToDestination(@PathVariable String method) {		
		ModelAndView mav = new ModelAndView("show-origin");
		mav.addObject("method", method);
		return mav;
	}

	// How about we reframe "Select a carpool" into "Look at routes, pick one, schedule a time, and bingo"
	// So we're not calling the API so many times
	// and so that we can add a function to the "Display Routes" page instead of it just being informational
	// --Sam
	@RequestMapping("/find-carpool")
	public ModelAndView findCarpool( 
			@RequestParam("date") String date,
			@RequestParam("time") String time) {
		ModelAndView mav = new ModelAndView ("search-carpool");
		Employee employee = (Employee)sesh.getAttribute("employee");
		Company company = coRepo.findByName(employee.getCompany().getName());
		List<Employee> employeeList = company.getEmployees();
		employeeList.remove(emRepo.findById(employee.getEmployeeId()).orElse(null));
		
		List<Distance> distanceFromYou = rCalc.getDistances(employeeList, "fromUser");
		List<Distance> distanceFromCom = rCalc.getDistances(employeeList, "fromWork");
	
		mav.addObject("date",date);
		mav.addObject("time",time);
		mav.addObject("employees", employeeList);
		mav.addObject("distanceC", distanceFromCom);
		mav.addObject("distanceY", distanceFromYou);
		return mav;
	}
	
	@RequestMapping("/routes")
	public ModelAndView showRoutes() {
		Employee employee = (Employee) sesh.getAttribute("employee");
		Company company = coRepo.findByName(employee.getCompany().getName());
		List<Employee> employeeList = company.getEmployees();
		employeeList.remove(employee);
	
		List<Distance> distanceFromYou = rCalc.getDistances(employeeList, "fromUser");
		List<Distance> distanceFromCom = rCalc.getDistances(employeeList, "fromWork");
	
		ModelAndView mav = new ModelAndView("routes");
		mav.addObject("carpools", company.getCarpool());
		mav.addObject("employees", employeeList);
		mav.addObject("distanceC", distanceFromCom);
		mav.addObject("distanceY", distanceFromYou);
		return mav;
	}
	
	//search for the carpool to ride back home
	@RequestMapping("/find-carpool-back/{id}")
	public ModelAndView carpoolBack(@PathVariable ("id") Employee employee,
			@RequestParam("date") String date,
			@RequestParam("time") String time) {
		
		//finding all the employees that work for this company
		Company company = employee.getCompany();
		List<Employee> empl = company.getEmployees();
		empl.remove(employee);
		List<Distance> distanceToYourHouse = new ArrayList<>();
		List<Distance> distanceToTheirOwn = new ArrayList<>();
		
		//calculating distance between work to rider's house and work to driver's house
		for (Employee e: empl) {
			SearchResult result1 = apiServe.getResult(employee.getAddress(),company.getAddress());
			distanceToYourHouse.add(apiServe.getDistance(result1));
			SearchResult result2 = apiServe.getResult(e.getAddress(),company.getAddress() );
			distanceToTheirOwn.add(apiServe.getDistance(result2));
		}
		ModelAndView mav = new ModelAndView("search-carpoolBack");
		mav.addObject("carpools", company.getCarpool());
		mav.addObject("employees", empl);
		mav.addObject("company",company);
		mav.addObject("distanceFY", distanceToYourHouse);
		mav.addObject("distanceFT", distanceToTheirOwn);
		mav.addObject("date",date);
		mav.addObject("time",time);
		mav.addObject("id",employee.getEmployeeId());
		return mav;
	}
	
	
	
	
	//submit carpool to ride back home
	//finding the name of the driver based on their username and sending information to jsp to show the confirmation page
	@RequestMapping("/submit-carpool-back/{id}")
	public ModelAndView carpoolBackS(@RequestParam(value="carpool")String username,
					@RequestParam(value="date",required=false) String date,
					@RequestParam(value="time",required=false) String time,
					@RequestParam(value="id", required=false) Long id) {
		List<Employee> poolers = new ArrayList<>();  
		Employee passenger1 = emRepo.findById(id).orElse(null);
		Employee passenger2 = emRepo.findByUsernameIgnoreCase(username);
		
		poolers.add(passenger1);
		poolers.add(passenger2);
		Company company = passenger1.getCompany();

		SearchResult result1 =apiServe.getResult(company.getAddress(), passenger1.getAddress());
		SearchResult result2 =apiServe.getResult(company.getAddress(), passenger2.getAddress());
		Distance d1= apiServe.getDistance(result1);
		Distance d2= apiServe.getDistance(result2);
		Distance distance;
		if(d1.getValue()>d2.getValue()) {
			distance = d2;
		}else {
			distance = d1;
		}
		
		Long d = distance.getValue();
		double miles = d/1609.344;
		CalculationService cs = new CalculationService();
		double saved=cs.calculateCO2(miles, "car");
		
		double score = saved*10;
		
		Carpool carpool = new Carpool();
		carpool.setCompany(company);
		date=date+" "+time;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    Date dateobj = new Date();
	    try {
	    	dateobj=df.parse(date);
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
		carpool.setDate(df.format(dateobj));
		carpool.setCo2(score);
		carRepo.save(carpool);
		carpool.setEmployees(poolers);
		for(Employee pool: poolers) {
			pool.addCarpool(carpool);
			emRepo.save(pool);
		}
		carRepo.save(carpool);

		ModelAndView mav = new ModelAndView("confirmationBack");
		mav.addObject("name", passenger2.getName());
		mav.addObject("destination", passenger1.getAddress());
		mav.addObject("date",date);
		mav.addObject("time",time);
		mav.addObject("saved", saved);
		mav.addObject("score", score);
		mav.addObject("id", id);
		return mav;
		
	}
	
	//Previous Routes:
		//-displays the previous routes of the employee
	@RequestMapping("/previous-routes")
	public ModelAndView previousRoutes() {
		Employee employee = (Employee)sesh.getAttribute("employee");
		Company company = coRepo.findByName(employee.getCompany().getName());
		List<Carpool> carpools = company.getCarpool();
		List<Carpool> carpoolsFiltered = new ArrayList<>();
		
		for (Carpool car : carpools) {
			if(car.getEmployees().contains(employee)) {
				carpoolsFiltered.add(car);
			}
		}
		return new ModelAndView("pastRoutes", "carpools", carpoolsFiltered);
	}
	
}
