package coursematch.controllers;

import coursematch.entities.*;
import coursematch.services.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.*;

public class StudentController extends HttpServlet {

    private StudentService studentService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            studentService = new StudentService();

            // Map of all the APS subjects
            Map<String, String[]> apsSubjects = (Map<String, String[]>) request.getAttribute("apsSubjects");
            int aps = studentService.calculateStudentAps(apsSubjects);

            // Map of all the subjects that a student selected
            Map<String, String[]> selectedSubjects = (Map<String, String[]>) request.getAttribute("selectedSubjects");
            String endorsement = studentService.computeStudentEndorsement(selectedSubjects);

            List<Subject> subjects = (List<Subject>) request.getAttribute("subjectList");

            // User is logged in already
            HttpSession session = request.getSession();

            String name = session.getAttribute("student_name").toString();
            String surname = session.getAttribute("student_surname").toString();
            String idNumber = session.getAttribute("student_id_number").toString();
            session.setAttribute("student_aps", aps);
            session.setAttribute("student_endorsement", endorsement);

            Student student = new Student(name, surname, aps, endorsement, subjects);
            studentService.addStudent(student, idNumber);//Store student info

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("Views/Outputs/SubjectsAddedOutcome.jsp").forward(request, response);
    }
}
