package com.neuedu.service;

import java.io.IOException;

import com.neuedu.bean.Userinfo;

public interface UserinfoService {
	// ��ѯ�����Ƿ����
	int checkEmailExists(String email) throws IOException;
	// ������û�
	int addNewUserinfo(Userinfo userinfo) throws IOException;
	// ��ѯ��¼�û��Ƿ����
	Userinfo checkLoginUser(Userinfo userinfo) throws IOException;
	// ���·�����
	int updateUserKiss(Userinfo userinfo) throws IOException;
	// ����ͷ��
	int updateHeadUrl(Userinfo userinfo) throws IOException;
}
