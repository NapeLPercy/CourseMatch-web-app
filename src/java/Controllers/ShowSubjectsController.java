package Controllers;

import Entities.Subject;
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

public class ShowSubjectsController extends HttpServlet {

    private StudentService student_service;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            student_service = new StudentService();
            HttpSession session = request.getSession();

            String id_number = session.getAttribute("student_id_number").toString();
            ArrayList<Subject> student_subjects = student_service.selectAllStudentSubjects(id_number);

            String student_endorsement = session.getAttribute("student_endorsement").toString();

            request.setAttribute("student_subjects", student_subjects);
            request.setAttribute("student_endorsement", student_endorsement);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ShowSubjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("/Views/Inputs/EditSubjects.jsp").forward(request, response);
    }
}
