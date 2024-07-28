package coursematch.controllers;

import coursematch.entities.Subject;
import coursematch.services.StudentService;
import coursematch.services.SubjectService;
import coursematch.utils.APSCalculator;
import coursematch.utils.MatrixEndorsement;
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

    private SubjectService subjectService;
    private APSCalculator apsCalculator;
    private MatrixEndorsement matrixEndorsementDeterminer;
    private StudentService studentService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String[]> student_subjects = request.getParameterMap();

        //DELETE CURRENT STUDENT SUBJECTS
        //ADD NEW SUBJECTS 
        //COMPUTE STUDENT NEW APS and ENDORSEMENT
        //UPDATE STUDENT'S APS  and ENDORSEMENT IN THE DATABASE
        
        apsCalculator = new APSCalculator();
        matrixEndorsementDeterminer = new MatrixEndorsement();

        try {

            subjectService = new SubjectService(apsCalculator, matrixEndorsementDeterminer);

            Map<String, String[]> AllSelectedSubjects = subjectService.getSelectedSubjects(student_subjects);//get all subjects

            apsCalculator.setStudentSubjectsInfo(AllSelectedSubjects);//Set a map for newly added subjects
            int newStudentAps = subjectService.getApsCalculator().calculateAPS();//Calculate new aps for student relative to their new subjects

            studentService = new StudentService();

            //Compute new student endorsement
            matrixEndorsementDeterminer = new MatrixEndorsement(AllSelectedSubjects);
            String newStudentEndorsement = matrixEndorsementDeterminer.getStudentEndorsement();//Determine new student matrix endorsement

            String idNumber = request.getSession().getAttribute("student_id_number").toString();
            int studentId = studentService.getStudentId(idNumber);//Get the id of the student who is trying to edit subjects

            //Delete old student subjects 
            boolean isSubjectDeleted = subjectService.deleteSubjects(studentId);//delete student subjects

            //Get and Add new subjects to database if the old ones are deleted
            if (isSubjectDeleted) {
                
                ArrayList<Subject> new_student_subjects = studentService.getStudentSubjects(AllSelectedSubjects);
                subjectService.addSubjects(new_student_subjects, studentId);
            }

            //Updates student endorsement to newly determined endorsement
            studentService.updateStudentEndorsement(studentId, newStudentEndorsement);

            //Updates student aps to newly determined
            studentService.updateStudentAPS(studentId, newStudentAps);

            //Updates student aps and endorsement in the session object
            HttpSession session = request.getSession();
            session.setAttribute("student_aps", newStudentAps);
            session.setAttribute("student_endorsement", newStudentEndorsement);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EditSubjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("/Views/Outputs/EditSubjectsOutcome.jsp").forward(request, response);
    }
}
