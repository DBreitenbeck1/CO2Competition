package co.grandcircus.CO2Competition.Controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.CO2Competition.Objects.Company;
import co.grandcircus.CO2Competition.Objects.Employee;
import co.grandcircus.CO2Competition.Objects.Score;
import co.grandcircus.CO2Competition.Repos.CompanyRepo;
import co.grandcircus.CO2Competition.Repos.EmployeeRepo;

@Controller

public class ScoresController {

	@Autowired
	private HttpSession sesh;

	@Autowired
	private CompanyRepo coRepo;

	@Autowired
	private EmployeeRepo emRepo;

	// Shows scoreboard for the company of the logged in user --Sam
	@RequestMapping("/summary")
	public ModelAndView showScores() {
		// Company ID
		Employee emp = (Employee) sesh.getAttribute("employee");
		Long companyId = emp.getCompany().getCompanyId();

		// Declare Variables
		Double companyTotal = 0.0;

		// Get Scoreboard
		List<Score> scores = emRepo.findScoresByCompany(companyId);

		// Get company total from scoreboard
		for (Score score : scores) {
			System.out.println(score.getEmployee());
			companyTotal += score.getScore();
		}

		// Create ModelAndView and add objects
		ModelAndView mav = new ModelAndView("userscores");
		mav.addObject("scoreboard", scores);
		mav.addObject("total", companyTotal);
		return mav;
	}

	// Here for testing purposes
	@RequestMapping("/company/total/{id}")
	public ModelAndView companyTotal(@PathVariable Long id) {
		// Declare Variables
		Double companyTotal = 0.0;

		Company company = coRepo.getOne(id);
		Integer goal = company.getGoal();

		// Get Scoreboard
		List<Score> scores = emRepo.findScoresByCompany(id);

		// Get company total from scoreboard
		for (Score score : scores) {
			companyTotal += score.getScore();

		}
		Integer goalPercent = (int) ((companyTotal / goal) * 100);

		ModelAndView mav = new ModelAndView("companyscores");

		mav.addObject("Score", goalPercent);
		mav.addObject("Goal", goal);
		mav.addObject("Total", companyTotal);

		return mav;

//		return new ModelAndView("companyscores", "Score", goalPercent);
	}
	
	

}
