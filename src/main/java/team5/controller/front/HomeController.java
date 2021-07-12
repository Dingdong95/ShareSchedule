package team5.controller.front;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = {RequestMethod.GET,RequestMethod.POST})
	public String signInForm(Locale locale, Model model) {
		
		return "signIn";
	}
	
	
	//@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	@GetMapping("/signUp")
	public String signUp(Locale locale, Model model) {
		
		return "signUp";
	}
	
	@PostMapping("/signIn")
	public String signIn(@RequestParam("uCode") String uCode, @RequestParam("uPassword") String uPassword ) {
		
		System.out.println(uCode + "servlet까지 넘어옴" + uPassword);

		
		return "signIn";
	}
	
	
}
