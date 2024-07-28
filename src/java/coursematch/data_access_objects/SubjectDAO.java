package coursematch.data_access_objects;

import coursematch.database_manager.CourseMatchDB;
import coursematch.entities.Subject;
import coursematch.utils.Random;
import java.sql.*;
import java.util.ArrayList;

public class SubjectDAO {

    private final CourseMatchDB connection;

    public SubjectDAO() throws SQLException, ClassNotFoundException {
        connection = new CourseMatchDB();
    }

    private boolean checkSubjectIDExistence(int subject_id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT subject_id FROM coursematchdb.subject "
                + "WHERE subject_id = ?";

        PreparedStatement ps = connection.getConnection().prepareStatement(sql);
        ps.setInt(1, subject_id);

        ResultSet rs = ps.executeQuery();
        return rs.next();

    }//end

    public boolean addSubjects(ArrayList<Subject> subjects_list, int student_id) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO coursematchdb.subject "
                + "VALUES(?,?,?,?) ";

        try (PreparedStatement ps = connection.getConnection().prepareStatement(sql)) {

            for (Subject subject : subjects_list) {

                //determine subject id 
                Random random = new Random();
                int subject_id = random.randomizeTableID();

                while (checkSubjectIDExistence(subject_id)) {
                    subject_id = random.randomizeTableID();
                }

                ps.setInt(1, subject_id);
                ps.setString(2, subject.getName());
                ps.setInt(3, subject.getMark());
                ps.setInt(4, student_id);//foreign key

                ps.addBatch();
            }
            ps.executeBatch();

        }

        return true;
    }//end

    public boolean deleteSubjects(int student_id) throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM coursematchdb.subject "
                + "WHERE student_id = ?";

        PreparedStatement ps = connection.getConnection().prepareStatement(sql);
        ps.setInt(1, student_id);

        ps.executeUpdate();
        ps.close();
        return true;
    }//end

}
