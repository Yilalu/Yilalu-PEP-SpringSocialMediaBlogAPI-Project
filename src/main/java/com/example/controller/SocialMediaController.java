package com.example.controller;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @Autowired
    private MessageService messageService;

    /**
     * Registers a new user account.
     * @param account The account details from the request body.
     * @return The newly created account in the response body.
    */
    @PostMapping("/register")
    public ResponseEntity<Account> postRegisterAccount(@RequestBody Account account){
        Account newAccount = accountService.registerAccount(account);
        return ResponseEntity.ok(newAccount);
    } 
    /**
     * Authenticates a user by validating their username and password.
     * @param account The login credentials.
     * @return The logged-in account details if successful.
    */
    @PostMapping("/login")
    public  ResponseEntity<Account> postLoginAccount(@RequestBody Account account) {
        Account loggedIn = accountService.logiAccount(account);
        return ResponseEntity.ok(loggedIn);
    }

    /**
     * Creates a new message.
     * @param message The message object to create.
     * @return The created message with its generated ID.
    */
    @PostMapping("/messages")
    public ResponseEntity<Message> postMessage(@RequestBody Message message) {
        Message newMessage  = messageService.createMessage(message);
        return ResponseEntity.ok(newMessage);
    }
    /**
     * Retrieves all messages.
     * @return A list of all messages (can be empty).
    */
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }
    /**
     * Retrieves a specific message by ID. 
     * @param messageId The ID of the message.
     * @return The message if found, or an empty body if not.
    */
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer messageId) {
        Message message = messageService.getByMessageId(messageId);
        return ResponseEntity.ok(message);
    }
    /**
     * Deletes a specific message by ID.
     * @param messageId The ID of the message to delete.
     * @return 1 if deleted, or an empty response if it didn’t exist.
    */
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deletMessageById (@PathVariable Integer messageId) {
        Integer deletedMessage = messageService.deleteMessageById(messageId);
        if(deletedMessage != null) {
           return ResponseEntity.ok(deletedMessage);
        }
        return ResponseEntity.ok().build();
    }
    /**
     * Updates the text of a specific message.
     * @param message The message containing updated text.
     * @param messageId The ID of the message to update.
     * @return 1 if the update succeeded.
    */
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updatMessageById(@RequestBody Message message, @PathVariable Integer messageId) {
        Integer updatedMessage = messageService.updateMessageById(message, messageId);
        return ResponseEntity.ok(updatedMessage);
    }
    /**
     * Retrieves all messages posted by a specific user.
     * @param accountId The ID of the account (user).
     * @return A list of messages posted by that user.
    */
    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getUserMessage(@PathVariable Integer accountId) {
        List<Message> messages = messageService.getUserMessage(accountId);
        return ResponseEntity.ok(messages);
    }
}
