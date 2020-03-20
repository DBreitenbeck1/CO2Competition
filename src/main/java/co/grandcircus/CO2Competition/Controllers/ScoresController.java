package co.grandcircus.CO2Competition.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.CO2Competition.ApiService;
import co.grandcircus.CO2Competition.Repos.CarpoolRepo;
import co.grandcircus.CO2Competition.Repos.CompanyRepo;
import co.grandcircus.CO2Competition.Repos.EmployeeRepo;

@Controller

public class ScoresController {

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

	@RequestMapping("/userscores")
	public ModelAndView showUserScores() {
		ModelAndView mav = new ModelAndView("userscores");
		return mav;
	}

}
