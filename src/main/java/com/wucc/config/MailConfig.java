package com.wucc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * <p>
 *
 * <p>
 *
 * @author wudingjia
 * @date 2020-06-30 15:59
 */
//@Configuration
public class MailConfig {

	@Bean
	public JavaMailSenderImpl javaMailSender(){
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost("smtp.sina.com");
		javaMailSender.setUsername("wu15922897725@sina.com");
		javaMailSender.setPassword("8004783667fc3861");
		javaMailSender.setDefaultEncoding("UTF-8");
		Properties properties = new Properties();
		properties.put("mail.smtp.starttls.enable",true);
		javaMailSender.setJavaMailProperties(properties);
		return  javaMailSender;
	}
}
