package coursematch.services;

import coursematch.data_access_objects.StudentDAO;
import coursematch.entities.Student;
import coursematch.entities.Subject;
import coursematch.utils.AdmissionPointScore;
import coursematch.utils.MatrixEndorsement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentService {

    private final StudentDAO studentDao;
    private MatrixEndorsement matrixEndorsement;
    private AdmissionPointScore aps;
    private SubjectService subjectService;

    public StudentService() throws SQLException, ClassNotFoundException {
        studentDao = new StudentDAO();
        matrixEndorsement = new MatrixEndorsement();
        aps = new AdmissionPointScore();
        subjectService = new SubjectService();
    }

    public SubjectService getSubjectService() {
        return subjectService;
    }

    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    public String computeStudentEndorsement(Map<String, String[]> selectedSubjects) {
        matrixEndorsement.setStudentSubjects(selectedSubjects);
        return matrixEndorsement.getStudentEndorsement();
    }//end

    public int calculateStudentAps(Map<String, String[]> apsSubjects) {
        aps.setApsSubjects(apsSubjects);
        return aps.calculateAPS();
    }//end

    //DAO Related Operations
    public boolean addStudent(Student student, String id_number) throws SQLException, ClassNotFoundException {

        if (!student.getEndorsement().equals("You do not have matrix")) {
            studentDao.addStudent(student, id_number);
        } else {
            return false;
        }
        return true;
    }//end

    public int getStudentAPS(String id_number) throws SQLException, ClassNotFoundException {
        return studentDao.getStudentAPS(id_number);
    }//end

    public List<Subject> selectAllStudentSubjects(String id_number) throws SQLException, ClassNotFoundException {
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

    public Student getStudent(String idNumber) {
        Student student = null;

        try {
            student = studentDao.getStudent(idNumber);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
    }//end

}
