package coursematch.data_access_objects;

import coursematch.database_manager.CourseMatchDB;
import coursematch.entities.PrerequisiteSubject;
import coursematch.entities.Qualification;
import coursematch.entities.Subject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QualificationDAO {

    private CourseMatchDB connection;

    public QualificationDAO() throws SQLException, ClassNotFoundException {
        connection = new CourseMatchDB();
    }

    //Get a list of all qualifications that a student qualifies for relative to their aps only
    //version2: filters using aps and Minimum_Endorsement
    //version3: I really have to have consider this one carefully, IMPORTANT!!!
    public List<Qualification> filterQualificationsUsingAPS(int student_aps) throws SQLException, ClassNotFoundException {

        String sql = "SELECT q.Code, q.Name, q.Minimum_APS, q.Minimum_Endorsement, q.Minimum_Duration, ps.Subject_Name, ps.Minimum_Mark "
                + "FROM coursematchdb.qualification q "
                + "INNER JOIN coursematchdb.prerequisite_subject ps ON q.Code = ps.Qualification_Code "
                + "WHERE q.Minimum_APS <= ?";

        PreparedStatement ps = connection.getConnection().prepareStatement(sql);
        ps.setInt(1, student_aps);

        ResultSet rs = ps.executeQuery();

        List<Qualification> aps_qualified_qualifications = new ArrayList<>();

        while (rs.next()) {
            // Get qualification details
            String q_code = rs.getString("code");
            String q_name = rs.getString("name");
            int duration = rs.getInt("minimum_duration");
            int minimum_aps = rs.getInt("minimum_aps");
            String endorsement = rs.getString("minimum_endorsement");

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
                List<PrerequisiteSubject> prerequisite_subjects = new ArrayList<>();
                prerequisite_subjects.add(p_subject);
                Qualification newQualification = new Qualification(q_name, q_code, minimum_aps, duration, prerequisite_subjects);
                newQualification.setMinumumEndorsement(endorsement);
                
                aps_qualified_qualifications.add(newQualification);
            } else {
                // Add the prerequisite subject to the existing qualification
                existingQualification.getPrerequisiteSubjects().add(p_subject);
            }
        }

        return aps_qualified_qualifications;
    }//end
}
