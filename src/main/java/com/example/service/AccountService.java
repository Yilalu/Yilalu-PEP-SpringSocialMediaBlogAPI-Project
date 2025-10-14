package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(Account account) {

        if(account.getUsername().isBlank() || account.getPassword().length() < 4){
            throw new IllegalArgumentException("Invalid username or password");
        }
        
        if (accountRepository.existsByUsername(account.getUsername())) {
            throw new IllegalStateException("Username already exists.");
        }
        return accountRepository.save(account);
        
        
    }
    public Account logiAccount(Account account) {
        Account existingAccount = accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (existingAccount ==  null) {
            throw new SecurityException("Username or password does not exist");
        }
        return existingAccount;
          
    }
}
