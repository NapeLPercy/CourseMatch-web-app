package coursematch.controllers;

import coursematch.entities.Subject;
import coursematch.services.SubjectService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.*;

public class SubjectController extends HttpServlet {

    private SubjectService subjectService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            subjectService = new SubjectService();

            Map<String, String[]> allAvailableSubjects = request.getParameterMap();

            // Subjects selected by student will have a mark greater than 0%
            Map<String, String[]> selectedSubjects = subjectService.getSelectedSubjects(allAvailableSubjects);

            // Returns selected subjects as a list
            List<Subject> subjectList = subjectService.computeSubjectList(selectedSubjects);

            //Get all subjects that will be used to calculate the APS
            Map<String, String[]> apsSubjects = subjectService.getApsSubjects();

            request.setAttribute("selectedSubjects", selectedSubjects);
            request.setAttribute("subjectList", subjectList);
            request.setAttribute("apsSubjects", apsSubjects);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("StudentController").forward(request, response);
    }
}
