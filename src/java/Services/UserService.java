package Services;
import Data_Access_Objects.UserDAO;
import Entities.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService {
    
    private final UserDAO user_dao;
    
    public UserService() throws SQLException, ClassNotFoundException{
       user_dao = new UserDAO();
    }
    
    public boolean addUser(User user){
        
        try {
            
            user_dao.addUser(user);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }//end
    
}
