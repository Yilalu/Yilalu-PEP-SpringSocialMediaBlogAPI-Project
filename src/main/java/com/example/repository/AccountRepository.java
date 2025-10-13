package com.example.repository;
import com.example.entity.Account;
import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//Since this is inherited from the JpaRepository, no need to annotate it with @Repository or @Componenet
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("select a from account a where a.username = :username")
    List<Account> findByUserName(@Param("username") String username);
}
