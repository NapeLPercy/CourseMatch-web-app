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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QualificationController extends HttpServlet {

    private QualificationService qualificationService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            qualificationService = new QualificationService();

            HttpSession session = request.getSession();

            int studentAps = (int) session.getAttribute("student_aps");
            String idNumber = session.getAttribute("student_id_number").toString();
            String studentEndorsement = session.getAttribute("student_endorsement").toString();

            // Quaifications that a student qualifies for. Relative to their APS
            List<Qualification> apsQualified = qualificationService.filterQualificationsUsingAPS(studentAps);

            // Quaifications that a student qualifies for. Relative to their Matrix Endorsement
            List<Qualification> endorsementQualified = qualificationService.filterQualificationsUsingEndorsement(apsQualified, studentEndorsement);


            /* //All the subjects that a student have submitted
            //These subjects wil; be compared to a Qualifcation's Prerequisite Subjects when filtering Qualifications
            List<Subject> studentSubjects = studentService.selectAllStudentSubjects(idNumber);

            //List a Qualifications that a student qualifies for
            //Checks whether or not the student meet a Qualification's Prerequisite Subjects
            List<Qualification> prerequisiteSubjectsQualified
                    = qualificationService.filterQualificationsUsingPrerequisiteSubject(apsQualified, studentSubjects);
          
            
            request.setAttribute("qualifications", prerequisiteSubjectsQualified);
            request.setAttribute("number_of_courses", prerequisiteSubjectsQualified.size());
             */
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(QualificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

//        request.getRequestDispatcher("/Views/Outputs/QualificationsPanel.jsp").forward(request, response);
    }

}
