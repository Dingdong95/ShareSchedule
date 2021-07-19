package team5.database.mapper;

import java.util.List;

import team5.services.bean.AuthBean;
import team5.services.bean.UserBean;

public interface MybatisInterface {
	//이게 mapper.xml 로 연결됨 
		//xml return값이 여기 저장됨
		//insert update delete 같은 경우는 int data type으로만 return이 되기 때문에 void로 설정 
	//java에서 overloading을 사용할수있었지만 이게 xml이랑 연결되었기 때문에 xml의 id중복이 되어버려서 여기서 overloading사용불가능 
		 String getEncryptedPW(AuthBean ab);
		 boolean isUcode(AuthBean ab);
		 void insMemberHistory(AuthBean ab);
		 void insMember(UserBean ub);
		 List<UserBean>selMemberInfo(UserBean ub);
		 int selMemberHistory(AuthBean ab);
		 boolean isAccess(AuthBean ab);
		 
}
