package co.grandcircus.CO2Competition.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.CO2Competition.ApiService;
import co.grandcircus.CO2Competition.Entities.Distance;

@Controller
public class IndexController {
	
	@Autowired
	private ApiService apiServe;
	
	
	@RequestMapping("/")
	public ModelAndView showIndex() {
		ModelAndView mav = new ModelAndView("index");
		Distance distance = apiServe.getDistance();
		mav.addObject("start", apiServe.getStart());
		mav.addObject("dest", apiServe.getDest());
		mav.addObject("text", distance.getText());
		mav.addObject("value", distance.getValue());
		return mav;
		//return new ModelAndView("index");
	}
}
