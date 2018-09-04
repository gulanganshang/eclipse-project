package com.neuedu.mapper;

import java.io.IOException;

import com.neuedu.bean.Userinfo;

public interface UserinfoMapper {
	// ��ѯ�����Ƿ����
	int checkEmailExists(String email);
	// ������û�
	int addNewUserinfo(Userinfo userinfo);
	// ��ѯ��¼�û��Ƿ����
	Userinfo checkLoginUser(Userinfo userinfo);
	// ���·�����
	int updateUserKiss(Userinfo userinfo);
	// ����ͷ��
	int updateHeadUrl(Userinfo userinfo) throws IOException;
}
