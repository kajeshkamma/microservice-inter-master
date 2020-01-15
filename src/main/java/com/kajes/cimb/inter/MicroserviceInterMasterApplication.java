package com.kajes.cimb.inter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MicroserviceInterMasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceInterMasterApplication.class, args);
	}
	
	@Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
