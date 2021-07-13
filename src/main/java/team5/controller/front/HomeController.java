package team5.controller.front;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import oracle.net.aso.e;
import team5.services.auth.Authentication;
import team5.services.bean.AuthBean;
import team5.services.bean.UserBean;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	ModelAndView mav;
	@Autowired
	Authentication auth;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = {RequestMethod.GET,RequestMethod.POST})
	public String signInForm(Locale locale, Model model) {
		
		return "signIn";
	}
	
	// 이때 bean의 variable 이름이랑 request로 넘어오는 parameter의 name이랑 같아야 자동으로 bean에 저장이됨.
	
	@PostMapping("/signIn")
	public ModelAndView signIn(@ModelAttribute AuthBean ab) {
		auth.isAccessCtl(ab);
		return null;
	}	
	
	
	//@RequestMapping(value = "/signUp", method = RequestMethod.GET)
		@PostMapping("/signUp")
		public ModelAndView signUp(@ModelAttribute UserBean ub) {
			return mav = auth.insMemberCtl(ub);
		}
	
		@PostMapping("/dupCheck")
		public boolean dupCheck(@ModelAttribute AuthBean ab) {
			boolean isAble = false;
			isAble = auth.isdupId(ab);
			return isAble;
		}
		
}
