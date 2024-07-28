package coursematch.services;

import coursematch.data_access_objects.AccountDAO;
import coursematch.entities.Account;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountService {

    private AccountDAO accountDao;

    public AccountService() throws SQLException, ClassNotFoundException {
        accountDao = new AccountDAO();
    }

    public boolean login(String username, String pasword) {

        Account account = new Account(username, pasword);

        boolean login_valid = false;
        try {

            login_valid = accountDao.validateLogin(account);
            System.out.println(login_valid);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return login_valid;
    }

    //get account_id inorder to save it in a session
    public String getAccountId(String username, String password) throws SQLException, ClassNotFoundException {
        return accountDao.getAccountId(username, password);
    }

    //get account holder name and surname inorder to save it in a session
    public ArrayList<String> getAccountHolderDetails(String id_number) throws SQLException, ClassNotFoundException {      
        return accountDao.getAccountHolderDetails(id_number);       
    }

}
