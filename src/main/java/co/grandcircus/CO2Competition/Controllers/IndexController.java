package co.grandcircus.CO2Competition.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
	@Autowired
	HttpSession httpSesh;

	@RequestMapping("/")
	public ModelAndView showIndex() {
		return new ModelAndView("index");
	}
	
	@RequestMapping("/employee")
	public ModelAndView showDesk() {
		return new ModelAndView ("employee-page");
	}

}
