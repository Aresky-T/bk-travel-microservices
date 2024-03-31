package com.aresky.tourservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import com.aresky.tourservice.repository.SubTourRepository;
import com.aresky.tourservice.repository.TourRepository;

@EnableR2dbcRepositories(basePackageClasses = {
		TourRepository.class,
		SubTourRepository.class
})
@EnableDiscoveryClient
@SpringBootApplication
public class TourServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourServiceApplication.class, args);
	}

}
