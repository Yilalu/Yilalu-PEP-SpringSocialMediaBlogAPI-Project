package com.example.repository;
import com.example.entity.Account;
import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//Since this is inherited from the JpaRepository, no need to annotate it with @Repository or @Componenet
public interface AccountRepository extends JpaRepository<Account, Integer> {
    //This is similar with if i put at head of the method
    //@Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Account a WHERE a.username = :username")
    boolean existsByUsername(String username); //Return true if the username exists

    Account findByUsernameAndPassword(String username, String password);

    Account findByAccountId(Integer accountId);
}
