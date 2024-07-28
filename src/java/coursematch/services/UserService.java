package coursematch.services;
import coursematch.data_access_objects.UserDAO;
import coursematch.entities.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService {
    
    private final UserDAO userDao;
    
    public UserService() throws SQLException, ClassNotFoundException{
       userDao = new UserDAO();
    }
    
    public boolean addUser(User user){
        
        try {
            
            userDao.addUser(user);
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }//end
    
}
