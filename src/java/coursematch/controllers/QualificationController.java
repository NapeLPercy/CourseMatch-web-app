package coursematch.controllers;

import coursematch.entities.Qualification;
import coursematch.entities.Subject;
import coursematch.services.QualificationService;
import coursematch.services.StudentService;
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

public class QualificationController extends HttpServlet {

    private QualificationService qualificationService;
    private StudentService studentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            qualificationService = new QualificationService();
            studentService = new StudentService();

            HttpSession session = request.getSession();

            int studentAps = (int) session.getAttribute("student_aps");
            String idNumber = session.getAttribute("student_id_number").toString();
            String studentEndorsement = session.getAttribute("student_endorsement").toString();

            //List of Quaifications that a student qualifies for. Relative to their APS only
            ArrayList<Qualification> apsQualified = 
                    qualificationService.filterQualificationsUsingAPS(studentAps, studentEndorsement);
            
            //All the subjects that a student have submitted
            //These subjects wil; be compared to a Qualifcation's Prerequisite Subjects when filtering Qualifications
            ArrayList<Subject> studentSubjects = studentService.selectAllStudentSubjects(idNumber);
     
            //List a Qualifications that a student qualifies for
            //Checks whether or not the student meet a Qualification's Prerequisite Subjects
            ArrayList<Qualification> prerequisite_subjects_qualified
                    = qualificationService.filterQualificationsUsingPrerequisiteSubject(apsQualified, studentSubjects);

            request.setAttribute("qualifications", prerequisite_subjects_qualified);
            request.setAttribute("number_of_courses", prerequisite_subjects_qualified.size());

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(QualificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("/Views/Outputs/QualificationsPanel.jsp").forward(request, response);
    }

}
