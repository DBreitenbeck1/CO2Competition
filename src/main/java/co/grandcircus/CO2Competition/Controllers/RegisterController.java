package co.grandcircus.CO2Competition.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.grandcircus.CO2Competition.ApiService;
import co.grandcircus.CO2Competition.Entities.Distance;
import co.grandcircus.CO2Competition.Entities.SearchResult;
import co.grandcircus.CO2Competition.Objects.Company;
import co.grandcircus.CO2Competition.Objects.Employee;
import co.grandcircus.CO2Competition.Repos.CompanyRepo;
import co.grandcircus.CO2Competition.Repos.EmployeeRepo;

@Controller
public class RegisterController {

	@Autowired
	private HttpSession sesh;
	@Autowired
	private EmployeeRepo emRepo;
	@Autowired
	private CompanyRepo coRepo;
	
	@Autowired
	private ApiService apiServe;

	@RequestMapping("/register")
	public ModelAndView showReg(Company company) {
		ModelAndView mav = new ModelAndView("registration/employee-registration");
		mav.addObject("companies", coRepo.findAll());
		mav.addObject("vehicleTypes", emRepo.findAllVehicleType());
		return mav;
	}

	@PostMapping("/register")

	public ModelAndView submitReg(Employee employee, @RequestParam String lastName,
			@RequestParam String passwordConfirm,
			RedirectAttributes red) {
		// if password does not match
		if (!employee.getPassword().equals(passwordConfirm)) {
			red.addFlashAttribute("message", "Passwords do not match.");
			red.addFlashAttribute("messageType", "warning");
			return new ModelAndView("redirect:/register");
		}
		employee.setName(employee.getName() + " " + lastName);		
		SearchResult result = apiServe.getResult(employee.getAddress(), employee.getCompany().getAddress());
		Distance dist = apiServe.getDistance(result);
		if (dist == null) {
			ModelAndView mav = new ModelAndView("redirect:/register");
			red.addFlashAttribute("message", "Invalid Address");
			red.addFlashAttribute("messageType", "danger");
			return mav;
		}else {
		
		emRepo.save(employee);
		red.addFlashAttribute("message", "Thank you for registering with us, " + employee.getName());
		red.addFlashAttribute("messageType", "success");
		return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping("/registercompany")
	public ModelAndView showCompanyReg() {
		ModelAndView mav = new ModelAndView("registration/registercompany");
		return mav;
	}

	@PostMapping("/registercompany")
	public ModelAndView submitCompany(Company company, RedirectAttributes red) {
	
		Company com = coRepo.findAll().get(0);
		SearchResult result = apiServe.getResult(company.getAddress(), com.getAddress());
		Distance dist = apiServe.getDistance(result);
		if (dist == null) {
			ModelAndView mav = new ModelAndView("redirect:/registercompany");
			red.addFlashAttribute("message", "Invalid Address");
			red.addFlashAttribute("messageType", "danger");
			return mav;
		} else {
		coRepo.save(company);

		red.addFlashAttribute("msg", "Enjoy your day, " + company.getName());

		return new ModelAndView("redirect:/login");
		}
	}
		
	

}
