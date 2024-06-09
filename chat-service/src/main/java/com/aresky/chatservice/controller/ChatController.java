package com.aresky.chatservice.controller;

import com.aresky.chatservice.dto.request.ConversationRequest;
import com.aresky.chatservice.dto.request.CustomerRequest;
import com.aresky.chatservice.entity.EActivationStatus;
import com.aresky.chatservice.service.conversation.IConversationService;
import com.aresky.chatservice.service.customer.ICustomerService;
import com.aresky.chatservice.service.message.IMessageService;
import com.aresky.chatservice.service.staff.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    @Autowired
    private IStaffService staffService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IConversationService conversationService;

    @Autowired
    private IMessageService messageService;

    @PostMapping("/customer/connect-to-staff")
    public Mono<ResponseEntity<?>> connectToConversationByCustomer(@RequestBody ConversationRequest request) {
        return conversationService.connectByCustomer(request).map(ResponseEntity::ok);
    }

    @PostMapping("/staff")
    public Mono<ResponseEntity<?>> createStaff(@RequestParam String email) {
        return staffService.create(email).thenReturn(ResponseEntity.ok("success"));
    }

    @PostMapping("/register-customer")
    public Mono<ResponseEntity<?>> createCustomer(@RequestBody CustomerRequest request) {
        return customerService.register(request).map(ResponseEntity::ok);
    }

    @GetMapping("/conversations")
    public Mono<ResponseEntity<?>> getAllConversations(
            @RequestParam Integer staffId,
            @RequestParam Integer limit,
            @RequestParam Integer offset) {
        return conversationService.getAll(staffId, limit, offset).map(ResponseEntity::ok);
    }

    @GetMapping("/customer")
    public Mono<ResponseEntity<?>> getCustomerByEmail(@RequestParam String email) {
        return customerService.getByEmail(email).map(ResponseEntity::ok);
    }

    @GetMapping("/customer/check")
    public Mono<ResponseEntity<?>> checkCustomerByEmail(@RequestParam String email) {
        return customerService.existsByEmail(email).map(ResponseEntity::ok);
    }

    @GetMapping("/staff")
    public Mono<ResponseEntity<?>> getStaffByEmail(@RequestParam String email) {
        return staffService.getStaffByEmail(email).map(ResponseEntity::ok);
    }

    @GetMapping("/staff/check")
    public Mono<ResponseEntity<?>> checkStaffByEmail(@RequestParam String email) {
        return staffService.existsByEmail(email).map(ResponseEntity::ok);
    }

    @GetMapping("/staff/online")
    public Mono<ResponseEntity<?>> findOnlineStaff() {
        return staffService.getRandomOnlineStaff().map(ResponseEntity::ok);
    }

    @GetMapping("/messages")
    public Mono<ResponseEntity<?>> getAllMessages(
            @RequestParam Integer conversationId,
            @RequestParam Integer limit,
            @RequestParam Integer offset) {
        return messageService.getAllMessageResponses(conversationId, limit, offset)
                .map(ResponseEntity::ok);
    }

    @PatchMapping("/staff/status")
    public Mono<ResponseEntity<?>> updateStaffStatus(
            @RequestParam Integer staffId, @RequestParam EActivationStatus status) {
        return staffService.updateStatus(staffId, status).thenReturn(ResponseEntity.ok("success"));
    }

    @DeleteMapping("/staff")
    public Mono<ResponseEntity<?>> deleteStaffById(@RequestParam Integer staffId) {
        return staffService.deleteStaff(staffId).thenReturn(ResponseEntity.ok("success"));
    }

    @DeleteMapping("/customer")
    public Mono<ResponseEntity<?>> deleteCustomerById(@RequestParam Integer customerId) {
        return customerService.deleteById(customerId).thenReturn(ResponseEntity.ok("success"));
    }

    @DeleteMapping("/conversation")
    public Mono<ResponseEntity<?>> deleteChatBoxById(@RequestParam Integer conversationId) {
        return conversationService.deleteById(conversationId).thenReturn(ResponseEntity.ok("success"));
    }
}
