package com.aresky.accountservice.delivery.grpc;

import com.aresky.accountservice.interceptors.AccountGrpcInterceptor;
import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

public class AccountGrpcServer {

    private static final Logger log = LoggerFactory.getLogger(AccountGrpcServer.class);
    private static final int POST = 9082;

    @Autowired
    private ReactorAccountServiceGrpcImp reactorAccountServiceGrpc;

    private Server server;

    @PostConstruct
    public void setup() {
        this.start();
    }

    public void start() {
        try {
            this.server = ServerBuilder.forPort(POST)
                    .addService(ServerInterceptors.intercept(reactorAccountServiceGrpc, new AccountGrpcInterceptor()))
                    .build()
                    .start();

            System.out.println("GrpcServer started, listening on port " + POST);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            log.error("Error while starting account grpc server: {}", e.getMessage());
        }
    }

    public void stop() throws InterruptedException {
        if (!server.isShutdown()) {
            this.server.shutdownNow().awaitTermination(30, TimeUnit.MILLISECONDS);
        }
    }

    public AccountGrpcServer() {

    }
}
