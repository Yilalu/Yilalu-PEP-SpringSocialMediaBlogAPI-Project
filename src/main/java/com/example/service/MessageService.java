package com.example.service;
import com.example.entity.Message;
import com.example.repository.MessageRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;

@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Message createMessage(Message message) {

        if(message.getMessageText() == null || message.getMessageText().isBlank() || message.getMessageText().length() > 255) {
            throw new IllegalArgumentException("Invalid Message");
        }
        if(accountRepository.findByAccountId(message.getPostedBy()) == null) {
            throw new IllegalArgumentException("User not dound");
        }

        return messageRepository.save(message);
    }

	public List<Message> getAllMessages() {
		return messageRepository.findAll();
	}

	public Message getByMessageId(Integer messageId) {
		Message message = messageRepository.findByMessageId(messageId);
        return message;

	}

    public Integer deleteMessageById(Integer messageId) {

        Message message = messageRepository.findByMessageId(messageId);
        if(message != null) {
            messageRepository.deleteById(messageId);
            return 1;
        }
        return null;
    }

    public Integer updateMessageById(Message message, Integer messageId) {
        Message existingMessage = messageRepository.findByMessageId(messageId);
        if(existingMessage == null || message.getMessageText() == null ||
        message.getMessageText().isBlank() || message.getMessageText().length() > 255) {
            throw new IllegalArgumentException("Invalid Message");
        }
        existingMessage.setMessageText(message.getMessageText());
        return 1;
    }
    


    
}
