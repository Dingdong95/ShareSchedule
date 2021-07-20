package team5.controller.front;


import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import team5.services.auth.Authentication;
import team5.services.bean.AuthBean;
import team5.services.bean.UserBean;
import team5.services.util.Encryption;
import team5.services.util.ProjectUtility;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	ModelAndView mav = new ModelAndView();
	@Autowired
	Authentication auth;
	@Autowired
	ProjectUtility pu;
	@Autowired
	Encryption enc;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView signInForm(Locale locale, Model model) {

		return auth.signInForm();
	}
	
	// 이때 bean의 variable 이름이랑 request로 넘어오는 parameter의 name이랑 같아야 자동으로 bean에 저장이됨.
	
	
	@PostMapping("/signIn")
	public ModelAndView signIn(HttpServletRequest req, @ModelAttribute AuthBean ab) {
		
		return auth.signInCtl(req,ab);
	}	
	
	@PostMapping("/signOut")
	public ModelAndView signOut(HttpServletRequest req, @ModelAttribute AuthBean ab) {
		
		return auth.signOutCtl(req,ab);
	}
	
	//@RequestMapping(value = "/signUp", method = RequestMethod.GET)
		@GetMapping("/signUpForm") //가입페이지 로딩 
		public String signUpForm(@ModelAttribute UserBean ub) {
			return "signUp";
		}
	
		
		@PostMapping("/signUp")
		public ModelAndView signUp(@ModelAttribute UserBean ub) {
			return auth.insMemberCtl(ub);
		}
		
		//가입페이지에서 가입을 눌렀을때 하는 요청 
		
		@PostMapping("/dupCheck")
		//ajax로 요청해서 ajax로 받을때 body의 일부분만 요청에 응답을 하겠다 는 annotation. 이걸 기본으로 하고 다른 기능을 강화한게 spring boot. spring boot는 api가 기본 
		@ResponseBody
		public String dupCheck(@ModelAttribute AuthBean ab) {
			String isAble = null;
			isAble = auth.isdupId(ab);
			return isAble;
		}
		
		
		
}
