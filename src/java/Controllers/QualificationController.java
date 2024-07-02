package Controllers;

import Entities.Qualification;
import Entities.Subject;
import Services.QualificationService;
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

public class QualificationController extends HttpServlet {

    private QualificationService qualification_service;
    private StudentService student_service;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            qualification_service = new QualificationService();
            student_service = new StudentService();

            HttpSession session = request.getSession();

            int student_aps = (int) session.getAttribute("student_aps");
            String id_number = session.getAttribute("student_id_number").toString();
            String student_endorsement = session.getAttribute("student_endorsement").toString();

            //list of students who qualifies for courses relative to their APS only
            ArrayList<Qualification> aps_qualified = qualification_service.filterQualificationsUsingAPS(student_aps, student_endorsement);//harcoded
            
            ArrayList<Subject> student_subjects = student_service.selectAllStudentSubjects(id_number);//all the subjects that a student have submitted
     
            //list of students who qualifies for courses relative to their APS(through the parameter) AND  whether or not the meet a course's prerequisite subjects
            ArrayList<Qualification> prerequisite_subjects_qualified
                    = qualification_service.filterQualificationsUsingPrerequisiteSubject(aps_qualified, student_subjects);

            System.out.print("prerequisite qualified: " + prerequisite_subjects_qualified.size());

            request.setAttribute("qualifications", prerequisite_subjects_qualified);
            request.setAttribute("number_of_courses", prerequisite_subjects_qualified.size());

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(QualificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("/Views/Outputs/QualificationsPanel.jsp").forward(request, response);
    }

}
