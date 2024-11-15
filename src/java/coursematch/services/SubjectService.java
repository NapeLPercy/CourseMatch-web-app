package coursematch.services;

import coursematch.data_access_objects.SubjectDAO;
import coursematch.entities.Subject;
import coursematch.utils.SubjectManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SubjectService {

    private final SubjectManager subjectManager;
    private final SubjectDAO subjectDao;

    public SubjectService() throws SQLException, ClassNotFoundException {

        subjectManager = new SubjectManager();
        subjectDao = new SubjectDAO();
    }

    //Iterate through the map to get rid of all unselected subjects(Thier mark value will be 0)
    public Map<String, String[]> getSelectedSubjects(Map<String, String[]> allAvailableSubjects) {
        subjectManager.setSelectedSubjects(allAvailableSubjects);
        return subjectManager.extractSelectedSubjects(allAvailableSubjects);
    }//end

    public List<Subject> computeSubjectList(Map<String, String[]> selectedSubjects) {
        return subjectManager.computeSubjectList(selectedSubjects);
    }

    //Returns a map of subjects that will be used to calculate the aps.
    public Map<String, String[]> getApsSubjects() {
        return subjectManager.computeApsSubjects();
    }//end

    public boolean deleteSubjects(int student_id) throws SQLException, ClassNotFoundException {
        return subjectDao.deleteSubjects(student_id);
    }//end

    public boolean addSubjects(ArrayList<Subject> subjects_list, int student_id) throws SQLException, ClassNotFoundException {
        return subjectDao.addSubjects(subjects_list, student_id);
    }//end

}
