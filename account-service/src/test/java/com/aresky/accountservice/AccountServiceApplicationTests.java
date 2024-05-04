package com.aresky.accountservice;

import com.aresky.accountservice.grpc.account.CreateAccountRequest;
import com.aresky.accountservice.grpc.account.CreateAccountResponse;
import com.aresky.accountservice.grpc.account.ReactorAccountServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

@SpringBootTest
class AccountServiceApplicationTests {
	private static ReactorAccountServiceGrpc.ReactorAccountServiceStub accountServiceStub;

	@BeforeAll
	public static void setup(){
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
		accountServiceStub = ReactorAccountServiceGrpc.newReactorStub(channel);
	}

	@Test
	public void createAccount() {
		System.out.println("Send request creating account");
		CreateAccountRequest request = CreateAccountRequest.newBuilder()
				.setUsername("aresky")
				.setEmail("aresky@gmail.com")
				.setPassword("110520")
				.build();

		accountServiceStub.createAccount(Mono.just(request))
				.map(CreateAccountResponse::getMessage)
				.map(System.out::printf);
	}
}
