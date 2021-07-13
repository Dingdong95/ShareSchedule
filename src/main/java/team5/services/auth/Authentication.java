package team5.services.auth;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import team5.services.bean.AuthBean;
import team5.services.bean.UserBean;

@Service
public class Authentication {
	
	//signIn check ctl
	public ModelAndView isAccessCtl(AuthBean ab) {
		
		if(this.isAccess(ab)) {
			
		};
		
		return null;
	}
	
	//signIn check 
	private boolean isAccess(AuthBean ab) {
		
		return false;
	}
	
	//join member control 
	public ModelAndView insMemberCtl(UserBean ub) {
		
		this.insMember(ub);
		
		return null;
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
	
	private boolean convertType(int result) {
		return (result==1) ? true:false ;
	}
	
}
