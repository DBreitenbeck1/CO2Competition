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
	
	private COCalculator coCal;
	
	private CalculationService calcServe;
	
	@RequestMapping("/login")
	public ModelAndView showLogin() {
		return new ModelAndView("login");
	}
	
	@PostMapping("/login")
	public ModelAndView checkLogin(@RequestParam ("username") String username, @RequestParam("password") String password,
			RedirectAttributes red) {

		Employee employee = emRepo.findByUsernameIgnoreCase(username);
		if(employee == null || !password.equals(employee.getPassword())) {
			red.addFlashAttribute("msg","Incorrect username or password, please try again!");
			return new ModelAndView("redirect:/login");
		}
		
		sesh.setAttribute("employee", employee);
		
		ModelAndView mav = new ModelAndView ("redirect:/employee/" + employee.getEmployeeId());
//		mav.addObject("name",employee.getName());
//		mav.addObject("company",employee.getCompany());
		return mav;
	}
	
<<<<<<< HEAD
	@RequestMapping("/employee")
	public ModelAndView showDesk() {
=======
	@RequestMapping("/logout")
	public ModelAndView showLogout(RedirectAttributes red) {
		sesh.invalidate();
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping("/register")
	public ModelAndView showReg(Company compnay) {
		return new ModelAndView ("employee-registration","company",coRepo.findAll());
	}
	
	@PostMapping("/register")
	public ModelAndView submitReg(Employee employee,Company company,RedirectAttributes red) {
		emRepo.save(employee);
//		coRepo.save(company);
		red.addFlashAttribute("msg","Thank you for registering with us, "+employee.getName());

		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping("/employee/{id}")
	public ModelAndView showDesk(@PathVariable ("id") Employee employee) {
		System.out.println(employee);
>>>>>>> 6bf8a33dac5b297d190f52d9eeb459f967b8391a
		ModelAndView mav = new ModelAndView ("employee-page");
		return mav;
	}
	
	@RequestMapping("/carpool/{id}")
	public ModelAndView showCarpool(@PathVariable("id") Employee employee) {
		Company company = coRepo.findByName(employee.getCompany().getName());
		List<Employee> allEmps = company.getEmployees();
		allEmps.remove(employee);
		ModelAndView mav = new ModelAndView ("carpool");
		mav.addObject("emId",employee.getEmployeeId());
		mav.addObject("name",employee.getName());
		mav.addObject("company",coRepo.findAll());
		mav.addObject("allEmployee", allEmps);
		return mav;
	}
	
	
	
	@RequestMapping("/tripdetails/{id}")
	public ModelAndView showDetails(
			@PathVariable ("id") Employee employee,
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
		System.out.println("em**"+username);

		// This needs better error checking, this is just a starter
		boolean validStreet = !emRepo.findByUsernameIgnoreCase(username).getStreetAddress().isEmpty() || emRepo.findByUsernameIgnoreCase(username).getStreetAddress() != null;
		boolean validCity = !emRepo.findByUsernameIgnoreCase(username).getCity().isEmpty() || emRepo.findByUsernameIgnoreCase(username).getCity() != null;
		boolean validZip = !emRepo.findByUsernameIgnoreCase(username).getZipCode().isEmpty() || emRepo.findByUsernameIgnoreCase(username).getZipCode() != null;
		if (!(validStreet && validCity && validZip)) {
			redir.addFlashAttribute("message", "Invalid address input, please try again.");
			return new ModelAndView("redirect:/logtrip");
		}
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
		
		employee.setCity(city);
		employee.setStreetAddress(street);
		employee.setZipCode(zip);
		employee.getCompany().getStreetAddress();
	
		employee.getCompany().getStreetAddress();
		System.out.println(employee.getName());
		System.out.println(employee.getAddress());
		System.out.println(employee.getEmployeeId());
		
		
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
		System.out.println(carpool.getCarpoolId());
		List<Employee> em = new ArrayList<>();
		employee.addCarpool(carpool);
		carpool.setEmployees(em);
		carRepo.save(carpool);
		emRepo.save(employee);
		System.out.println(carpool.getCarpoolId());
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
	
	
	//submit the carpool that user already chose
	@RequestMapping("/submit-carpool")
	public ModelAndView submitCarpool(@RequestParam(value="carpool")String username,
			@RequestParam(value="date",required=false) String date,
			@RequestParam(value="time",required=false) String time,
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
		
		SearchResult result;
		if(d1.getValue()>d2.getValue()) {
			result = apiServe.getResult(passenger1.getAddress(), passenger2.getAddress(), company.getAddress());
		}else {
			result = apiServe.getResult( passenger2.getAddress(), passenger1.getAddress(), company.getAddress());
		}
	

		Distance distance = apiServe.getDistance(result);
		
		
		Long d = distance.getValue()- d2.getValue(); 
		System.out.println(d2.getValue());
			//	distance.getValue();
		System.out.println(d);
		double miles = d/1609.344;
		CalculationService cs = new CalculationService();
		double saved=cs.calculateCO2(miles, "car");
		
		double score = saved*10;
		score = (int)(Math.round(score*100))/100.00;
		
		Carpool carpool = new Carpool();
		carpool.setCompany(company);
		String dateRec=date+" "+time;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    Date dateobj = new Date();
	    try {
	    	dateobj=df.parse(dateRec);
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
	
	@RequestMapping("/list-of-routes")
	public ModelAndView showList() {
		return new ModelAndView("list-of-routes","company",coRepo.findAll());
	}
	
	@RequestMapping("/details-list/{id}")
	public ModelAndView showDetials(@PathVariable ("id") Company company) {
		ModelAndView mav =  new ModelAndView("company-details");
		mav.addObject("info",company.getEmployees());
		mav.addObject("cName",company.getName());
		return mav;
	}
	
	//getting the information for ride to work 
	@RequestMapping("/ridetw/{id}")
	public ModelAndView showRideToWork(@PathVariable ("id") Employee employee) {
		
		ModelAndView mav = new ModelAndView("show-origin");
		mav.addObject("eCity",employee.getCity());
		mav.addObject("eStreet",employee.getStreetAddress());
		mav.addObject("eZip",employee.getZipCode());
		mav.addObject("cCity", employee.getCompany().getCity());
		mav.addObject("cStreet",employee.getCompany().getStreetAddress());
		mav.addObject("cZip",employee.getCompany().getZipCode());
		mav.addObject("id",employee.getEmployeeId());
		return mav;
	}
	
	@RequestMapping("/find-carpool/{id}")
	public ModelAndView findCarpool(@PathVariable("id") Employee employee, 
			@RequestParam(value="eCity", required=false) String city
			, @RequestParam("date") String date,
			@RequestParam("time") String time) {
		
		ModelAndView mav = new ModelAndView ("search-carpool");
		Company company = employee.getCompany();
//		List<Employee> em = emRepo.findByCity(city);
		List<Employee> employee1 = company.getEmployees();
		employee1.remove(employee);
		List<Distance> distanceFromYou = new ArrayList<>();
		List<Distance> distanceFromCom = new ArrayList<>();
		//find employee that work for this company and calculate their distances from home to work and to each others house
		for (Employee e: employee1) {
			SearchResult result1 = apiServe.getResult(employee.getAddress(), e.getAddress());
			distanceFromYou.add(apiServe.getDistance(result1));
			SearchResult result2 = apiServe.getResult(e.getAddress(), company.getAddress());
			distanceFromCom.add(apiServe.getDistance(result2));
		}
//		mav.addObject("list", em);
		mav.addObject("date",date);
		mav.addObject("time",time);
		mav.addObject("carpools", company.getCarpool());
		mav.addObject("employees", employee1);
		mav.addObject("company", company);
		mav.addObject("distanceC", distanceFromCom);
		mav.addObject("distanceY", distanceFromYou);
		mav.addObject("emId",employee.getEmployeeId());
		return mav;
	}
	


	@RequestMapping("/routes/{id}")
	public ModelAndView showRoutes(@PathVariable ("id") Employee employee) {
		Company company = employee.getCompany();
		List<Employee> emps = company.getEmployees();
		emps.remove(employee);
		List<Distance> distanceFromYou = new ArrayList<>();
		List<Distance> distanceFromCom = new ArrayList<>();
		for (Employee e: emps) {
			SearchResult result1 = apiServe.getResult(employee.getAddress(), e.getAddress());
			distanceFromYou.add(apiServe.getDistance(result1));
			SearchResult result2 = apiServe.getResult(e.getAddress(), company.getAddress());
			distanceFromCom.add(apiServe.getDistance(result2));
		}
		
		
		
		ModelAndView mav = new ModelAndView("routes");
		mav.addObject("carpools", company.getCarpool());
		mav.addObject("employees", emps);
		mav.addObject("distanceC", distanceFromCom);
		mav.addObject("distanceY", distanceFromYou);

		return mav;
	}
	
	//show the start and end address which is work to home 
	//and other setups on jsp to be picked up
	@RequestMapping("/ridebh/{id}")
	public ModelAndView showRideBackHome(@PathVariable ("id") Employee employee) {
		
		ModelAndView mav = new ModelAndView("show-endLocation");
		mav.addObject("eCity", employee.getCity());
		mav.addObject("eStreet",employee.getStreetAddress());
		mav.addObject("eZip",employee.getZipCode());
		mav.addObject("cCity", employee.getCompany().getCity());
		mav.addObject("cStreet",employee.getCompany().getStreetAddress());
		mav.addObject("cZip",employee.getCompany().getZipCode());
		mav.addObject("id",employee.getEmployeeId());
		return mav;
	}
	
	//search for the carpool to ride back home
	//
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
		score = (int)(Math.round(score*100))/100.00;
		 
		Carpool carpool = new Carpool();
		carpool.setCompany(company);
		String dateRec=date+" "+time;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    Date dateobj = new Date();
	    try {
	    	dateobj=df.parse(dateRec);
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
		Company company = employee.getCompany();
		List<Carpool> carpools = company.getCarpool();
		List<Carpool> cp = new ArrayList<>();
		for (int i =0; i<carpools.size(); i++) {
			if (carpools.get(i).getEmployees().contains(employee)) {
				cp.add(carpools.get(i));
			}
		}
		ModelAndView mav = new ModelAndView("pastRoutes");
		mav.addObject("carpools", cp);
		return mav;
	}
	
	
	
//	@RequestParam(value="carpool")String username,
//	@RequestParam(value="date",required=false) String date,
//	@RequestParam(value="time",required=false) String time,
//	@PathVariable("id") Employee employee,
//	@RequestParam(value="id", required=false) Long id) {
//
//ModelAndView mav = new ModelAndView("confirmationBack");
//mav.addObject("name",emRepo.findByUsernameIgnoreCase(username).getName());
//mav.addObject("address",employee.getCity());
//mav.addObject("date",date);
//mav.addObject("time",time);
//mav.addObject("id", id);
//return mav;
//
	
}
