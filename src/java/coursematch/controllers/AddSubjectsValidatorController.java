package coursematch.controllers;

import coursematch.entities.Student;
import coursematch.services.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddSubjectsValidatorController extends HttpServlet {

    private StudentService studentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String id_number = (String) session.getAttribute("student_id_number");

        try {

            studentService = new StudentService();

            Student student = studentService.getStudent(id_number);

            request.setAttribute("student", student);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AddSubjectsValidatorController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("/Views/Inputs/ChooseMatricSubjects.jsp").forward(request, response);
    }

}
