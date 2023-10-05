package com.mm.messaging;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MessagingApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(MessagingApplication.class).web(WebApplicationType.NONE).build().run(args);
	}

}
