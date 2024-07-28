package coursematch.services;

import coursematch.data_access_objects.SubjectDAO;
import coursematch.entities.Subject;
import coursematch.utils.APSCalculator;
import coursematch.utils.MatrixEndorsement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class SubjectService {

    private final APSCalculator apsCalculator;
    private final MatrixEndorsement matrixEndorsementDeterminer;
    private final SubjectDAO subjectDao;

    public SubjectService(APSCalculator apsCalculator, MatrixEndorsement matrixEndorsementDeterminer) throws SQLException, ClassNotFoundException {

        this.apsCalculator = apsCalculator;
        this.matrixEndorsementDeterminer = matrixEndorsementDeterminer;
        subjectDao = new SubjectDAO();
    }

    public APSCalculator getApsCalculator() {
        return apsCalculator;
    }

    //iterate through the map to get rid of subjects that the student did not select(Thier mark value will be 0)
    public Map<String, String[]> getSelectedSubjects(Map<String, String[]> selected_subjects) {

        Iterator<Map.Entry<String, String[]>> iterator = selected_subjects.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String[]> entry = iterator.next();
            if (entry.getValue()[0].equals("0")) {
                iterator.remove();
            }
        }
        return selected_subjects;
    }//end

    public boolean deleteSubjects(int student_id) throws SQLException, ClassNotFoundException {
        return subjectDao.deleteSubjects(student_id);
    }//end

    public boolean addSubjects(ArrayList<Subject> subjects_list, int student_id) throws SQLException, ClassNotFoundException {
        return subjectDao.addSubjects(subjects_list, student_id);
    }
}
