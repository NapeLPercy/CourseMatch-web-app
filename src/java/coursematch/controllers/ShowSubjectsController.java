package coursematch.controllers;

import coursematch.entities.Subject;
import coursematch.services.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowSubjectsController extends HttpServlet {

    private StudentService studentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            studentService = new StudentService();
            HttpSession session = request.getSession();

            String idNumber = session.getAttribute("student_id_number").toString();
            List<Subject> studentSubjects = studentService.selectAllStudentSubjects(idNumber);

            String studentEndorsement = session.getAttribute("student_endorsement").toString();

            request.setAttribute("student_subjects", studentSubjects);
            request.setAttribute("student_endorsement", studentEndorsement);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ShowSubjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.getRequestDispatcher("/Views/Inputs/EditSubjects.jsp").forward(request, response);
    }
}
