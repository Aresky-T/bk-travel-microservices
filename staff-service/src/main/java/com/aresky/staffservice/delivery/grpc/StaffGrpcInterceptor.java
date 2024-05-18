package com.aresky.staffservice.delivery.grpc;

import com.google.common.annotations.VisibleForTesting;
import io.grpc.*;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcGlobalServerInterceptor
public class StaffGrpcInterceptor implements ServerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(StaffGrpcInterceptor.class);

    @VisibleForTesting
    public static final Metadata.Key<String> CUSTOM_KEY = Metadata.Key.of("GRPC_SERVER_KEY", Metadata.ASCII_STRING_MARSHALLER);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        String service = call.getMethodDescriptor().getServiceName();
        String method = call.getMethodDescriptor().getBareMethodName();

        log.info("Intercepting call to {}, method: {}, headers: {}", service, method, headers.toString());
        return next.startCall(new ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(call) {
            @Override
            public void sendHeaders(Metadata headers) {
                headers.put(CUSTOM_KEY, "STAFF-SERVICE-GRPC-SERVER-RESPONSE-VALUE");
                super.sendHeaders(headers);
            }
        }, headers);
    }
}
