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
import co.grandcircus.CO2Competition.Entities.Distance;
import co.grandcircus.CO2Competition.Objects.Carpool;
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
		
		
		ModelAndView mav = new ModelAndView ("carpool");
		mav.addObject("emId",employee.getEmployeeId());
		mav.addObject("company",coRepo.findAll());
		mav.addObject("allEmployee",emRepo.findAll());
		return mav;
	}
	
	
	
	@RequestMapping("/tripdetails/{id}")
	public ModelAndView showDetails(
			@PathVariable ("id") Employee employee,
//			@RequestParam String street,
//			@RequestParam String city,
//			@RequestParam String zip,
//			@RequestParam (value="co") String des, 
			@RequestParam(value="em") String username,
//			@RequestParam String street1,
//			@RequestParam String city1,
//			@RequestParam String zip1,
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
//		String address2 = street1+city1+zip1;
		String address2 = emRepo.findByUsernameIgnoreCase(username).getCompany().getStreetAddress()+emRepo.findByUsernameIgnoreCase(username).getCompany().getCity()+ emRepo.findByUsernameIgnoreCase(username).getCompany().getZipCode();
		Distance distance = apiServe.getDistance(address1, address2);
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
		
//		employee.setCity(city);
//		employee.setStreetAddress(street);
//		employee.setZipCode(zip);
//		employee.getCompany().getStreetAddress();
	
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
		//add userId
		
//		employee.getEmployeeId();

//		carpool.setEmployees(employee);
//		carRepo.saveAll(employee);
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
	
	@RequestMapping("/summary")
	public ModelAndView showSummary() {
		return new ModelAndView("summary","saved",carRepo.findAll());
	}
}
