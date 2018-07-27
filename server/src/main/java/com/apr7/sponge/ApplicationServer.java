package com.apr7.sponge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({ "classpath:config/spring-context.xml" })
public class ApplicationServer {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApplicationServer.class, args);
	}

}
