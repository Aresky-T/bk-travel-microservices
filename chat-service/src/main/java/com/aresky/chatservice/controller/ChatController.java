package com.aresky.chatservice.controller;

import com.aresky.chatservice.dto.request.ConversationRequest;
import com.aresky.chatservice.dto.request.CustomerRequest;
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

    @PostMapping("/customer/connect-to-staff/registered")
    public Mono<ResponseEntity<?>> connectToStaff(@RequestParam Integer accountId, @RequestParam Integer staffId){
        return Mono.just(ResponseEntity.ok(""));
    }

    @PostMapping("/customer/connect-to-staff/guest")
    public Mono<ResponseEntity<?>> connectToStaff(@RequestParam Integer staffId, @RequestBody CustomerRequest customer){
        return Mono.just(ResponseEntity.ok(""));
    }

    @PostMapping("/conversation/connect")
    public Mono<ResponseEntity<?>> connectToConversation(@RequestBody ConversationRequest request){
        return Mono.just(ResponseEntity.ok(""));
    }

    @GetMapping("/customers")
    public Mono<ResponseEntity<?>> getAllCustomers(){
        return Mono.just(ResponseEntity.ok(""));
    }

    @GetMapping("/customer")
    public Mono<ResponseEntity<?>> getCustomerById(@RequestParam Integer customerId){
        return Mono.just(ResponseEntity.ok(""));
    }

    @GetMapping("/staff/check")
    public Mono<ResponseEntity<?>> checkStaffByAccountId(@RequestParam Integer accountId){
        return staffService.validateStaffByAccountId(accountId).map(ResponseEntity::ok);
    }

    @GetMapping("/staff/online")
    public Mono<ResponseEntity<?>> findOnlineStaff(){
        return staffService.getRandomOnlineStaff().map(ResponseEntity::ok);
    }

    @DeleteMapping("/customer")
    public Mono<ResponseEntity<?>> deleteCustomerById(@RequestParam Integer customerId){
        return Mono.just(ResponseEntity.ok(""));
    }

    @DeleteMapping("/conversation")
    public Mono<ResponseEntity<?>> deleteChatBoxById(@RequestParam Integer conversationId){
        return Mono.just(ResponseEntity.ok(""));
    }

    @DeleteMapping("/chat")
    public Mono<ResponseEntity<?>> deleteChatById(@RequestParam Integer chatId){
        return Mono.just(ResponseEntity.ok(""));
    }
}
