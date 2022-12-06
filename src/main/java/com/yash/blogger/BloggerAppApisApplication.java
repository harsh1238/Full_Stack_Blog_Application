package com.yash.blogger;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BloggerAppApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggerAppApisApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelmaper()
	{
		return new ModelMapper();
	}

}
