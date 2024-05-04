package com.aresky.tourservice.grpc;

import com.google.common.annotations.VisibleForTesting;

import io.grpc.ForwardingServerCall;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import lombok.extern.slf4j.Slf4j;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

@Slf4j
public class TourGrpcInterceptor implements ServerInterceptor {

    @VisibleForTesting
    public static final Metadata.Key<String> CUSTOM_KEY = Metadata.Key.of("GRPC_SERVER_KEY",
            Metadata.ASCII_STRING_MARSHALLER);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers,
            ServerCallHandler<ReqT, RespT> next) {
        log.info("service: {}, method: {}, headers: {}", call.getMethodDescriptor().getServiceName(),
                call.getMethodDescriptor().getFullMethodName(), headers.toString());
        return next.startCall(new ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(call) {
            @Override
            public void sendHeaders(Metadata headers) {
                headers.put(CUSTOM_KEY, "TOUR-SERVICE-GRPC-SERVER-RESPONSE-VALUE");
                super.sendHeaders(headers);
            }
        }, headers);
    }
}
