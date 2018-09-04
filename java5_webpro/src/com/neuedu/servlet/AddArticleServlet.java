package com.neuedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.bean.Articleinfo;
import com.neuedu.bean.Userinfo;
import com.neuedu.service.ArticleService;
import com.neuedu.service.UserinfoService;
import com.neuedu.service.impl.ArticleServiceImpl;
import com.neuedu.service.impl.UserinfoServiceImpl;

/**
 * Servlet implementation class AddArticleServlet
 */
//@WebServlet("/article/add")
public class AddArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object user = request.getSession().getAttribute("login_user");
		if(user == null) {
			// û��¼�ȵ�¼
			response.sendRedirect(request.getContextPath()+"/jsp/user/login.jsp");
		}else {
			// ��ת�������µ�jsp
			response.sendRedirect(request.getContextPath()+"/jsp/jie/add.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ���±���
		String title = request.getParameter("title");
		// ��������
		String content = request.getParameter("content");
		// ��������
		String type = request.getParameter("class");
		// ���ĵķ���
		String paykiss = request.getParameter("experience");
		// ������id
		int userid = ((Userinfo)request.getSession().getAttribute("login_user")).getId();
		// ��װ�ɶ���
		Articleinfo al = new Articleinfo();
		al.setTitle(title);
//		al.setContent(content);
		al.setType(Integer.valueOf(type));
		al.setPaykiss(Integer.valueOf(paykiss));
		al.setUserid(userid);
		// ���µ�ǰ��¼��kissnum
		Userinfo user = ((Userinfo)request.getSession().getAttribute("login_user"));
		user.setKissnum(user.getKissnum()-Integer.valueOf(paykiss));
		// ��service:1.�����±�������һƪ���£�2.������userinfo�ķ�����
		ArticleService as = new ArticleServiceImpl();
		int count = as.addArticle(al);
		UserinfoService us = new UserinfoServiceImpl();
		us.updateUserKiss(user);
		response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
	}

}
