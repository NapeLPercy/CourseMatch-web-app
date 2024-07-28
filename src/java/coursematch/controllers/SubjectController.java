package coursematch.controllers;

import coursematch.services.SubjectService;
import coursematch.utils.APSCalculator;
import coursematch.utils.MatrixEndorsement;
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

    private SubjectService subjectService;
    private APSCalculator apsCalculator;
    private MatrixEndorsement matrixEndorsementDeterminer;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String[]> parameter_map = request.getParameterMap();//Get all the subjects that a student can potentially selects

        try {

            apsCalculator = new APSCalculator();
            matrixEndorsementDeterminer = new MatrixEndorsement();
            subjectService = new SubjectService(apsCalculator, matrixEndorsementDeterminer);

            //Get a map of all selected subjects, the parameterMap contains all the subjects that a student can potentially select
            //Selected subjects will have a mark greater than 0%
            Map<String, String[]> selectedSubjects = subjectService.getSelectedSubjects(parameter_map);

            //Set subjects that student have selected, they have to be filtered before aps is calculated
            apsCalculator.setStudentSubjectsInfo(selectedSubjects);

            int studentAps = subjectService.getApsCalculator().calculateAPS();

            //Get all subjecst that a student selected, they will be used to determine a Student's Matrix Endorsement
            //Unlike with APS, Life Orientation will be needed to calculate a Student's endorsement.
            //At this point, Life Orientation can only be acquired from a map of all collected subjects 
            Map<String, String[]> endorsementSubjects = apsCalculator.getEndorsementSubjects();//Identical to aps map subjects, add L.O to it
            endorsementSubjects.put("subjects[Life Orientation]", parameter_map.get("subjects[Life Orientation]"));//Add LO to endorsement map
            matrixEndorsementDeterminer.setStudentSubjects(endorsementSubjects);

            //Calculate student endorsement based on set selected subjects
            String studentEndorsement = matrixEndorsementDeterminer.getStudentEndorsement();

            request.setAttribute("student_aps", studentAps);
            request.setAttribute("selected_subjects", selectedSubjects);
            request.setAttribute("student_endorsement", studentEndorsement);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("StudentController").forward(request, response);
    }
}
