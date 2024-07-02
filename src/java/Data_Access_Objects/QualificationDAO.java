package Data_Access_Objects;

import Database_Manager.CourseMatchDB;
import Entities.PrerequisiteSubject;
import Entities.Qualification;
import Entities.Subject;
import java.sql.*;
import java.util.ArrayList;

public class QualificationDAO {

    private CourseMatchDB connection;

    public QualificationDAO() throws SQLException, ClassNotFoundException {
        connection = new CourseMatchDB();
    }

    //Get a list of all qualifications that a student qualifies for relative to their aps only
    //version2: filters using aps and Minimum_Endorsement
    public ArrayList<Qualification> filterQualificationsUsingAPS(int student_aps, String student_endorsement) throws SQLException, ClassNotFoundException {

        String sql = "SELECT q.Code, q.Name, q.Faculty, q.Minimum_APS, q.University_Abbreviation, ps.Subject_Name, ps.Minimum_Mark "
                + "FROM coursematchdb.qualification q "
                + "INNER JOIN coursematchdb.prerequisite_subject ps ON q.Code = ps.Qualification_Code "
                + "WHERE q.Minimum_APS <= ? AND q.Minimum_Endorsement = ?";

        PreparedStatement ps = connection.getConnection().prepareStatement(sql);
        ps.setInt(1, student_aps);
        ps.setString(2, student_endorsement);

        ResultSet rs = ps.executeQuery();

        ArrayList<Qualification> aps_qualified_qualifications = new ArrayList<>();
        // ArrayList<PrerequisiteSubject> prerequisite_subjects = new ArrayList<>();

        while (rs.next()) {
            // Get qualification details
            String q_code = rs.getString("code");
            String q_name = rs.getString("name");
            String faculty = rs.getString("faculty");
            String abbreviation = rs.getString("university_abbreviation");
            int minimum_aps = rs.getInt("minimum_aps");

            // Get prerequisite subject details
            String subject_name = rs.getString("subject_name");
            int minimum_mark = rs.getInt("minimum_mark");
            PrerequisiteSubject p_subject = new PrerequisiteSubject(subject_name, minimum_mark);

            // Check if qualification already exists in the list
            Qualification existingQualification = null;
            for (Qualification qualification : aps_qualified_qualifications) {
                if (qualification.getCode().equals(q_code)) {
                    existingQualification = qualification;
                    break;
                }
            }

            if (existingQualification == null) {
                // Create a new qualification if it does not exist
                ArrayList<PrerequisiteSubject> prerequisite_subjects = new ArrayList<>();
                prerequisite_subjects.add(p_subject);
                Qualification newQualification = new Qualification(q_name, q_code, minimum_aps, faculty + "#" + abbreviation, prerequisite_subjects);
                aps_qualified_qualifications.add(newQualification);
            } else {
                // Add the prerequisite subject to the existing qualification
                existingQualification.getPrerequisite_subjects().add(p_subject);
            }
        }

        return aps_qualified_qualifications;
    }//end
}
