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
	private ModelAndView mav = null;
	
	//signIn check ctl
	public ModelAndView isAccessCtl(AuthBean ab) {
		
		if(dao.isUcode(ab)) {
			if(dao.isAccess(ab)) {
				System.out.println("isaccess통과");
				if(dao.insMemberHistory(ab)) {
					mav = new ModelAndView();
					ArrayList<UserBean> list = (ArrayList)dao.selMemberInfo(ab);
					System.out.println(list.get(0).getEmail());
				}
			}
		}
		
		return mav;
	}
	

	//join member control 
	public ModelAndView insMemberCtl(UserBean ub) {
		return mav;
	}
	
	public boolean isdupId(AuthBean ab) {
		return false;
	}
}
