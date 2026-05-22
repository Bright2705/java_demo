package com.example.javaDemo;

import com.example.javaDemo.service.NotificationManager;
import com.example.javaDemo.service.UserSevice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JavaDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context =
				SpringApplication.run(
						JavaDemoApplication.class,
						args
				);
	}

}
