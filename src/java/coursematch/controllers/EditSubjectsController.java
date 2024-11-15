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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditSubjectsController extends HttpServlet {

    private StudentService studentService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String[]> allAvailableSubjects = request.getParameterMap();//All subjects that a student can potentially select

        //DELETE CURRENT STUDENT SUBJECTS
        //ADD NEW SUBJECTS 
        //COMPUTE STUDENT NEW APS and ENDORSEMENT
        //UPDATE STUDENT'S APS  and ENDORSEMENT IN THE DATABASE
        try {

            studentService = new StudentService();

            //All newly selected subjects
            Map<String, String[]> selectedSubjects = studentService.getSubjectService().getSelectedSubjects(allAvailableSubjects);

            //Sbjects will be used to calculate the new aps
            Map<String, String[]> apsSubjects = studentService.getSubjectService().getApsSubjects();
            int newAps = studentService.calculateStudentAps(apsSubjects);

            //Compute new student endorsement
            String newEndorsement = studentService.computeStudentEndorsement(selectedSubjects);
                    
                    
            String idNumber = request.getSession().getAttribute("student_id_number").toString();
            int studentId = studentService.getStudentId(idNumber);//Get the id of the student who is trying to edit subjects

            //Delete old student subjects 
            boolean isSubjectDeleted = studentService.getSubjectService().deleteSubjects(studentId);//delete student subjects

            //Get and Add new subjects to database if the old ones are deleted
            if (isSubjectDeleted) {

           //     ArrayList<Subject> newStudentSubjects = studentService.getStudentSubjects(allAvailableSubjects);
                //studentService.getSubjectService().addSubjects(newStudentSubjects, studentId);
            }

            //Updates student endorsement to newly determined endorsement
            studentService.updateStudentEndorsement(studentId, newEndorsement);

            //Updates student aps to newly determined
            studentService.updateStudentAPS(studentId, newAps);

            //Updates student aps and endorsement in the session object
            HttpSession session = request.getSession();
            session.setAttribute("student_aps", newAps);
            session.setAttribute("student_endorsement", newEndorsement);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EditSubjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("/Views/Outputs/EditSubjectsOutcome.jsp").forward(request, response);
    }
}
