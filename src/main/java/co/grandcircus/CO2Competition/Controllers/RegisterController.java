package co.grandcircus.CO2Competition.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@RequestMapping("/register")
	public ModelAndView showReg(Company company, Employee employee) {
		ModelAndView mav = new ModelAndView("registration/employee-registration");

		mav.addObject("companies", coRepo.findAll());
		mav.addObject("vehicleTypes", emRepo.findAllVehicleType());
		return mav;
	}

	@PostMapping("/register")
	public ModelAndView submitReg(Employee employee, RedirectAttributes red) {
		emRepo.save(employee);
//		coRepo.save(company);
		red.addFlashAttribute("msg", "Thank you for registering with us, " + employee.getName());

		return new ModelAndView("redirect:/login");
	}

	@RequestMapping("/registercompany")
	public ModelAndView showCompanyReg() {
		ModelAndView mav = new ModelAndView("registration/registercompany");
		return mav;
	}

	@PostMapping("/registercompany")
	public ModelAndView submitCompany(Company company, RedirectAttributes red) {
		coRepo.save(company);
//		coRepo.save(company);
		red.addFlashAttribute("msg", "Enjoy your day, " + company.getName());

		return new ModelAndView("redirect:/login");
	}
	

}
