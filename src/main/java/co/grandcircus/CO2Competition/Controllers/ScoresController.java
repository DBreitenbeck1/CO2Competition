package co.grandcircus.CO2Competition.Controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
		//All-Time____
		// Retrieve usable employee object --David
		Employee emp = (Employee) sesh.getAttribute("employee");
		Employee employee = new Employee();
		try {
		employee = emRepo.findByUsernameIgnoreCase(emp.getUsername());
		} catch (NullPointerException e) {
			System.out.println("User not found");
		}
		Company company = employee.getCompany();	
	
		// Declare Variables
		Integer companyTotalAT = 0;

		//recover employees from company and order by scores
		List<Employee> employeesAT = emRepo.findByCompanyOrderByScoreDesc(company);
		
		//calculate company total
		for (Employee e: employeesAT) {
		companyTotalAT += e.getScore();	
		}
		
	//Weekly____
		//Define the current date
		LocalDate now = LocalDate.now();
		//Define date of one week prior
		LocalDate weekLess = LocalDate.now().minusWeeks(1);
		//Format both dates so that the Repo can compare them to the Carpool's 'date' field
		DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = weekLess.format(dateForm) + " 00:00";
		String today = now.format(dateForm) + " 00:00";		
		//Recover Carpools logged within that timeframe
		List<Carpool> weekCPs = carRepo.findByCompanyAndDateBetweenOrderByDateDesc(company, date, today);
	
		//create a new hashmap to store the 'weekly totals'
		HashMap<String, Integer> weeklyTotals = new HashMap<String, Integer>();
		//Define comparator objects
		ValueComparator vc = new ValueComparator(weeklyTotals);
		
		// Declare Variables
		Integer companyTotalWK = 0;
		//Recover company employees
		List<Employee> employeesWK = emRepo.findByCompany(company);
		
		//Check which employees used a carpool this week and calculate their 
		//scores for the week
		for(Employee e: employeesWK) {
			int total = 0;
			for(Carpool c : weekCPs) {
				if(c.getEmployees().contains(e)) {
					int save = c.getCo2();
					
					total+=(save/c.getEmployees().size());
				}	
			}
			weeklyTotals.put(e.getName(), total);
			
		}
		
	
		//Calculate company total for the week
		for (Map.Entry<String,Integer> entry: weeklyTotals.entrySet()) {
		companyTotalWK += entry.getValue();	
		}
		
		//transfer to a new map sorted by scores
		TreeMap<String, Integer> weeklyTotals2 = new TreeMap<>(vc);
		weeklyTotals2.putAll(weeklyTotals);
			
		
	//Monthly__	
		LocalDate monthLess = LocalDate.now().minusMonths(1);
		String month = monthLess.format(dateForm) + " 00:00";
		
		List<Carpool> monthCPs = carRepo.findByCompanyAndDateBetweenOrderByDateDesc(company, month, today);
		
		//create a new hashmap to store the 'monthly totals'
		HashMap<String, Integer> monthlyTotals = new HashMap<String, Integer>();
		//Define comparator objects
		ValueComparator vcm = new ValueComparator(monthlyTotals);
		
		// Declare Variables
		Integer companyTotalMN = 0;
		//Recover company employees
		List<Employee> employeesMN = emRepo.findByCompany(company);
		
		//Check which employees used a carpool this week and calculate their 
		//scores for the week
		for(Employee e: employeesMN) {
			int total = 0;
			for(Carpool c:monthCPs) {
				if(c.getEmployees().contains(e)) {
					System.out.println(e.getName());
					int save = c.getCo2();
					System.out.println(save);
					total+=(save/c.getEmployees().size());
					System.out.println(total);
				}
				
			}
			monthlyTotals.put(e.getName(), total);
			
		}
		
		
		//Calculate company total for the week
		for (Map.Entry<String,Integer> entry: monthlyTotals.entrySet()) {
		companyTotalMN += entry.getValue();	
		}
		
		//transfer to a new map sorted by scores
		TreeMap<String, Integer> monthlyTotals2 = new TreeMap<>(vcm);
		monthlyTotals2.putAll(monthlyTotals);
	
		
		
		
		
		// Create ModelAndView and add objects
		ModelAndView mav = new ModelAndView("scores/userscores");
		mav.addObject("empscoresAT", employeesAT);
		mav.addObject("totalAT", companyTotalAT);
		mav.addObject("empscoresWK", weeklyTotals2);
		mav.addObject("totalWK", companyTotalWK);
		mav.addObject("empscoresMN", monthlyTotals2);
		mav.addObject("totalMN", companyTotalMN);
		
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
