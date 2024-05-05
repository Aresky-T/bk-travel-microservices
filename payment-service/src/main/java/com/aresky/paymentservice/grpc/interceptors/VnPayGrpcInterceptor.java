package com.aresky.paymentservice.grpc.interceptors;

import com.google.common.annotations.VisibleForTesting;
import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VnPayGrpcInterceptor implements ServerInterceptor {

    @VisibleForTesting
    public static final Metadata.Key<String> CUSTOM_KEY = Metadata.Key.of("GRPC_SERVER_KEY", Metadata.ASCII_STRING_MARSHALLER);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        log.info("Service: {}, method: {}, headers: {}", call.getMethodDescriptor().getServiceName(), call.getMethodDescriptor().getBareMethodName(), headers.toString());
        return next.startCall(new ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(call) {
            @Override
            public void sendHeaders(Metadata headers) {
                headers.put(CUSTOM_KEY, "PAYMENT-SERVICE-GRPC-SERVER-RESPONSE-VALUE");
                super.sendHeaders(headers);
            }
        }, headers);
    }
}
