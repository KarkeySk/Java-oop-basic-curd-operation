package dao;

import java.io.IOException;
import model.Account;
import java.util.List;

public interface AccountDao {
    
    void initializeFile() throws IOException;
    
    void addAccount(Account account) throws Exception;
    
    List<Account> findAll(String searchTerm);
    
    List<Account> findAll();
    
    void saveAll(List<Account> accounts);
    
    void updateAccount(Account updatedAccount);
    
    Account findAccount(int accNumber);
    
    void deleteAccount(int accountNumber);
}