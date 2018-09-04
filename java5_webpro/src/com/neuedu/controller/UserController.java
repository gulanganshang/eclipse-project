package com.neuedu.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.neuedu.bean.Userinfo;
import com.neuedu.service.UserinfoService;
import com.neuedu.service.impl.UserinfoServiceImpl;
import com.neuedu.util.MD5;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserinfoService us;

	@RequestMapping("/checkreg")
	public void checkReg(String email, HttpServletResponse response) throws IOException {
		// ����service��ѯ�������Ƿ����������(servlet-->mapper servlet-->service-->mapper )
		int count = us.checkEmailExists(email);
		response.getWriter().println(count);
	}

	@RequestMapping("/reg")
	public String reg(Userinfo userinfo) throws IOException {
		// �������
		userinfo.setPassword(MD5.MD5(userinfo.getPassword()));
		// ���ö�Ӧservice����ɲ������û�
		int count = us.addNewUserinfo(userinfo);
		if (count == 1) {
			return "redirect:/article/loadindex";
		} else {
			return "redirect:/page/loadreg";
		}
	}

	@RequestMapping("/login")
	public String dologin(Userinfo userinfo, HttpServletRequest request) throws IOException {
		userinfo.setPassword(MD5.MD5(userinfo.getPassword()));
		// ��service��ѯ�Ƿ���Ե�¼
		Userinfo user = us.checkLoginUser(userinfo);
		if (user != null) {
			// ���Ե�¼
			request.getSession().setAttribute("login_user", user);
			return "redirect:/article/loadindex";
		} else {
			request.setAttribute("msg", "�û������������");
			return "user/login";
		}
	}

	@RequestMapping("/headupload")
	public void uploadHead(MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {
		// ��ȡ�ļ���
		String fileName = file.getOriginalFilename();
		// ��ȡ�ϴ�·��
		String uploadpath = request.getServletContext().getRealPath("/upload/");
		File files = new File(uploadpath);
		// ���ϴ�·�������ڣ��½�·��
		if (!files.exists()) {
			files.mkdirs();
		}
		
		// �ϴ��ļ����յ�·��
		String newName = UUID.randomUUID() + fileName;
		String finalName = uploadpath + newName;
		File finalfile = new File(finalName);
		// �ϴ���ָ��·��
		file.transferTo(finalfile);
		// 1.��ǰ̨ҳ���ͷ��·��
		response.getWriter().println(request.getContextPath()+"/upload/"+newName);
		// 2.��session��login_user��ͷ�����һ��
		Userinfo userinfo = (Userinfo) request.getSession().getAttribute("login_user");
		userinfo.setHead_url(newName);
		// 3.�޸�userinfo���¼����Ϣ���е�head_url
		us.updateHeadUrl(userinfo);
	}
	
	@RequestMapping("/logout")
	public String logOut(HttpSession session) {
		session.invalidate();
		return "redirect:/article/loadindex";
	}
}
