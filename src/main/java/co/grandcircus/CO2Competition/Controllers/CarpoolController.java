package co.grandcircus.CO2Competition.Controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.grandcircus.CO2Competition.COCalculator;
import co.grandcircus.CO2Competition.RouteCalculator;
import co.grandcircus.CO2Competition.Entities.Distance;
import co.grandcircus.CO2Competition.Objects.Carpool;
import co.grandcircus.CO2Competition.Objects.Company;
import co.grandcircus.CO2Competition.Objects.Employee;
import co.grandcircus.CO2Competition.Repos.CarpoolRepo;
import co.grandcircus.CO2Competition.Repos.CompanyRepo;
import co.grandcircus.CO2Competition.Repos.EmployeeRepo;

@Controller
public class CarpoolController {
	//adding a comment to test
	
	@Autowired
	private HttpSession sesh;
	@Autowired
	private CompanyRepo coRepo;
	@Autowired
	private RouteCalculator rCalc;
	@Autowired
	private EmployeeRepo emRepo;
	@Autowired
	private CarpoolRepo carRepo;
	@Autowired
	private COCalculator coCal;

	// Shows available routes, form directs to /ride
	@RequestMapping("/routes")
	public ModelAndView showRoutes() {
		Employee user = (Employee) sesh.getAttribute("employee");
		Employee employee = emRepo.findByUsernameIgnoreCase(user.getUsername());
		Company company = coRepo.findByName(employee.getCompany().getName());
		List<Employee> employeeList = company.getEmployees();
		employeeList.remove(employee);

		List<Distance> distanceFromYou = rCalc.getDistances(employeeList, "fromUser");
		List<Distance> distanceFromCom = rCalc.getDistances(employeeList, "fromWork");
		ModelAndView mav = new ModelAndView("carpool/routes");
		mav.addObject("employ", employee);
		mav.addObject("company", company);
		mav.addObject("carpools", company.getCarpool());
		mav.addObject("employees", employeeList);
		mav.addObject("distanceC", distanceFromCom);
		mav.addObject("distanceY", distanceFromYou);
		return mav;
	}

	// Directed to by /routes
	// Shows form asking user for pickup time and date
	// Directs to /submit-carpool
	@RequestMapping("/ride")
	public ModelAndView showRideToDestination(@RequestParam String method, @RequestParam Double distanceFromCom,
			@RequestParam Double distanceFromYou, @RequestParam String username) {
		ModelAndView mav = new ModelAndView("carpool/ride");
		mav.addObject("method", method);
		mav.addObject("distanceFromCom", distanceFromCom);
		mav.addObject("distanceFromYou", distanceFromYou);
		mav.addObject("username", username);
		return mav;
	}
	
	// Directed to by /ride
	// submit the carpool that the user has chosen
	// end point
	@RequestMapping("/submit-carpool")
	public ModelAndView submitCarpool(@RequestParam String username, @RequestParam String date,
			@RequestParam String time, RedirectAttributes redir) {
		// Creates list of carpoolers and adds current user and selected carpooler
		Employee tester = (Employee) sesh.getAttribute("employee");
		
		Employee employee = emRepo.findByUsernameIgnoreCase(tester.getUsername());
		Employee passenger = emRepo.findByUsernameIgnoreCase(username);
		
		List<Employee> poolers = Arrays.asList(employee, passenger);
		Company company = coRepo.findByName(employee.getCompany().getName());
		
	

		// Get distances to compare and calculate CO2 Savings and points earned
		Double savings = coCal.calculateDifference(employee, passenger);
		
		
		// User scores 1 pt for 0.10 lbs/CO2 Saved
		Integer score = (int)(savings * 10);
		
		Integer userScore = score/poolers.size();
		
		// Create new Carpool object to save to database
		Carpool carpool = new Carpool();
		carpool.setCompany(company);
		carpool.setDate(date + " " + time);
		carpool.setCo2(score);

		// Tie Carpool to employees
		carpool.setEmployees(poolers);
		for (Employee pooler : poolers) {
			if (pooler.getScore() != null) {
			pooler.addToScore(userScore);
			}  else {
				pooler.setScore(userScore);
			}
			pooler.addCarpool(carpool);
		}
		
		// Save to database
		carRepo.save(carpool);
		
		// Add confirmation message
		String message = "<p>Your carpool request was sucessfully submitted. " +
				passenger.getName() + " will carpool with you on " +
				date + " at " + time + " to take you to " +
				company.getName() + ".</p><p>This will save " +
				savings + " lbs of CO2 and earn you " +
				userScore + " points!</p>";
		redir.addFlashAttribute("message", message);
		redir.addFlashAttribute("messageType", "success");
		return new ModelAndView("redirect:/dashboard");
	}
	
	// Work from Home Button
	// directs from /routes
	// Displays same jsp as /ride "show-origin"
	// which then directs to /submit-carpool
	@RequestMapping("/workfromhome")
	public ModelAndView workFromHome() {
		
		// finds prev distance from work
		String method = "workFromHome";
		Distance distanceFromCom = rCalc.getDistanceSingle();
		
		
		// creates new ModelAndView and assigns mapping
		ModelAndView mav = new ModelAndView("carpool/show-origin");
		mav.addObject("method", method);
		mav.addObject("distanceFromCom", distanceFromCom);
		mav.addObject("distanceFromYou", null);
		mav.addObject("username", null);
		return mav;
		
		
//		public ModelAndView showRideToDestination(@RequestParam String method, @RequestParam Double distanceFromCom,
//				@RequestParam Double distanceFromYou, @RequestParam String username) {
//			ModelAndView mav = new ModelAndView("show-origin");
//			mav.addObject("method", method);
//			mav.addObject("distanceFromCom", distanceFromCom);
//			mav.addObject("distanceFromYou", distanceFromYou);
//			mav.addObject("username", username);
//			return mav;
		
		// /ride requests
		// @RequestParam String method, @RequestParam Double distanceFromCom,
		// @RequestParam Double distanceFromYou, @RequestParam String username
	}
	
	
	
	
	// Previous Routes:
	// -displays the previous routes of the employee 
	//Marks off which ones are completed and which are still to be done
	@RequestMapping("/previous-routes")
	public ModelAndView previousRoutes() {
		Employee user = (Employee) sesh.getAttribute("employee");
		Employee employee = emRepo.findByUsernameIgnoreCase(user.getUsername());
		Company company = coRepo.findByName(employee.getCompany().getName());

		//defines current date
		LocalDate now = LocalDate.now();
		DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String today = now.format(dateForm) + " 00:00";		
		
		//retrieves the user's carpools and orders by date
		List<Carpool> carpools = carRepo.findByEmployeesContainingOrderByDateDesc(employee);
		
		ModelAndView mav = new ModelAndView("carpool/pastRoutes");
		mav.addObject("carpools", carpools);
		mav.addObject("today",today);
		
		return mav;
	}
	@RequestMapping("/cancel/{id}")
	public ModelAndView showCancel(@PathVariable("id") Long carpool) {
		Carpool c = carRepo.findById(carpool).orElse(null);
		for (Employee e: c.getEmployees()) {
			e.getCarpool().remove(c);
			emRepo.save(e);
		}
		
		carRepo.save(c);
		carRepo.delete(c);

		return new ModelAndView("redirect:/dashboard");
	}

}
