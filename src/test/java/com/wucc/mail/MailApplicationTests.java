package com.wucc.mail;

import com.wucc.entity.Message;
import com.wucc.entity.User;
import com.wucc.service.MessageService;
import com.wucc.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Calendar;
import java.util.Properties;


@SpringBootTest
class MailApplicationTests {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private Environment environment;
	@Autowired
	private MessageService messageService;
	@Autowired
	private UserService userService;
	@Test
	void contextLoads() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("wu15922897725@sina.com");
		message.setTo("1528956427@qq.com"); //自己给自己发送邮件
		message.setSubject("主题：简单邮件");
		message.setText("测试邮件内容");
		javaMailSender.send(message);
	}
	@Test
	public void test01() throws MessagingException {
		// 1.创建一个程序与邮件服务器会话对象 Session
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "SMTP");// 连接协议
		props.setProperty("mail.smtp.host", "smtp.sina.com");// 连接协议
		props.setProperty("mail.smtp.port", "25");// 连接协议
		// 指定验证为true
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.timeout","1000");
		// 验证账号及密码，密码需要是第三方授权码
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication("wu15922897725@sina.com", "8004783667fc3861");
			}
		};
		Session session = Session.getInstance(props, auth);

		// 2.创建一个Message，它相当于是邮件内容
		MimeMessage message = new MimeMessage(session);
		message.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");
		// 设置发送者
		message.setFrom(new InternetAddress("wu15922897725@sina.com"));
		// 设置发送方式与接收者
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("1528956427@qq.com"));
		// 设置主题
		message.setSubject("测试邮件");

		//创建消息主体
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText("这是一份简单的邮件");
		// 创建多重消息
		Multipart multipart=new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		// 设置邮件消息发送的时间
		message.setSentDate(Calendar.getInstance().getTime());
		// 设置内容
		message.setContent(multipart, "text/html;charset=utf-8");

		// 3.创建 Transport用于将邮件发送
		Transport.send(message);

	}

	@Test
	public void test02(){
		System.out.println(environment.getProperty("spring.mail.password"));
	}

	@Test
	public void test03(){
		User user = userService.queryById("1");
		System.out.println(user);
		//System.out.println(environment.getProperty("spring.mail.password"));
		Message message = messageService.queryById("1");
		System.out.println(message);
	}

}
