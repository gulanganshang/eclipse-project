package com.neuedu.service;

import java.io.IOException;

import com.neuedu.bean.Commentinfo;

public interface CommentService {

	// ���һ������
	int addComment(Commentinfo comm) throws IOException;
}
