package com.neuedu.mapper;

import com.neuedu.bean.Commentinfo;

public interface CommentMapper {
	// ���һ������
	int addComment(Commentinfo comm);
	// ���¶�Ӧ���µ�������
	int updateCommNum(Commentinfo comm);
}
