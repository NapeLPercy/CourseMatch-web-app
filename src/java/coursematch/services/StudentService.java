package coursematch.services;

import coursematch.data_access_objects.StudentDAO;
import coursematch.entities.Student;
import coursematch.entities.Subject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class StudentService {

    private final StudentDAO studentDao;

    public StudentService() throws SQLException, ClassNotFoundException {
        studentDao = new StudentDAO();
    }

    public boolean addStudent(Student student, String id_number) throws SQLException, ClassNotFoundException {

        if (!student.getEndorsement().equals("You do not have matrix")) {
           studentDao.addStudent(student, id_number);
        } else {
            return false;
        }
        return true;
    }//end

    //returns a list of all the subjects that a student selected. A subject has a name and mark
    public ArrayList<Subject> getStudentSubjects(Map<String, String[]> selected_subjects) {

        ArrayList<Subject> student_subjects = new ArrayList<>();

        for (Map.Entry<String, String[]> entry : selected_subjects.entrySet()) {

            String subject_name = entry.getKey().substring(9, entry.getKey().length() - 1);
            //subject[name]
            int subject_mark = Integer.parseInt(entry.getValue()[0]);
            Subject subject = new Subject(subject_name, subject_mark);
            student_subjects.add(subject);

        }

        return student_subjects;
    }//end

    public int getStudentAPS(String id_number) throws SQLException, ClassNotFoundException {
        return studentDao.getStudentAPS(id_number);
    }//end

    public ArrayList<Subject> selectAllStudentSubjects(String id_number) throws SQLException, ClassNotFoundException {
        return studentDao.selectAllStudentSubjects(id_number);
    }//end

    public String getStudentEndorsement(String id_number) throws SQLException, ClassNotFoundException {
        return studentDao.getStudentEndorsement(id_number);
    }//end

    public int getStudentId(String id_number) throws SQLException, ClassNotFoundException {
        return studentDao.getStudentId(id_number);
    }//end

    public void updateStudentEndorsement(int student_id, String new_student_endorsement) throws SQLException, ClassNotFoundException {
        studentDao.updateStudentEndorsement(student_id, new_student_endorsement);
    }//end

    public void updateStudentAPS(int student_id, int new_student_aps) throws SQLException, ClassNotFoundException {
        studentDao.updateStudentAPS(student_id, new_student_aps);
    }//end

    public Student getStudent(String id_number) throws SQLException, ClassNotFoundException {
        return studentDao.getStudent(id_number);
    }//end
    
}
