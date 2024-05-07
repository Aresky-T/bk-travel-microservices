package com.aresky.bookingservice.delivery.grpc;

import com.google.common.annotations.VisibleForTesting;
import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookingGrpcInterceptor implements ServerInterceptor {

    @VisibleForTesting
    public static final Metadata.Key<String> CUSTOM_KEY = Metadata.Key.of("GRPC_CUSTOM_KEY", Metadata.ASCII_STRING_MARSHALLER);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        MethodDescriptor<ReqT, RespT> method = call.getMethodDescriptor();
        log.error("Service: {}, method: {}, headers: {}", method.getServiceName(), method.getBareMethodName(), headers.toString());

        return next.startCall(new ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(call) {
            @Override
            public void sendHeaders(Metadata headers) {
                headers.put(CUSTOM_KEY, "BOOKING_GRPC_SERVER_CUSTOM_KEY");
                super.sendHeaders(headers);
            }
        }, headers);
    }
}
