<<<<<<< HEAD
package com.neuedu.mapper;

import com.neuedu.bean.Userinfo;

public interface UserinfoMapper {
	int checkEmailExists(String email);
	int addNewUserinfo(Userinfo userinfo);
	Userinfo checkLoginUserinfo(Userinfo userinfo);
	int updateUserinfo(Userinfo userinfo);
	
	int updateHead_url(Userinfo userinfo);
}
=======
package com.neuedu.mapper;

import com.neuedu.bean.Userinfo;

public interface UserinfoMapper {
	int checkEmailExists(String email);
	int addNewUserinfo(Userinfo userinfo);
	Userinfo checkLoginUserinfo(Userinfo userinfo);
	int updateUserinfo(Userinfo userinfo);
	
	int updateHead_url(Userinfo userinfo);
}
>>>>>>> branch 'master' of https://github.com/gulanganshang/fly-.git
