package team5.services.auth;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import team5.database.mapper.MybatisInterface;
import team5.services.bean.AuthBean;
import team5.services.bean.UserBean;

@Service
public class Authentication implements MybatisInterface {
	private ModelAndView mav;
	
	//signIn check ctl
	public ModelAndView isAccessCtl(AuthBean ab) {
		
		if(this.isUcode(ab)) {
			this.isAccess(ab);
		}
		
		return mav;
	}
	

	//join member control 
	public ModelAndView insMemberCtl(UserBean ub) {
		
		this.insMember(ub);
		
		return mav;
	}
	
	//signIn check 
	private boolean isAccess(AuthBean ab) {
		
		return false;
	}
	
	
	//join member info 
	private boolean insMember(UserBean ub) {
		return false;
	}
	
	
	//checks if id exist in db 
	private boolean isUcode(AuthBean ab) {

		
		return false;
		
	}
	
	public boolean isdupId(AuthBean ab) {
		return false;
	}
	
	private void insMemberHistory(AuthBean ab) {
		return false;
	}
	
	private boolean convertType(int result) {
		return (result>0) ? true:false ;
	}
	
}
