package co.grandcircus.CO2Competition.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.grandcircus.CO2Competition.ApiService;
import co.grandcircus.CO2Competition.COCalculator;
import co.grandcircus.CO2Competition.Entities.Distance;

@Controller
public class IndexController {
	
	@Autowired
	private ApiService apiServe;
	
	private COCalculator coCal;
	
	@RequestMapping("/")
	public ModelAndView showIndex() {
		ModelAndView mav = new ModelAndView("index");
		String address1 = "209HillStreetNorthvilleMI48167";
		String address2 = "DetroitMI";
		Distance distance = apiServe.getDistance(address1, address2);
		mav.addObject("start", apiServe.getStart(address1, address2));
		mav.addObject("dest", apiServe.getDest(address1, address2));
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
			@RequestParam String street1,
			@RequestParam String city1,
			@RequestParam String zip1,
			RedirectAttributes redir
			) {
		// This needs better error checking, this is just a starter
		boolean validStreet = !street.isEmpty() || street != null;
		boolean validCity = !city.isEmpty() || city != null;
		boolean validZip = !zip.isEmpty() || zip != null;
		if (!(validStreet && validCity && validZip)) {
			redir.addFlashAttribute("message", "Invalid address input, please try again.");
			return new ModelAndView("redirect:/logtrip");
		}
		ModelAndView mav = new ModelAndView("details");
		String address1 = street+city+zip;
		String address2 = street1+city1+zip1;
		Distance distance = apiServe.getDistance(address1, address2);
		if (distance!=null) {
	
		mav.addObject("street", street);
		mav.addObject("city", city);
		mav.addObject("zip", zip);
		mav.addObject("street1", street1);
		mav.addObject("city1", city1);
		mav.addObject("zip1", zip1);
		mav.addObject("distance", distance);
		mav.addObject("em", coCal.smallCar(distance.getValue() ));
		} else {
			mav.addObject("invalid", "No such address");
		}
		return mav;
		
	}
}
