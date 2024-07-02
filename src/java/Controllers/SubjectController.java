package Controllers;

import Services.SubjectService;
import Utils.APSCalculator;
import Utils.MatrixEndorsement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SubjectController extends HttpServlet {

    private SubjectService subject_service;
    private APSCalculator aps_calculator;
    private MatrixEndorsement matrix_endorsement_determiner;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String[]> parameter_map = request.getParameterMap();//get student subjects
        Map<String, String[]> selected_subjects = null;
        int student_aps = 0;
        String student_endorsement = "";

        try {

            aps_calculator = new APSCalculator();
            matrix_endorsement_determiner = new MatrixEndorsement();
            subject_service = new SubjectService(aps_calculator, matrix_endorsement_determiner);

            selected_subjects = subject_service.getSelectedSubjects(parameter_map);
            aps_calculator.set_student_subjects_info(selected_subjects);//set subjects that student have selected, they have to be filtered before aps

            student_aps = subject_service.get_aps_calculator().calculateAPS();

            //get all subjecst that a student selected, they will be used to determine a student's matrix endorsement
            //Unlike with APS, Life Orientation will be needed to calculate endorsment.
            //At this point, Life orientation can only be acquired from a map of all collected subjects 
            Map<String, String[]> endorsement_subjects = aps_calculator.getEndorsementSubjects();//identical to aps maps, add L.O to it
            endorsement_subjects.put("subjects[Life Orientation]", parameter_map.get("subjects[Life Orientation]"));//add LO to endorsement map
            matrix_endorsement_determiner.setStudent_subjects(endorsement_subjects);

            student_endorsement = matrix_endorsement_determiner.getStudentEndorsement();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("student_aps", student_aps);
        request.setAttribute("selected_subjects", selected_subjects);
        request.setAttribute("student_endorsement", student_endorsement);

        request.getRequestDispatcher("StudentController").forward(request, response);
    }
}
