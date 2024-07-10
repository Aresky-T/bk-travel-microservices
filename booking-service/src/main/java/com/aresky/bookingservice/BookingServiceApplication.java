package com.aresky.bookingservice;

import com.aresky.bookingservice.repository.BookingRepository;
import com.aresky.bookingservice.repository.BookingStatisticRepository;
import com.aresky.bookingservice.repository.CancellationRequestedRepository;
import com.aresky.bookingservice.repository.TouristRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories(basePackageClasses = {
		BookingRepository.class,
		TouristRepository.class,
		CancellationRequestedRepository.class,
		BookingStatisticRepository.class,
})
@EnableDiscoveryClient
@SpringBootApplication
public class BookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingServiceApplication.class, args);
	}

}
