package Controllers;

import Data_Access_Objects.UserDAO;
import Entities.Account;
import Entities.User;
import Services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserController extends HttpServlet {

    private UserService user_service;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id_number = request.getParameter("sa_id");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String gender = request.getParameter("gender");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Account account = new Account(username, password);
        User user = new User(id_number, name, surname, gender, account);

        try {
            //add user to database
            user_service = new UserService();
            user_service.addUser(user);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("/Views/Outputs/AccountCreatedOutcome.jsp").forward(request, response);
    }
}
