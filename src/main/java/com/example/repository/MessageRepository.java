package com.example.repository;
import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

//Since this is inherited from the JpaRepository, no need to annotate it with @Repository or @Componenet 
public interface MessageRepository extends JpaRepository<Message, Integer> {
    Message findByMessageId(Integer messageId);
}
