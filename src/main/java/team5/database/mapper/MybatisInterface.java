package team5.database.mapper;

import team5.services.bean.AuthBean;
import team5.services.bean.UserBean;

public interface MybatisInterface {
	//이게 mapper.xml 로 연결됨 
	//xml return값이 여기 저장됨 
	public int isUcode(AuthBean ab);
	public int isAccess(AuthBean ab);
	public int insMemberHistory(AuthBean ab);
	public int insMember(UserBean ub);
}
