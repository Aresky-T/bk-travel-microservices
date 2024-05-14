package com.aresky.chatservice;

import com.aresky.chatservice.repository.IConversationRepository;
import com.aresky.chatservice.repository.ICustomerRepository;
import com.aresky.chatservice.repository.IMessageRepository;
import com.aresky.chatservice.repository.IStaffRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories(basePackageClasses = {
		ICustomerRepository.class,
		IStaffRepository.class,
		IConversationRepository.class,
		IMessageRepository.class
})
@EnableDiscoveryClient
@SpringBootApplication
public class ChatServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatServiceApplication.class, args);
	}

}
