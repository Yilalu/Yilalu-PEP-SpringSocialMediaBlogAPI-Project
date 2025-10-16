package com.example.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Account;
import com.example.repository.AccountRepository;

/**
 * Service layer responsible for handling all business logic related to
 * account registration and authentication.
 * This class interacts with the AccountRepository to perform operations such as:
 * Registering new accounts
 * Validating login credentials
*/
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    // Constructor injection ensures proper dependency initialization and easier testing
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Registers a new user account.
     * Validation rules:
     * Username must not be blank
     * Password must be at least 4 characters long
     * Username must not already exist in the database
     * If all validations pass, the new account is saved and returned.
     * If validation fails, appropriate exceptions are thrown:
     * IllegalArgumentException for invalid input
     * IllegalStateException for duplicate username
     * @param account The account object containing username and password.
     * @return The saved Account object, including its generated accountId.
    */
    public Account registerAccount(Account account) {

        if(account.getUsername().isBlank() || account.getPassword().length() < 4){
            throw new IllegalArgumentException("Invalid username or password");
        }
        
        if (accountRepository.existsByUsername(account.getUsername())) {
            throw new IllegalStateException("Username already exists.");
        }
        return accountRepository.save(account); 
    }
    /**
     * Authenticates a user's login credentials.
     * Checks if the provided username and password match an existing account.
     * If no match is found, a SecurityException is thrown.
     * @param account The account object containing login credentials.
     * @return The matching Account object if credentials are valid.
    */
    public Account logiAccount(Account account) {
        Account existingAccount = accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (existingAccount ==  null) {
            throw new SecurityException("Username or password does not exist");
        }
        return existingAccount;   
    }
}
