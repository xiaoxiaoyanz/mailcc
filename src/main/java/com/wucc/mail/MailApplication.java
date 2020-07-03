package com.wucc.mail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableAsync  //开启异步注解功能
//@EnableScheduling //开启基于注解的定时任务
@ComponentScan(basePackages = {"com.wucc"})
@MapperScan({"com.wucc.dao"})
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class MailApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailApplication.class, args);
	}

}
