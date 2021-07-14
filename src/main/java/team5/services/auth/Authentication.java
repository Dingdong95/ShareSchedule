package team5.services.auth;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import team5.services.bean.AuthBean;
import team5.services.bean.UserBean;

// 여기서 access modifier 때문에 private method들을 사용하지 못하기때문에 dao를 생성 

@Service
public class Authentication {
	@Autowired
	AuthDao dao;
	
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
	public ModelAndView insMemberCtl(UserBean ub) {
		ModelAndView mav = new ModelAndView();
		if(dao.insMember(ub)) {
			mav.setViewName("signUp");
			mav.addObject("message","회원가입 성공");
		}
		
		
		return mav;
	}
	
	public boolean isdupId(AuthBean ab) {
		return false;
	}
}
