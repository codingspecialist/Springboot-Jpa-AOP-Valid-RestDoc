package com.cos.bindingresult;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringBindingResultApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBindingResultApplication.class, args);
	}

}
