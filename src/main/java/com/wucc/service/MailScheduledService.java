package com.wucc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

/**
 * <p>
 *
 * <p>
 *
 * @author wudingjia
 * @date 2020-06-30 17:05
 */
@Service
public class MailScheduledService {

	@Autowired
	private Environment env;

	/**
	 * second(秒), minute（分）, hour（时）, day of month（日）, month（月）, day of week（周几）.
	 * 0 * * * * MON-FRI
	 *  【0 0/5 14,18 * * ?】 每天14点整，和18点整，每隔5分钟执行一次
	 *  【0 15 10 ? * 1-6】 每个月的周一至周六10:15分执行一次
	 *  【0 0 2 ? * 6L】每个月的最后一个周六凌晨2点执行一次
	 *  【0 0 2 LW * ?】每个月的最后一个工作日凌晨2点执行一次
	 *  【0 0 2-4 ? * 1#1】每个月的第一个周一凌晨2点到4点期间，每个整点都执行一次；
	 */
	// @Scheduled(cron = "0 * * * * MON-SAT")
	//@Scheduled(cron = "0,1,2,3,4 * * * * MON-SAT")
	// @Scheduled(cron = "0-4 * * * * MON-SAT")
	//@Scheduled(cron = "0/4 * * * * *")  //每4秒执行一次
	//@Scheduled(fixedRate=5000)
	//@Scheduled(fixedRate = 2000)
	public void hello(){
		System.out.println(String.format("当前时间：%s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
	}

	//@Scheduled(cron = "0 0/5 * * * *")
	@Scheduled(cron = "${spring.mail.scheduledc.con}")
	public void  sendmail() throws MessagingException {
		System.out.println("开始发送消息");
		// 1.创建一个程序与邮件服务器会话对象 Session
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "SMTP");// 连接协议
		props.setProperty("mail.smtp.host", env.getProperty("spring.mail.host"));// 连接协议
		props.setProperty("mail.smtp.port", "25");// 连接协议
		// 指定验证为true
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.timeout",env.getProperty("spring.mail.smtp.timeout"));
		// 验证账号及密码，密码需要是第三方授权码
		Authenticator auth = new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(env.getProperty("spring.mail.username"), env.getProperty("spring.mail.password"));
			}
		};
		Session session = Session.getInstance(props, auth);

		// 2.创建一个Message，它相当于是邮件内容
		MimeMessage message = new MimeMessage(session);
		message.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");
		// 设置发送者
		message.setFrom(new InternetAddress(env.getProperty("spring.mail.username")));
		// 设置发送方式与接收者
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("1055141339@qq.com"));
		// 设置主题
		message.setSubject("测试邮件");
		//创建消息主体
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText("老王，每隔5分钟给你发个邮件");
		// 创建多重消息
		Multipart multipart=new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		// 设置邮件消息发送的时间
		message.setSentDate(Calendar.getInstance().getTime());
		// 设置内容
		message.setContent(multipart, "text/html;charset=utf-8");

		// 3.创建 Transport用于将邮件发送
		Transport.send(message);

		System.out.println("发送成功");
	}
}
