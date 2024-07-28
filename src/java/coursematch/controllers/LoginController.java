package coursematch.controllers;

import coursematch.services.AccountService;
import coursematch.services.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
//import net.sf.json.JSONObject;
import org.json.JSONObject;

public class LoginController extends HttpServlet {

    private AccountService accountService;
    private StudentService studentService;

 //   private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
          accountService = new AccountService();
            studentService = new StudentService();

            boolean isValid= accountService.login(username, password);

            if (isValid) {
                //save logged in user's name, surname in a session
                HttpSession session = request.getSession(isValid);
                session.setAttribute("username", username);//ensures a user cannot access resources unless they are logged in

                String idNumber = accountService.getAccountId(username, password);
                int studentAps = studentService.getStudentAPS(idNumber);
                String studentEndorsement = studentService.getStudentEndorsement(idNumber);

                ArrayList<String> account_holder_details = accountService.getAccountHolderDetails(idNumber);
                String name = account_holder_details.get(0);
                String surname = account_holder_details.get(1);

                session.setAttribute("student_name", name);
                session.setAttribute("student_surname", surname);
                session.setAttribute("student_id_number", idNumber);
                session.setAttribute("student_aps", studentAps);
                session.setAttribute("student_endorsement", studentEndorsement);
            }

            //json response
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("valid",  isValid);

            PrintWriter out = response.getWriter();
            out.write(jsonObject.toString());

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
