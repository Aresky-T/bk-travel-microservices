package com.aresky.chatservice.websocket;

import com.aresky.chatservice.entity.EActivationStatus;
import com.aresky.chatservice.service.customer.ICustomerService;
import com.aresky.chatservice.service.staff.IStaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class WebSocketEventListener {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IStaffService staffService;

    @EventListener
    public Mono<Void> handleCustomerDisconnectEvent(SessionDisconnectEvent event){
        return Mono.justOrEmpty(StompHeaderAccessor.wrap(event.getMessage()))
                .map(StompHeaderAccessor::getSessionAttributes)
                .filter(attributes -> attributes.containsKey("customerId"))
                .map(attributes -> (Integer) attributes.get("customerId"))
                .flatMap(customerId -> customerService.updateActivationStatus(customerId, EActivationStatus.OFFLINE))
                .then();
    }

    @EventListener
    public Mono<Void> handleStaffDisconnectEvent(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        return Mono.justOrEmpty(headerAccessor.getSessionAttributes())
                .filter(attributes -> attributes.containsKey("staffId"))
                .map(attributes -> (Integer) attributes.get("staffId"))
                .flatMap(staffId -> staffService.updateStatus(staffId, EActivationStatus.OFFLINE))
                .onErrorResume(err -> Mono.empty())
                .then();
    }
}
