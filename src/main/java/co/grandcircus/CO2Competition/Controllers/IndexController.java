package co.grandcircus.CO2Competition.Controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.CO2Competition.ApiService;
import co.grandcircus.CO2Competition.COCalculator;
import co.grandcircus.CO2Competition.Objects.Carpool;
import co.grandcircus.CO2Competition.Objects.Company;
import co.grandcircus.CO2Competition.Objects.Employee;
import co.grandcircus.CO2Competition.Repos.CarpoolRepo;
import co.grandcircus.CO2Competition.Repos.CompanyRepo;
import co.grandcircus.CO2Competition.Repos.EmployeeRepo;

@Controller
public class IndexController {

	@Autowired
	HttpSession sesh;

	@Value("${api_key}")
	String apiKey;

	@Autowired
	private ApiService apiServe;

	@Autowired
	private COCalculator coCal;

	@Autowired
	private EmployeeRepo emRepo;

	@Autowired
	private CompanyRepo coRepo;

	@Autowired
	private CarpoolRepo carRepo;


	@RequestMapping("/logtrip")
	public ModelAndView logTripForm() {
		return new ModelAndView("tripform");
	}


	@RequestMapping("/")
	public ModelAndView showIndex() {

		List<Company> company = coRepo.findAll();

		HashMap<String, Integer> co = new HashMap<String, Integer>();

		ValueComparator value = new ValueComparator(co);

		for (Company c : company) {
			Integer total = 0;

			for (Employee e : c.getEmployees()) {
				total += e.getScore();
			}
			co.put(c.getName(), total);
		}
		TreeMap<String, Integer> cc = new TreeMap<>(value);
		cc.putAll(co);
		ModelAndView mav = new ModelAndView("index/index");

		mav.addObject("cc", cc);
		return mav;
	}

	@RequestMapping("/dashboard")
	public ModelAndView showDesk() {
		Employee employee = new Employee();
		try {
			Employee user = (Employee) sesh.getAttribute("employee");
			employee = emRepo.findByUsernameIgnoreCase(user.getUsername());
		} catch (Exception e) {
			System.out.println("Please Login");
			return new ModelAndView("redirect:/login/login");
		}

		// defines current date
		LocalDate now = LocalDate.now();
		DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String today = now.format(dateForm) + " 00:00";

		// retrieves the user's carpools and orders by date
		List<Carpool> carpools = carRepo.findByEmployeesContainingOrderByDateDesc(employee);
		List<Integer> userScore = new ArrayList<>();

		for (Carpool cp : carpools) {
			if (cp.getCo2() != null) {
				int s = cp.getCo2() / cp.getEmployees().size();
				userScore.add(s);
			}

		}

		ModelAndView mav = new ModelAndView("index/dashboard");
		mav.addObject("noCP", "You Don't Have Any Carpools Yet!");
		mav.addObject("score", employee.getScore());
		mav.addObject("userScore", userScore);
		mav.addObject("carpools", carpools);
		mav.addObject("today", today);
		return mav;

	}

	@RequestMapping("/about")
	public ModelAndView showAbout() {
		return new ModelAndView("index/about");

	}

}
