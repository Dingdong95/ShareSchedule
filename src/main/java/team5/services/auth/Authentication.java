package team5.services.auth;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import team5.services.bean.AuthBean;
import team5.services.bean.UserBean;

// 여기서 access modifier 때문에 private method들을 사용하지 못하기때문에 dao를 생성 

@Service
public class Authentication {
	@Autowired
	AuthDao dao;
	
	@Autowired
	Gson gson;
	//private ModelAndView mav = null;
	
	
	//signIn check ctl
	public ModelAndView isAccessCtl(AuthBean ab) {
		boolean check = false;
		ModelAndView mav = new ModelAndView();
		
		if(check = dao.isUcode(ab)) {
			if(check = dao.isAccess(ab)) {
				if(check = dao.insMemberHistory(ab)) {
					//ArrayList<UserBean> list = (ArrayList)dao.selMemberInfo(ab);
					mav.setViewName("dashBoard");
					mav.addObject("umail", (dao.selMemberInfo(ab).get(0).getUMail()));
				}
				}
			}
		if(!check) {
			mav.setViewName("signIn");
			mav.addObject("message","정보확인하세요");
		}
		return mav;
		}
		
		
	
	

	//join member control 
	//중복검사를 한 시점과 실제로 회원가입을 하는 시점이 다르기 때문에 여기서 사실상 한번 더 해줘야함
	// 실제 프로젝트시에는 중복검사 하는순간 가상의 아이디를 만들어주고 회원가입 실패시 
	// 중복 검사한 아이디를 지워주는 방식으로 해야함 
	public ModelAndView insMemberCtl(UserBean ub) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("signUp");
		mav.addObject("message","잠시 후 다시 시도해 주세요");
		
		if(dao.insMember(ub)) {
			mav.setViewName("signIn");
			mav.addObject("message","회원가입 성공. 다시 로그인해주세요");
		}
		
		
		return mav;
	}
	
	public String isdupId(AuthBean ab) {
		boolean message = false;
		if(!dao.isUcode(ab)) {
			message = true;
		}
		//json 형식 {"message:", "true"}
		return gson.toJson(message);
	}
}
