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
    private MessageService messageService;

    @PostMapping("/register")
    public ResponseEntity<?> postRegisterAccount(@RequestBody Account account){
        try {
            Account newAccount = accountService.register(account);
            return ResponseEntity.ok(newAccount);
        } catch (RuntimeException e) {
            //Duplicate userName
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public Account postLoginAccount(@RequestBody Account account) {
        return account;
    }

    @PostMapping("/messages")
    public Message posMessage(@RequestBody Message message) {
        return message;
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
