package co.grandcircus.CO2Competition.Controllers;

import java.text.DateFormat;
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
			return new ModelAndView("login");
		}
		System.out.println(username);
		sesh.setAttribute("employee", employee);
		System.out.println(username);

		ModelAndView mav = new ModelAndView ("redirect:/employee/" + employee.getEmployeeId());
		mav.addObject("name",employee.getName());
//		mav.addObject("company",employee.getCompany());
		return mav;
	}
	
	@RequestMapping("/employee/{id}")
	public ModelAndView showDesk(@PathVariable ("id") Employee employee) {
		System.out.println(employee);
		ModelAndView mav = new ModelAndView ("employee-page");
		mav.addObject("name", employee.getName());
		mav.addObject("company", employee.getCompany().getName());
		mav.addObject("emId",employee.getEmployeeId());
		return mav;
	}
	
	@RequestMapping("/carpool/{id}")
	public ModelAndView showCarpool(@PathVariable("id") Employee employee) {
		Company company =coRepo.findByName(employee.getCompany().getName());
		System.out.println(company.getName());
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
			@RequestParam(value="em") String username,
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
	
	@RequestMapping("/carpool")
	public ModelAndView assignCarpool(
			@RequestParam("id") Long id,
			@RequestParam(value="passengers", defaultValue="") List<Long> ids
	//@RequestParam(value="toppings", defaultValue = "") List<String> toppings,
			) {
		System.out.println(ids);
		List<Employee> passengers = new ArrayList<>();
		for(Long pass: ids) {
			passengers.add(emRepo.findById(pass).orElse(null));
		}
		Employee driver = emRepo.findById(id).orElse(null);
		Company company = driver.getCompany();
		
		double saved = 0;
		double total = 0;
		double e =0;
		for(Employee pass: passengers) {
			SearchResult result =apiServe.getResult(pass.getAddress(), company.getAddress());
		Distance distance= apiServe.getDistance(result);
		System.out.println(distance.getValue());
		Long d = distance.getValue();
		System.out.println(d);
		double miles = d/1609.344;
		System.out.println(miles);
		CalculationService cs = new CalculationService();
		e=cs.calculateCO2(miles, "car");
	//	e=7.08*miles;
	//	e=calcServe.calculateCO2(miles, "car");
			System.out.println(e);
			saved += e;
			total +=e;
		}
	
		
		passengers.add(driver);
		
		Carpool carpool = new Carpool();
		carpool.setCompany(driver.getCompany());
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	    Date dateobj = new Date();
		carpool.setDate(df.format(dateobj));
		carpool.setCo2(saved);
		carRepo.save(carpool);
		carpool.setEmployees(passengers);
		for(Employee pass: passengers) {
			pass.addCarpool(carpool);
			emRepo.save(pass);
		}
		carRepo.save(carpool);
		System.out.println(carpool.getCarpoolId());
		ModelAndView mav = new ModelAndView("redirect:/carpoolsummary/"+carpool.getCarpoolId());		
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
		mav.addObject("distanceC", distanceFromCom);
		mav.addObject("distanceY", distanceFromYou);
		mav.addObject("emId",employee.getEmployeeId());
		return mav;
	}
	
	@RequestMapping("/submit-carpool")
	public ModelAndView submitCarpool(@RequestParam(value="carpool")String username,
			@RequestParam(value="date",required=false) String date,
			@RequestParam(value="time",required=false) String time,
			@RequestParam(value="id", required=false) Long id) {
		System.out.println("emid" +id);
		ModelAndView mav = new ModelAndView("confirmation");
		mav.addObject("name",emRepo.findByUsernameIgnoreCase(username).getName());
		mav.addObject("company",emRepo.findByUsernameIgnoreCase(username).getCompany().getName());
		mav.addObject("date",date);
		mav.addObject("time",time);
		mav.addObject("id", id);
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
	
	@RequestMapping("/ridebh/{id}")
	public ModelAndView showRideBackHome(@PathVariable ("id") Employee employee) {
		
		ModelAndView mav = new ModelAndView("show-endLocation");
		mav.addObject("eCity",employee.getCity());
		mav.addObject("eStreet",employee.getStreetAddress());
		mav.addObject("eZip",employee.getZipCode());
		mav.addObject("cCity", employee.getCompany().getCity());
		mav.addObject("cStreet",employee.getCompany().getStreetAddress());
		mav.addObject("cZip",employee.getCompany().getZipCode());
		mav.addObject("id",employee.getEmployeeId());
		return mav;
	}
	
	@RequestMapping("/find-carpool-back/{id}")
	public ModelAndView carpoolBack(@PathVariable ("id") Employee employee,
			@RequestParam("date") String date,
			@RequestParam("time") String time) {
		
		Company company = employee.getCompany();
		List<Employee> empl = company.getEmployees();
		empl.remove(employee);
		List<Distance> distanceToYourHouse = new ArrayList<>();
		List<Distance> distanceToTheirOwn = new ArrayList<>();
		
		for (Employee e: empl) {
			SearchResult result1 = apiServe.getResult(employee.getAddress(),company.getAddress());
			distanceToYourHouse.add(apiServe.getDistance(result1));
			SearchResult result2 = apiServe.getResult(e.getAddress(),company.getAddress() );
			distanceToTheirOwn.add(apiServe.getDistance(result2));
		}
		ModelAndView mav = new ModelAndView("search-carpoolBack");
		mav.addObject("carpools", company.getCarpool());
		mav.addObject("employees", empl);
		mav.addObject("cCity",company.getCity());
		mav.addObject("cStreet",company.getStreetAddress());
		mav.addObject("cZip",company.getZipCode());
		mav.addObject("distanceFY", distanceToYourHouse);
		mav.addObject("distanceFT", distanceToTheirOwn);
		mav.addObject("date",date);
		mav.addObject("time",time);
		mav.addObject("id",employee.getEmployeeId());
		return mav;
	}
	
	@RequestMapping("/submit-carpool-back/{id}")
	public ModelAndView carpoolBackS(@RequestParam(value="carpool")String username,
			@RequestParam(value="date",required=false) String date,
			@RequestParam(value="time",required=false) String time,
			@PathVariable("id") Employee employee,
			@RequestParam(value="id", required=false) Long id) {
		
		ModelAndView mav = new ModelAndView("confirmationBack");
		mav.addObject("name",emRepo.findByUsernameIgnoreCase(username).getName());
		mav.addObject("address",employee.getCity());
		mav.addObject("date",date);
		mav.addObject("time",time);
		mav.addObject("id", id);
		return mav;
	}
	
	
	@RequestMapping("/previous-routes/{id}")
	public ModelAndView previousRoutes(@PathVariable ("id") Employee employee) {
		Company company = employee.getCompany();
		ModelAndView mav = new ModelAndView("routes");
		mav.addObject("carpools", company.getCarpool());
		return mav;
	}
	
}
