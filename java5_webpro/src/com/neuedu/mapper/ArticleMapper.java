package com.neuedu.mapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.neuedu.bean.Artcletype;
import com.neuedu.bean.Articleinfo;
import com.neuedu.bean.Page;

public interface ArticleMapper {
	// �����������ӷ���
	List<Artcletype> getArtType();
	// �������
	int addArticle(Articleinfo al);
	// ��ѯ��������
	List<Map<String, Object>> getAllArticle();
	// ��ѯ�ö���Ӿ�����2��
	List<Map<String, Object>> getTopArticle(int num) throws IOException;
	// ��ѯ��ҳ��10��������
	List<Map<String, Object>> getIndexTopTen(Page page) throws IOException;
	// ��ѯ��������
	int getArtTotalNum() throws IOException;
	// �鿴ıƪ����
	Map<String, Object> getArtDetail(int id) throws IOException;
	// ���·�����
	int updateVisitNum(int id);
}
