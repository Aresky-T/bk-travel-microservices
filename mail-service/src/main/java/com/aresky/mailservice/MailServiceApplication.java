package com.aresky.mailservice;

import com.aresky.mailservice.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories(basePackageClasses = {
		ICustomerRepository.class,
		IStaffRepository.class,
		IMailBoxRepository.class,
		IMailRepository.class,
		IMailReplyRepository.class
})
@EnableDiscoveryClient
@SpringBootApplication
public class MailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailServiceApplication.class, args);
	}

}
