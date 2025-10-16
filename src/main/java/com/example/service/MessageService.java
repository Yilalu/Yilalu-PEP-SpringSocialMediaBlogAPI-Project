package com.example.service;
import com.example.entity.Message;
import com.example.repository.MessageRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.AccountRepository;

/**
 * Service layer for managing Message-related operations.
 * This class handles business logic for creating, retrieving,
 * updating, and deleting messages, as well as filtering messages by user.
*/
@Service
public class MessageService {
    // Inject the MessageRepository to interact with the message database table
    @Autowired
    private MessageRepository messageRepository;
    // Inject the AccountRepository to verify that postedBy refers to a real user
    @Autowired
    private AccountRepository accountRepository;

    /**
     * Creates and saves a new message if it meets validation criteria.
     * @param message Message object containing message fields.
     * @return The saved Message with a generated messageId.
     * @throws IllegalArgumentException if messageText is invalid or user does not exist.
    */
    public Message createMessage(Message message) {

        if(message.getMessageText() == null || message.getMessageText().isBlank() || message.getMessageText().length() > 255) {
            throw new IllegalArgumentException("Invalid Message");
        }
        if(accountRepository.findByAccountId(message.getPostedBy()) == null) {
            throw new IllegalArgumentException("User not dound");
        }

        return messageRepository.save(message);
    }

    /**
     * Retrieves all messages from the database.
     * @return A list of all messages (empty if none exist)
    */
    public List<Message> getAllMessages() {
		return messageRepository.findAll();
	}

    /**
     * Retrieves a message by its messageId.
     * @param messageId ID of the message to retrieve.
     * @return The Message object, or null if not found.
    */
	public Message getByMessageId(Integer messageId) {
		Message message = messageRepository.findByMessageId(messageId);
        return message;

	}
    /**
     * Deletes a message by its messageId.
     * @param messageId ID of the message to delete.
     * @return 1 if the message was deleted, or null if it did not exist.
    */
    public Integer deleteMessageById(Integer messageId) {

        Message message = messageRepository.findByMessageId(messageId);
        if(message != null) {
            messageRepository.deleteById(messageId);
            return 1;
        }
        return null;
    }
    /**
     * Updates an existing message's text if it exists and the new text is valid.
     * @param message Message object containing the new text.
     * @param messageId ID of the message to update.
     * @return 1 if the update is successful.
     * @throws IllegalArgumentException if the message does not exist or the new text is invalid.
    */
    public Integer updateMessageById(Message message, Integer messageId) {
        Message existingMessage = messageRepository.findByMessageId(messageId);
        if(existingMessage == null || message.getMessageText() == null ||
        message.getMessageText().isBlank() || message.getMessageText().length() > 255) {
            throw new IllegalArgumentException("Invalid Message");
        }
        existingMessage.setMessageText(message.getMessageText());
        return 1;
    }
    /**
     * Retrieves all messages posted by a specific user.
     * @param accountId The ID of the account (user).
     * @return A list of messages posted by the user (empty if none exist).
    */
    public List<Message> getUserMessage(Integer accountId) {
        return messageRepository.findAllByPostedBy(accountId);
    }  
}
