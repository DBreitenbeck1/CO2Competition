package co.grandcircus.CO2Competition.Controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
		return mav;
	}
	@RequestMapping("/tripdetails/{id}")
	public ModelAndView showDetails(
			@PathVariable ("id") Employee employee,
			@RequestParam String street,
			@RequestParam String city,
			@RequestParam String zip,
			@RequestParam (value="co") String des, 
//			@RequestParam String street1,
//			@RequestParam String city1,
//			@RequestParam String zip1,
			RedirectAttributes redir
			) {
		// This needs better error checking, this is just a starter
		boolean validStreet = !street.isEmpty() || street != null;
		boolean validCity = !city.isEmpty() || city != null;
		boolean validZip = !zip.isEmpty() || zip != null;
		if (!(validStreet && validCity && validZip)) {
			redir.addFlashAttribute("message", "Invalid address input, please try again.");
			return new ModelAndView("redirect:/logtrip");
		}
		ModelAndView mav = new ModelAndView("details");
		String address1 = street+city+zip;
//		String address2 = street1+city1+zip1;
		String address2 = coRepo.findByName(des).getStreetAddress() + coRepo.findByName(des).getCity() + coRepo.findByName(des).getZipCode();
		Distance distance = apiServe.getDistance(address1, address2);
		if (distance!=null) {
	
		mav.addObject("street", street);
		mav.addObject("city", city);
		mav.addObject("zip", zip);
		mav.addObject("coName",coRepo.findByName(des).getName());
		mav.addObject("street1", coRepo.findByName(des).getStreetAddress());
		mav.addObject("city1", coRepo.findByName(des).getCity());
		mav.addObject("zip1", coRepo.findByName(des).getZipCode());
		mav.addObject("distance", distance);
		mav.addObject("em", coCal.smallCar(distance.getValue() ));
		
		employee.setCity(city);
		employee.setStreetAddress(street);
		employee.setZipCode(zip);
		employee.getCompany().getStreetAddress();
		
		
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	    Date dateobj = new Date();
	    Carpool carpool = new Carpool();
		carpool.setCo2(coCal.smallCar(distance.getValue() ));
		carpool.setDate(df.format(dateobj));
		//add userId
		carRepo.save(carpool);
//		employee.getEmployeeId();
		
//		carpool.setEmployees(employee);
//		carRepo.saveAll(employee);
		} else {
			mav.addObject("invalid", "No such address");
		}
		return mav;
		
	}
}
