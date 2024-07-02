package Services;

import Data_Access_Objects.SubjectDAO;
import Entities.Subject;
import Utils.APSCalculator;
import Utils.MatrixEndorsement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class SubjectService {

    private final APSCalculator aps_calculator;
    private final MatrixEndorsement matrix_endorsement_determiner;
    private final SubjectDAO subject_dao;

    public SubjectService(APSCalculator aps_calculator, MatrixEndorsement matrix_endorsement_determiner) throws SQLException, ClassNotFoundException {

        this.aps_calculator = aps_calculator;
        this.matrix_endorsement_determiner = matrix_endorsement_determiner;
        subject_dao = new SubjectDAO();
    }

    public APSCalculator get_aps_calculator() {
        return aps_calculator;
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
        return subject_dao.deleteSubjects(student_id);
    }//end

    public boolean addSubjects(ArrayList<Subject> subjects_list, int student_id) throws SQLException, ClassNotFoundException {
        return subject_dao.addSubjects(subjects_list, student_id);
    }

    /* public ArrayList<Subject> getStudentSubjects(Map<String, String[]> student_subjects_info) {

        ArrayList<Subject> student_subjects = new ArrayList<>();

        for (Map.Entry<String, String[]> entry : student_subjects_info.entrySet()) {

            String paramName = entry.getKey();

            if (paramName.startsWith("subjects[")) {

                int subject_mark = Integer.parseInt(entry.getValue()[0]);
                String subject_name = paramName.substring(9, paramName.length() - 1);

                String subject_code = getSubjectCode(subject_name);

                Subject subject = new Subject(subject_name, subject_code, subject_mark);
                student_subjects.add(subject);
            }
        }

        return student_subjects;
    }//end

    /*Extract student marks from a map of subject_name(key) and subject_mark(value)
    //populates an arraylist with subject_marks inorder to determine student APS
    public ArrayList<String> getStudentMarks(Map<String, String[]> student_subjects_info) {

        ArrayList<String> student_marks = new ArrayList<>();

        for (Map.Entry<String, String[]> entry : student_subjects_info.entrySet()) {

            String paramName = entry.getKey();

            if (paramName.startsWith("subjects[")) {

                String subject_mark = entry.getValue()[0];

                student_marks.add(subject_mark);
            }
        }

        return student_marks;
    }//end

    private String getSubjectCode(String subject_name) {

        String subject_code = Character.toString(subject_name.charAt(0));

        if (!subject_name.contains(" ")) {

            subject_code = subject_name.substring(0, 3);

        } else {
            String last_two_characters = subject_name.substring(subject_name.length() - 2, subject_name.length());

            if (last_two_characters.equals("HL")) {
                subject_code += last_two_characters;
            } else {
                subject_code = subject_name.substring(0, 3);
            }
        }

        subject_code = subject_code.toUpperCase();

        return subject_code;
    }*/
}
