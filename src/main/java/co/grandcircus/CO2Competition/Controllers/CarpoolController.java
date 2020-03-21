package co.grandcircus.CO2Competition.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.CO2Competition.RouteCalculator;
import co.grandcircus.CO2Competition.Entities.Distance;
import co.grandcircus.CO2Competition.Objects.Carpool;
import co.grandcircus.CO2Competition.Objects.Company;
import co.grandcircus.CO2Competition.Objects.Employee;
import co.grandcircus.CO2Competition.Repos.CompanyRepo;

@Controller
public class CarpoolController {
	@Autowired
	private HttpSession sesh;
	@Autowired
	private CompanyRepo coRepo;
	@Autowired
	private RouteCalculator rCalc;

	// Shows available routes, form directs to /ride
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

	// Directed to by /routes
	// Shows form asking user for pickup time and date
	// Directs to /submit-carpool (WORK IN PROGRESS)
	@RequestMapping("/ride")
	public ModelAndView showRideToDestination(@RequestParam String method, @RequestParam Double distanceFromCom,
			@RequestParam Double distanceFromYou, @RequestParam String username) {
		ModelAndView mav = new ModelAndView("show-origin");
		mav.addObject("method", method);
		mav.addObject("distanceFromCom", distanceFromCom);
		mav.addObject("distanceFromYou", distanceFromYou);
		mav.addObject("username", username);
		return mav;
	}

	// Previous Routes:
	// -displays the previous routes of the employee
	@RequestMapping("/previous-routes")
	public ModelAndView previousRoutes() {
		Employee employee = (Employee) sesh.getAttribute("employee");
		Company company = coRepo.findByName(employee.getCompany().getName());

		List<Carpool> carpools = company.getCarpool();
		List<Carpool> carpoolsFiltered = new ArrayList<>();

		for (Carpool car : carpools) {
			if (car.getEmployees().contains(employee)) {
				carpoolsFiltered.add(car);
			}
		}
		return new ModelAndView("pastRoutes", "carpools", carpoolsFiltered);
	}

}
