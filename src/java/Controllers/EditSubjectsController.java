package Controllers;

import Entities.Subject;
import Services.StudentService;
import Services.SubjectService;
import Utils.APSCalculator;
import Utils.MatrixEndorsement;
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

    private SubjectService subject_service;
    private APSCalculator aps_calculator;
    private MatrixEndorsement matrix_endorsement_determiner;
    private StudentService student_service;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String[]> student_subjects = request.getParameterMap();

        //DELETE CURRENT STUDENT SUBJECTS, DONE
        //ADD NEW SUBJECTS 
        //COMPUTE STUDENT NEW APS, DONE
        //determine new endorsement
        //UPDATE STUDENT'S APS  and endrosement IN THE DATABASE
        aps_calculator = new APSCalculator();
        matrix_endorsement_determiner = new MatrixEndorsement();

        try {

            subject_service = new SubjectService(aps_calculator, matrix_endorsement_determiner);

            Map<String, String[]> new_selected_subjects = subject_service.getSelectedSubjects(student_subjects);//get all subjects

            aps_calculator.set_student_subjects_info(new_selected_subjects);//set a map for newly added subjects
            int new_student_aps = subject_service.get_aps_calculator().calculateAPS();//calculate new aps for student relative to their new subjects

            student_service = new StudentService();

            //compute new student endorsement
            matrix_endorsement_determiner = new MatrixEndorsement(new_selected_subjects);
            String new_student_endorsement = matrix_endorsement_determiner.getStudentEndorsement();//determine new student matrix endorsement

            String id_number = request.getSession().getAttribute("student_id_number").toString();
            int student_id = student_service.getStudentId(id_number);//get the id of the student who is trying to edit subjects

            //delete old student subjects 
            boolean is_subject_deleted = subject_service.deleteSubjects(student_id);//delete student subjects

            //get a list of new student subjects
            ArrayList<Subject> new_student_subjects = student_service.getStudentSubjects(new_selected_subjects);

            //add new subject to database if the old ones are deleted
            if (is_subject_deleted) {
                subject_service.addSubjects(new_student_subjects, student_id);
            }

            student_service.updateStudentEndorsement(student_id, new_student_endorsement);//update student endorsement to new endorsement
            student_service.updateStudentAPS(student_id, new_student_aps);//update student aps

            HttpSession session = request.getSession();
            session.setAttribute("student_aps", new_student_aps);
            session.setAttribute("student_endorsement", new_student_endorsement);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EditSubjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("/Views/Outputs/EditSubjectsOutcome.jsp").forward(request, response);
    }
}
