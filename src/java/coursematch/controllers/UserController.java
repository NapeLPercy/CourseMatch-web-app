package coursematch.controllers;

import coursematch.data_access_objects.UserDAO;
import coursematch.entities.Account;
import coursematch.entities.User;
import coursematch.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserController extends HttpServlet {

    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idNumber = request.getParameter("sa_id");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String gender = request.getParameter("gender");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Account account = new Account(username, password);
        User user = new User(idNumber, name, surname, gender, account);

        try {
            //add user to database
            userService = new UserService();
            userService.addUser(user);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("/Views/Outputs/AccountCreatedOutcome.jsp").forward(request, response);
    }
}
