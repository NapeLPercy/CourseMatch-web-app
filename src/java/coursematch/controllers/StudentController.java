package coursematch.controllers;

import coursematch.entities.Student;
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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentController extends HttpServlet {

    private StudentService studentService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            studentService = new StudentService();

            int student_aps = (int) request.getAttribute("student_aps");
            Map<String, String[]> selectedSubjectsMap = (Map<String, String[]>) request.getAttribute("selected_subjects");

            //List of all the subjects that a student selected
            ArrayList<Subject> selectedSubjects = studentService.getStudentSubjects(selectedSubjectsMap);

            String studentEndorsement = request.getAttribute("student_endorsement").toString();
            
            //User is logged in already
            HttpSession session = request.getSession();

            String name = session.getAttribute("student_name").toString();
            String surname = session.getAttribute("student_surname").toString();
            String idNumber = session.getAttribute("student_id_number").toString();
            session.setAttribute("student_aps", student_aps);
             session.setAttribute("student_endorsement", studentEndorsement);

            Student student = new Student(name, surname, student_aps,studentEndorsement, selectedSubjects);

            studentService.addStudent(student, idNumber);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("Views/Outputs/SubjectsAddedOutcome.jsp").forward(request, response);
    }
}
