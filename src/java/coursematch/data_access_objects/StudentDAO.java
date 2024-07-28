package coursematch.data_access_objects;

import coursematch.database_manager.CourseMatchDB;
import coursematch.entities.Student;
import coursematch.entities.Subject;
import coursematch.utils.Random;
import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {

    private final CourseMatchDB connection;

    public StudentDAO() throws SQLException, ClassNotFoundException {
        connection = new CourseMatchDB();

    }

    private boolean checkStudentIDExistence(int student_id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT student_id FROM coursematchdb.student "
                + "WHERE student_id = ?";

        PreparedStatement ps = connection.getConnection().prepareStatement(sql);
        ps.setInt(1, student_id);

        ResultSet rs = ps.executeQuery();
        return rs.next();

    }//end

    public final void addStudent(Student student, String id_number) throws SQLException, ClassNotFoundException {

        //determine student table id 
        Random random = new Random();
        int student_id = random.randomizeTableID();

        while (checkStudentIDExistence(student_id)) {
            student_id = random.randomizeTableID();
        }

        //add list of subjects to subject table
        SubjectDAO subject_dao = new SubjectDAO();
        ArrayList<Subject> subject_list = student.getSubject_list();

        String sql = "INSERT INTO coursematchdb.student "
                + "VALUES(?,?,?,?,?,?)";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, student_id);
            ps.setString(2, student.getName());
            ps.setString(3, student.getSurname());
            ps.setInt(4, student.getAPS());
            ps.setString(5, student.getEndorsement());
            ps.setString(6, id_number);//foreign key(User_id)

            ps.executeUpdate();
        }

        subject_dao.addSubjects(subject_list, student_id);

    }//end

    public int getStudentAPS(String id_number) throws SQLException, ClassNotFoundException {

        String sql = "SELECT aps FROM coursematchdb.student "
                + "WHERE user_id LIKE ? ";

        PreparedStatement ps = connection.getConnection().prepareStatement(sql);
        ps.setString(1, id_number);

        ResultSet rs = ps.executeQuery();
        int student_aps = 0;
        if (rs.next()) {
            student_aps = rs.getInt("APS");
        }

        return student_aps;
    }//end

    //select student subjects from database, this will be used to compare a course's prerequisite subjects to the subject that the student have submitted
    public ArrayList<Subject> selectAllStudentSubjects(String id_number) throws SQLException, ClassNotFoundException {

        String sql = "SELECT name, mark FROM coursematchdb.student st "
                + "INNER JOIN coursematchdb.subject su ON st.student_id = su.student_id "
                + "WHERE st.user_id = ?";

        PreparedStatement ps = connection.getConnection().prepareStatement(sql);
        ps.setString(1, id_number);

        ArrayList<Subject> student_subjects = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String name = rs.getString("name");
            int mark = rs.getInt("mark");

            Subject subject = new Subject(name, mark);
            student_subjects.add(subject);
        }

        return student_subjects;
    }//end

    public String getStudentEndorsement(String id_number) throws SQLException, ClassNotFoundException {

        String sql = "SELECT endorsement FROM coursematchdb.student "
                + "WHERE user_id LIKE ? ";

        PreparedStatement ps = connection.getConnection().prepareStatement(sql);
        ps.setString(1, id_number);

        ResultSet rs = ps.executeQuery();
        String student_endorsement = "";
        if (rs.next()) {
            student_endorsement = rs.getString("Endorsement");
        }

        return student_endorsement;
    }//end

    public int getStudentId(String id_number) throws SQLException, ClassNotFoundException {

        String sql = "SELECT student_id FROM coursematchdb.student "
                + "WHERE user_id LIKE ? ";

        PreparedStatement ps = connection.getConnection().prepareStatement(sql);
        ps.setString(1, id_number);

        ResultSet rs = ps.executeQuery();
        int student_id = 0;
        if (rs.next()) {
            student_id = rs.getInt("Student_Id");
        }

        return student_id;
    }//end

    public void updateStudentEndorsement(int student_id, String new_student_endorsement) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE coursematchdb.student "
                + "SET endorsement = ? "
                + "WHERE student_id = ? ";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {

            ps.setString(1, new_student_endorsement);
            ps.setInt(2, student_id);

            ps.executeUpdate();
        }

    }//end

    public void updateStudentAPS(int student_id, int new_student_aps) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE coursematchdb.student "
                + "SET aps = ? "
                + "WHERE student_id = ? ";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {

            ps.setInt(1, new_student_aps);
            ps.setInt(2, student_id);

            ps.executeUpdate();
        }
    }//end

    public Student getStudent(String id_number) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM coursematchdb.student "
                + "WHERE user_id LIKE ? ";

        PreparedStatement ps = connection.getConnection().prepareStatement(sql);
        ps.setString(1, id_number);

        ResultSet rs = ps.executeQuery();
        Student student = null;

        if (rs.next()) {
            student = new Student(
                    rs.getString("first_name"), 
                    rs.getString("last_name"),
                    rs.getInt("aps"), 
                    rs.getString("endorsement"), null);
        }

        return student;
    }//end

}
