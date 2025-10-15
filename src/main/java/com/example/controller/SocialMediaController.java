package com.example.controller;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
@RequestMapping("/")
public class SocialMediaController {

     @Autowired
    private AccountService accountService;

    @Autowired
    private MessageService messageService;

    @PostMapping("/register")
    public ResponseEntity<Account> postRegisterAccount(@RequestBody Account account){
        Account newAccount = accountService.registerAccount(account);
        return ResponseEntity.ok(newAccount);
    } 
    
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleDuplicateUsername(IllegalStateException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @PostMapping("/login")
    public  ResponseEntity<Account> postLoginAccount(@RequestBody Account account) {
        Account loggedIn = accountService.logiAccount(account);
        return ResponseEntity.ok(loggedIn);
    }
    //Handle if there user entered unexisting or unmatched inputs
    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<String> handleUnauthorized(SecurityException e) {
        return ResponseEntity.status(401).body(e.getMessage());
    }


    @PostMapping("/messages")
    public ResponseEntity<Message> posMessage(@RequestBody Message message) {
        Message newMessage  = messageService.createMessage(message);
        return ResponseEntity.ok(newMessage);
    }

    @GetMapping("/messages")
    public Message getMessage(@RequestBody Message message) {
        return message;
    }

    @GetMapping("/message{messageId}")
    public Message getMessageById(@RequestBody Message message, @PathVariable Integer messageId) {
        return null;
    }

    @DeleteMapping("/message{messageId}")
    public Message deletMessageById (@RequestBody Message message, @PathVariable Integer messageId) {
        return null;
    }

    @PatchMapping("/message{messageId}")
    public Message updatMessageById(@RequestBody Message message, @PathVariable Integer messageId) {
        return null;
    }

    @GetMapping("/accounts/{accountId}/messages")
    public Message getUserMessage(@PathVariable Integer accountId) {
        return null;
    }



}
