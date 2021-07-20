package team5.services.auth;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team5.services.bean.AuthBean;
import team5.services.bean.UserBean;

//db연동되는 애들 구분하면서 singletone으로 올림 annotation 
@Repository
public class AuthDao {

	@Autowired
	SqlSessionTemplate sqlsession;
	
	String getEncryptedPW(AuthBean ab) {
		return sqlsession.selectOne("getEncryptedPW",ab);
	}
	
	 boolean isUcode(AuthBean ab) {
		return this.convertType(sqlsession.selectOne("isUcode",ab));
	}


	
	 boolean insMemberHistory(AuthBean ab) {
		return this.convertType(sqlsession.insert("insMemberHistory",ab));
	}

	
	 boolean insMember(UserBean ub) {
		return this.convertType(sqlsession.insert("insMember",ub));
			
	}
	 
	 ArrayList<UserBean> selMemberInfo(AuthBean ab){
		 return (ArrayList)sqlsession.selectList("selMemberInfo", ab);
	 }
	 
	 boolean selMemberHistory(AuthBean ab){
		 return this.convertType(sqlsession.selectOne("selMemberHistory",ab));
	 }
	 
	 boolean isAccess(AuthBean ab) {
		 return this.convertType(sqlsession.selectOne("isAccess",ab));
	 }
	
	private boolean convertType(int result) {
		return (result>0) ? true:false ;
	}

	boolean checkOtherLogs(AuthBean ab) {
		//int total = sqlsession.selectOne("checkOtherLogs", ab);
		
		return this.convertType(sqlsession.selectOne("checkOtherLogs", ab)) ;
		// TODO Auto-generated method stub
		
	}

	boolean checkBrowserExist(AuthBean ab) {
		boolean check = false;
		if((int)sqlsession.selectOne("checkBrowserExist",ab) > 0) {
			check = true;
		}
		return check;
	}
}
