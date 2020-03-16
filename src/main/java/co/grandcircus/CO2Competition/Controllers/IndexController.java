package co.grandcircus.CO2Competition.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping("/logtrip")
	public ModelAndView logTripForm() {
		return new ModelAndView("tripform");
	}
	
	@RequestMapping("/tripdetails")
	public ModelAndView showDetails(
			@RequestParam String street,
			@RequestParam String city,
			@RequestParam String zip,
			RedirectAttributes redir
			) {
		// This needs better error checking, this is just a starter
		boolean validStreet = !street.isBlank() || street != null;
		boolean validCity = !city.isBlank() || city != null;
		boolean validZip = !zip.isBlank() || zip != null;
		if (!(validStreet && validCity && validZip)) {
			redir.addFlashAttribute("message", "Invalid address input, please try again.");
			return new ModelAndView("redirect:/logtrip");
		}
		
		ModelAndView mav = new ModelAndView("details");
		mav.addObject("street", street);
		mav.addObject("city", city);
		mav.addObject("zip", zip);
		
		return mav;
		
	}
}
