package com.neuedu.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.websocket.Session;

import com.neuedu.bean.Userinfo;
import com.neuedu.service.UserinfoService;
import com.neuedu.service.UserinfoServiceImpl;

/**
 * Servlet implementation class HeadUploadServlet
 */
@WebServlet("/user/headupload")
@MultipartConfig
public class HeadUploadServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String savePath = request.getServletContext().getRealPath("/headphoto");
		File file = new File(savePath);
		if(!file.exists()) {
			file.mkdirs();
		}
		PrintWriter out = response.getWriter();
		// ��ȡ�ϴ����ļ�����
		Collection<Part> parts = request.getParts();	
			for(Part part:parts) {
				if(part.getContentType() != null) {
					String header = part.getHeader("content-disposition");
					// ��ȡ�ļ���
					String fileName = getFileName(header);
					// �洢
					// ʱ�������ļ�����
//					Date date = new Date();
					UUID uuid = UUID.randomUUID();
					part.write(savePath + File.separator + uuid+fileName);
					// 1.��ǰ̨ҳ���ͷ��·��
					out.println(request.getContextPath()+"/headphoto/"+uuid+fileName);
					System.out.println(request.getContextPath()+"/headphoto/"+uuid+fileName);
					// 2.��session��login_user��ͷ�����һ��
					// 3.�޸�userinfo���¼����Ϣ���е�head_url
					
					Userinfo user=(Userinfo)request.getSession().getAttribute("login_user");
					user.setHead_url(uuid+fileName);
                    
					Userinfo userinfo=new Userinfo();
					userinfo.setId(user.getId());
					userinfo.setHead_url(uuid+fileName);
					
					UserinfoService us=new UserinfoServiceImpl();
					int count=us.updateHead_url(userinfo);
					System.out.println(count);
				}
			}
		out.flush();
		out.close();
	}
	public String getFileName(String header) {
		/**
		 * String[] tempArr1 = header.split(";");����ִ����֮���ڲ�ͬ��������£�tempArr1���������������������
		 * �������google������£�tempArr1={form-data,name="file",filename="snmp4j--api.zip"}
		 * IE������£�tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"}
		 */
		String[] tempArr1 = header.split(";");
		for(String str:tempArr1) {
			System.out.println(str);
		}
		/**
		 * �������google������£�tempArr2={filename,"snmp4j--api.zip"}
		 * IE������£�tempArr2={filename,"E:\snmp4j--api.zip"}
		 */
		String[] tempArr2 = tempArr1[2].split("=");
		// ��ȡ�ļ��������ݸ����������д��
		String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
		return fileName;
	}

	}


