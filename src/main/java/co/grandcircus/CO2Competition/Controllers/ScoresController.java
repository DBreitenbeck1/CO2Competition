package co.grandcircus.CO2Competition.Controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.CO2Competition.Objects.Carpool;
import co.grandcircus.CO2Competition.Objects.Company;
import co.grandcircus.CO2Competition.Objects.Employee;
import co.grandcircus.CO2Competition.Objects.Score;
import co.grandcircus.CO2Competition.Repos.CarpoolRepo;
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
	
	@Autowired
	private CarpoolRepo carRepo;

	// Shows scoreboard for the company of the logged in user --Sam
	@RequestMapping("/summary")
	public ModelAndView showScores() {
		// Retrieve usable employee object --David
		Employee emp = (Employee) sesh.getAttribute("employee");
		Employee employee = emRepo.findByUsernameIgnoreCase(emp.getUsername());
		
		//company Id isn't really necessary at this point, fyi
//		Long companyId = emp.getCompany().getCompanyId();

		// Declare Variables
		Integer companyTotal = 0;

		// Get Scoreboard
	//	List<Score> scores = emRepo.findScoresByCompany(companyId);

		// Get company total from scoreboard
//		for (Score score : scores) {
//			companyTotal += score.getScore();
//		}
		
		//recover employees from company and order by scores
		List<Employee> employees = emRepo.findByCompanyOrderByScoreDesc(employee.getCompany());
		
		//calculate company total
		for (Employee e: employees) {
		companyTotal += e.getScore();	
		}
		
		
		// Create ModelAndView and add objects
		ModelAndView mav = new ModelAndView("scores/userscores");
		mav.addObject("empscores", employees);
	//	mav.addObject("scoreboard", scores);
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

		ModelAndView mav = new ModelAndView("scores/companyscores");

		mav.addObject("Score", goalPercent);
		mav.addObject("Goal", goal);
		mav.addObject("Total", companyTotal);

		return mav;

//		return new ModelAndView("companyscores", "Score", goalPercent);
	}
	
	//Displays weekly total for all company employees --David
	@RequestMapping("/summary-week")
	public ModelAndView weekSummary() {
		//Retrieve usable Employee object
		Employee emp = (Employee) sesh.getAttribute("employee");
		Employee employee = emRepo.findByUsernameIgnoreCase(emp.getUsername());
		//Define company
		Company company = employee.getCompany();
		//Define the current date
		LocalDate now = LocalDate.now();
		//Define date of one week prior
		LocalDate newDate = LocalDate.now().minusWeeks(1);
		//Format both dates so that the Repo can compare them to the Carpool's 'date' field
		DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = newDate.format(dateForm) + " 00:00";
		String today = now.format(dateForm) + " 00:00";		
		//Recover Carpools logged within that timeframe
		List<Carpool> weekCPs = carRepo.findByCompanyAndDateBetweenOrderByDateDesc(company, date, today);
	
		//create a new hashmap to store the 'local totals'
		HashMap<String, Integer> localTotals = new HashMap<String, Integer>();
		//Define comparator objects
		ValueComparator vc = new ValueComparator(localTotals);
		
		// Declare Variables
		Integer companyTotal = 0;
		//Recover company employees
		List<Employee> employees = emRepo.findByCompany(company);
		
		//Check which employees used a carpool this week and calculate their 
		//scores for the week
		for(Employee e: employees) {
			int total = 0;
			for(Carpool c:weekCPs) {
				if(c.getEmployees().contains(e)) {
					total+=(int)(c.getCo2()*10)/c.getEmployees().size();
				}
				localTotals.put(e.getName(), total);
			}
			
		}
		//Calculate company total for the week
		for (Map.Entry<String,Integer> entry: localTotals.entrySet()) {
		companyTotal += entry.getValue();	
		}
		
		//transfer to a new map sorted by scores
		TreeMap<String, Integer> localTotals2 = new TreeMap<>(vc);
		localTotals2.putAll(localTotals);
			
		
		// Create ModelAndView and add objects
		ModelAndView mav = new ModelAndView("scores/weeklyscores");
		mav.addObject("empscores", localTotals2);
	//	mav.addObject("scoreboard", scores);
		mav.addObject("total", companyTotal);
		return mav;
	
	}
	

}
