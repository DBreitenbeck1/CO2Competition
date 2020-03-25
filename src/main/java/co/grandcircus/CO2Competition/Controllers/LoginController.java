package co.grandcircus.CO2Competition.Controllers;

import java.util.List;

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
public class LoginController {
	@Autowired
	private HttpSession sesh;
	@Autowired
	private EmployeeRepo emRepo;
	@Autowired
	private CompanyRepo coRepo;

	@RequestMapping("/login")
	public ModelAndView showLogin() {
		return new ModelAndView("login/login");
	}

	@PostMapping("/login")
	public ModelAndView checkLogin(@RequestParam("username") String username, @RequestParam("password") String password,
			RedirectAttributes red) {

		Employee employee = emRepo.findByUsernameIgnoreCase(username);

		if (employee == null || !password.equals(employee.getPassword())) {
			red.addFlashAttribute("message", "Incorrect username or password, please try again!");
			red.addFlashAttribute("messageType", "danger");
			return new ModelAndView("redirect:/login");
		}

		sesh.setAttribute("employee", employee);
		System.out.println(employee.isAdmin());
		return new ModelAndView("redirect:/dashboard");
	}

	@RequestMapping("/logout")
	public ModelAndView showLogout(RedirectAttributes red) {
		sesh.invalidate();
		red.addFlashAttribute("message", "Successfully logged out.");
		red.addFlashAttribute("messageType", "success");
		return new ModelAndView("redirect:/login");
	}
}
