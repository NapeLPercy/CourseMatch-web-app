package Services;

import Data_Access_Objects.AccountDAO;
import Entities.Account;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountService {

    private AccountDAO account_dao;

    public AccountService() throws SQLException, ClassNotFoundException {
        account_dao = new AccountDAO();
    }

    public boolean login(String username, String pasword) {

        Account account = new Account(username, pasword);

        boolean login_valid = false;
        try {

            login_valid = account_dao.validateLogin(account);

        } catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return login_valid;
    }

    //get account_id inorder to save it in a session
    public String getAccountId(String username, String password) throws SQLException, ClassNotFoundException {
        return account_dao.getAccountId(username, password);
    }

    //get account holder name and surname inorder to save it in a session
    public ArrayList<String> getAccountHolderDetails(String id_number) throws SQLException, ClassNotFoundException {      
        return account_dao.getAccountHolderDetails(id_number);       
    }

}
