package com.example.repository;
import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for performing CRUD operations on the Account entity.
 * This interface extends JpaRepository, which provides all the standard
 * methods for interacting with the database (e.g. save, findAll, findById, deleteById).
 * It also defines custom query methods for specific account operations such as
 * checking if a username exists or validating login credentials.
*/
public interface AccountRepository extends JpaRepository<Account, Integer> {

    /**
     * Checks if an account with the given username already exists.
     * This method uses Spring Data JPA's derived query mechanism to
     * automatically generate the SQL query equivalent to:
     * SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END
     * FROM Account a
     * WHERE a.username = :username
     * @param username The username to check.
     * @return true if a user with this username exists, false otherwise.
    */
    boolean existsByUsername(String username);
    /**
     * Finds an account by matching both username and password.
     * Typically used during user login to verify credentials.
     * @param username The username of the account.
     * @param password The password of the account.
     * @return The matching Account object if credentials are valid; otherwise null.
    */
    Account findByUsernameAndPassword(String username, String password);

    /**
     * Finds an account by its unique ID.
     * @param accountId The ID of the account.
     * @return The Account if found; otherwise null
    */
    Account findByAccountId(Integer accountId);
}
