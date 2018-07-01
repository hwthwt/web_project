package com.itany.test;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itany.mvc.annotation.RequestMapping;

@RequestMapping("/mail")
public class MailController {

	@RequestMapping("/sendMail")
	public String sendMail(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
		Properties p = new Properties();
		p.load(MailController.class.getClassLoader()
				.getResourceAsStream("mail.properties"));
		
		Session session = Session.getInstance(p);
		
		MimeMessage message = new MimeMessage(session);
		
		String to = request.getParameter("email");
		
		// TO:收件人  、CC 抄送 、BCC 密送
		message.addRecipient(RecipientType.TO, new InternetAddress(to, "TEACHER", "utf-8"));
		
		// 发送人
		message.setFrom(new InternetAddress("teacher@itany.com", "TEACHER", "utf-8"));
		
		// 设置邮件主题/标题
		message.setSubject("密码重置-来自SHOP", "utf-8");
		
		long curTime = System.currentTimeMillis();
		
		ServletContext application = request.getSession().getServletContext();
		
		String uuid= UUID.randomUUID().toString();
			
		application.setAttribute(uuid, curTime);
		
		// 设置邮件正文
		message.setContent("<a href='http://localhost:8080/test/mail/resetPwd.do?to="+to+"&u="+uuid+"'>点击重置密码</a><font color='red'>该链接半小时内有效</font> ", "text/html;charset=utf8");
		
		message.saveChanges();
		
		Transport trans = session.getTransport();
		
		// user 写的是登录账户
		trans.connect("teacher@itany.com", "00000000A");
		trans.sendMessage(message, message.getAllRecipients());
		trans.close();
		
		//依赖的jar
//		<dependency>
//	    	<version>1.4.7</version>
//			<groupId>javax.mail</groupId>
//			<artifactId>mail</artifactId>
//	    </dependency>
		
		return "success";
	}
	
	@RequestMapping("/resetPwd")
	public String resetPwd(HttpServletRequest request,HttpServletResponse response)
	{
		String to = request.getParameter("to");
		long curTime = System.currentTimeMillis();
		
		String u = request.getParameter("u");
		long createTime = (long) request.getServletContext()
				.getAttribute(u);
		
		if(curTime - createTime > 5000)
		{
			return "redirect:error";
		}
		request.setAttribute("to", to);
		return "resetPwd";
	}
	
	@RequestMapping("/reset")
	public String reset(HttpServletRequest request,HttpServletResponse response)
	{
		String newPwd = request.getParameter("pwd");
		String to = request.getParameter("to");
		System.out.println("update t_user set pwd = '" + newPwd + "' where email='" + to + "'");
		return "success";
	}
	
}
