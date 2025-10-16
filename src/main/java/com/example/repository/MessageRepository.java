package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repository interface for performing CRUD operations on the Message entity.
 * This interface extends JpaRepository, which provides built-in
 * methods for interacting with the database such as save, findAll, findById, and deleteById.
 * Custom query methods follow Spring Data JPA's derived query conventions,
 * automatically generating SQL queries based on method names.
*/
public interface MessageRepository extends JpaRepository<Message, Integer> {
    /**
     * Finds a single message by its unique messageId.
     * @param messageId The ID of the message to find.
     * @return The Message object if found, otherwise null.
    */
    Message findByMessageId(Integer messageId);

    /**
     * Finds all messages posted by a specific user (account).
     * @param accountId The ID of the account (user) who posted the messages.
     * @return A list of messages posted by the specified account.
    */
    List<Message> findAllByPostedBy(Integer accountId);
}

