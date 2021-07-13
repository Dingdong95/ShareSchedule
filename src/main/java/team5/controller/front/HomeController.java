package team5.controller.front;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import oracle.net.aso.e;
import team5.services.bean.UserBean;

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
	
	
	
	// 이때 bean의 variable 이름이랑 request로 넘어오는 parameter의 name이랑 같아야 자동으로 bean에 저장이됨.
	
	@PostMapping("/signIn")
	public String signIn(@ModelAttribute UserBean ub) {
		
		System.out.println( ub.getUCode() + "bean으로" +  ub.getUPassword());

		
		return "signIn";
	}
	
	@PostMapping("/signIn2")
	public String signIn2(@ModelAttribute UserBean ub) {
		System.out.println(ub.getUCode() + "이거 arrayList" + ub.getUPassword());
		System.out.println("이거 날짜" + ub.getDate());
		System.out.println(ub.getUInfo().get(0) + "이거 arrayList" + ub.getUInfo().get(1));
		return "signIn";
	}
	
	/*
	@PostMapping("/signIn2")
	public String signIn2(@RequestParam("Code")  ArrayList<String> code ) {
		
		System.out.println( code.get(0) + "arrayList로" + code.get(1));

		
		return "signIn";
	}
	*/
	
	
}
