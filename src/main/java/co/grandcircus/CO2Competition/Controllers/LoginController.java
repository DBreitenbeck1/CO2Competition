package co.grandcircus.CO2Competition.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.grandcircus.CO2Competition.Objects.Employee;
import co.grandcircus.CO2Competition.Repos.EmployeeRepo;

@Controller
public class LoginController {

	@Autowired
	private HttpSession sesh;
	
	@Autowired
	private EmployeeRepo emRepo;
	
	
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

		return new ModelAndView ("redirect:/employee/" + employee.getEmployeeId());
	}
	
	@RequestMapping("/employee/{id}")
	public ModelAndView showDesk(@PathVariable ("id") Employee employee) {
		return new ModelAndView ("employee-page","employee",employee);
	}
}
