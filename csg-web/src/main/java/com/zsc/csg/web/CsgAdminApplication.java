package com.zsc.csg.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
@SpringBootApplication
@ImportResource(value={"classpath:config/application-context.xml"})
public class CsgAdminApplication {

	private static final Logger logger = LoggerFactory.getLogger(CsgAdminApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CsgAdminApplication.class, args);
		logger.info("--csg-admin启动成功--");
	}
}
