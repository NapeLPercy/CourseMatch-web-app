package Controllers;

import Services.AccountService;
import Services.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController extends HttpServlet {

    private AccountService account_service;
    private StudentService student_service;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String url = "Views/Outputs/login_error.jsp";

        try {

            account_service = new AccountService();
            student_service = new StudentService();
            
            boolean login_valid = account_service.login(username, password);
            if (login_valid) {
                url = "Views/Menu.jsp";

                //save logged in user's name, surname in a session
                HttpSession session = request.getSession(login_valid);
                session.setAttribute("username", username);//ensures a user cannot access resources unless they are logged in
                
                String id_number = account_service.getAccountId(username, password);
                int student_aps = student_service.getStudentAPS(id_number);
                String student_endorsement = student_service.getStudentEndorsement(id_number);
                
                ArrayList<String> account_holder_details = account_service.getAccountHolderDetails(id_number);
                String name = account_holder_details.get(0);
                String surname = account_holder_details.get(1);

                session.setAttribute("student_name", name);
                session.setAttribute("student_surname", surname);
                session.setAttribute("student_id_number", id_number);
                session.setAttribute("student_aps",student_aps);
                 session.setAttribute("student_endorsement",student_endorsement);
                 
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

}
