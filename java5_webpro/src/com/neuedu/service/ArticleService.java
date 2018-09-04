package com.neuedu.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.neuedu.bean.Artcletype;
import com.neuedu.bean.Articleinfo;
import com.neuedu.bean.Page;

public interface ArticleService {
	// �����������ӷ���
	List<Artcletype> getArtType() throws IOException;
	// �������
	int addArticle(Articleinfo al) throws IOException;
	// ��ѯ��ҳ10ƪ����
	List<Map<String, Object>> getAllArticle() throws IOException;
	// ��ѯ�ö���Ӿ�����4��
	List<Map<String, Object>> getTopArticle(int num) throws IOException;
	// ��ѯ��ҳ��10��������
	List<Map<String, Object>> getIndexTopTen(Page page) throws IOException;
	// ��ѯ��������
	int getArtTotalNum() throws IOException;
	// �鿴ıƪ����
	Map<String, Object> getArtDetail(int id) throws IOException;
}
