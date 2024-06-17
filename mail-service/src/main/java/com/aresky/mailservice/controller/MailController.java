package com.aresky.mailservice.controller;

import com.aresky.mailservice.dto.request.MailReplyRequest;
import com.aresky.mailservice.dto.request.MailRequest;
import com.aresky.mailservice.dto.response.*;
import com.aresky.mailservice.service.mail.IMailService;
import com.aresky.mailservice.service.mailbox.IMailBoxService;
import com.aresky.mailservice.service.staff.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mail")
public class MailController {

    @Autowired
    private IMailService mailService;

    @Autowired
    private IStaffService staffService;

    @Autowired
    private IMailBoxService mailBoxService;

    // POST /send
    @PostMapping("/send")
    public Mono<ResponseEntity<?>> sendMail(@RequestBody MailRequest request){
        return mailService.send(request).thenReturn(ResponseEntity.ok("success"));
    }

    // POST /reply
    @PostMapping("/reply")
    public Mono<ResponseEntity<?>> replyMail(@RequestBody MailReplyRequest request){
        return mailService.reply(request).thenReturn(ResponseEntity.ok("success"));
    }

    // POST /staff
    @PostMapping("/staff")
    public Mono<ResponseEntity<?>> createStaff(@RequestParam String email){
        return staffService.createStaff(email).thenReturn(ResponseEntity.ok("success"));
    }

    @GetMapping("/all")
    public Mono<ResponseEntity<List<MailResponse>>> getAllMails(
            @RequestParam Integer boxId,
            @RequestParam Integer limit,
            @RequestParam Integer offset
    ){
        return mailService.getAllMailResponses(boxId, limit, offset).map(ResponseEntity::ok);
    }

    // GET /all
    @GetMapping("/all-for-customer")
    public Mono<ResponseEntity<List<MailResponse>>> getAllMailsForCustomer(
            @RequestParam String customerEmail,
            @RequestParam Integer limit,
            @RequestParam Integer offset
    ){
        return mailService.getAllMailsForCustomer(customerEmail, limit, offset).map(ResponseEntity::ok);
    }

    @GetMapping("/details-for-customer")
    public Mono<ResponseEntity<CustomerMailDetails>> getMailDetailsForCustomer(
            @RequestParam String customerEmail,
            @RequestParam Integer mailId
    ){
        return mailService.getMailDetailsForCustomer(customerEmail, mailId).map(ResponseEntity::ok);
    }

    // GET /details
    @GetMapping("/details-for-staff")
    public Mono<ResponseEntity<StaffMailDetails>> getMailById(
            @RequestParam String staffEmail,
            @RequestParam Integer mailId
    ){
        return mailService.getMailDetailsForStaff(staffEmail, mailId).map(ResponseEntity::ok);
    }

    // GET /staff/check
    @GetMapping("/staff/check")
    public Mono<ResponseEntity<Boolean>> checkStaff(@RequestParam String email){
        return staffService.existsByEmail(email).map(ResponseEntity::ok);
    }

    // GET /staff/details
    @GetMapping("/staff/details")
    public Mono<ResponseEntity<StaffResponse>> getStaff(@RequestParam String email){
        return staffService.getStaffDetailsByEmail(email).map(ResponseEntity::ok);
    }

    // GET /boxes
    @GetMapping("/boxes")
    public Mono<ResponseEntity<List<MailBoxResponse>>> getAllMailBoxes(
            @RequestParam String staffEmail,
            @RequestParam Integer limit,
            @RequestParam Integer offset
    ){
        return mailBoxService.getAllMailBoxResponsesForStaff(staffEmail).map(ResponseEntity::ok);
    }
    // GET /box/{id}
    @GetMapping("/box/details")
    public Mono<ResponseEntity<MailBoxDetails>> getMailBoxById(@RequestParam Integer boxId){
        return mailBoxService.getMailBoxDetails(boxId).map(ResponseEntity::ok);
    }

    // PATCH /seen
    @PatchMapping("/read")
    public Mono<ResponseEntity<MailResponse>> markMailAsSeen(
            @RequestParam String staffEmail,
            @RequestParam Integer mailId
    ){
        return mailService.markMailAsRead(staffEmail, mailId).map(ResponseEntity::ok);
    }

    // DELETE
    @DeleteMapping
    public Mono<ResponseEntity<?>> deleteMailById(@RequestParam Integer mailId){
        return mailService.deleteMail(mailId).thenReturn(ResponseEntity.ok("success"));
    }

    // DELETE /box/{id}
    @DeleteMapping("/box")
    public Mono<ResponseEntity<?>> deleteMailBoxById(@RequestParam Integer boxId){
        return mailBoxService.deleteMailBox(boxId).thenReturn(ResponseEntity.ok("success"));
    }

    // DELETE /staff/{id}
    @DeleteMapping("/staff")
    public Mono<ResponseEntity<?>> deleteStaffById(@RequestParam Integer staffId){
        return staffService.deleteStaff(staffId).thenReturn(ResponseEntity.ok("success"));
    }
}
