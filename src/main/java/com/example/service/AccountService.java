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

    public Account register(Account account) throws Exception {
        List<Account> existingUserName = accountRepository.findByUserName(account.getUsername());

        if (account.getUsername().isBlank() || account.getPassword().length() < 4 || existingUserName != null) {
            return null;
        }
        return accountRepository.save(account);
        
    }
}
