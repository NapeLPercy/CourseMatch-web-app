package Controllers;

import Entities.Student;
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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentController extends HttpServlet {

    private StudentService student_service;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            student_service = new StudentService();

            int student_aps = (int) request.getAttribute("student_aps");
            Map<String, String[]> selected_subjects_map = (Map<String, String[]>) request.getAttribute("selected_subjects");

            //list of all the subjects that a student selected
            ArrayList<Subject> selected_subjects = student_service.getStudentSubjects(selected_subjects_map);

            String student_endorsement = request.getAttribute("student_endorsement").toString();
            
            //user is logged in already
            HttpSession session = request.getSession();

            String name = session.getAttribute("student_name").toString();
            String surname = session.getAttribute("student_surname").toString();
            String id_number = session.getAttribute("student_id_number").toString();
            session.setAttribute("student_aps", student_aps);
             session.setAttribute("student_endorsement", student_endorsement);

            Student student = new Student(name, surname, student_aps,student_endorsement, selected_subjects);

            student_service.addStudent(student, id_number);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("Views/Outputs/SubjectsAddedOutcome.jsp").forward(request, response);
    }
}
