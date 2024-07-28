package coursematch.services;

import coursematch.data_access_objects.QualificationDAO;
import coursematch.entities.PrerequisiteSubject;
import coursematch.entities.Qualification;
import coursematch.entities.Subject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class QualificationService {

    private final QualificationDAO qualificationDao;

    public QualificationService() throws SQLException, ClassNotFoundException {
        qualificationDao = new QualificationDAO();
    }

    public ArrayList<Qualification> filterQualificationsUsingAPS(int student_aps, String student_endorsement) throws SQLException, ClassNotFoundException {
        
        if(student_endorsement.equals("Barchelor")){//if a student acquired a Barchelor, they automatically qualify for Diploma
          student_endorsement = "Diploma";
        }
        return qualificationDao.filterQualificationsUsingAPS(student_aps, student_endorsement);
    }

//Check if student has prerequisite Subjects
//Compare student subjects to the subjects in a prerequisite subjects, if the prerequisite subjects are all matched, then student qualifies for the course 
//The method receives a list of all qualifications and subjects that a student has. It checks whether or not the student has a prerequisite subject for certain qualifications. If they don't, the qualification is removed from the list of qualifications they qualify for.
//getMinimum_mark()getPrerequisite_subjects()
    
public ArrayList<Qualification> filterQualificationsUsingPrerequisiteSubject(
            ArrayList<Qualification> apsQualifiedQualifications, ArrayList<Subject> studentSubjects) {

        Iterator<Qualification> qualificationIterator = apsQualifiedQualifications.iterator();

        while (qualificationIterator.hasNext()) {
            Qualification qualification = qualificationIterator.next();
            ArrayList<PrerequisiteSubject> prerequisiteSubjects = qualification.getPrerequisiteSubjects();//list of a qualification's prerequisite subjects

            boolean hasAllPrerequisites = true;

            for (PrerequisiteSubject prerequisite : prerequisiteSubjects) {//loop through a list of prerequisite subjects
                String prerequisiteName = prerequisite.getName();
                int prerequisiteMinMark = prerequisite.getMinimumMark();

                boolean found = false;

                for (Subject studentSubject : studentSubjects) {//loops through a list of student'
                    String studentSubjectName = studentSubject.getName();
                    int studentSubjectMark = studentSubject.getMark();

                    if (prerequisiteName.equals("Mathematics") || prerequisiteName.equals("Math Literacy")) {
                        if ((studentSubjectName.equals("Mathematics") || studentSubjectName.equals("Math Literacy"))
                                && studentSubjectMark >= prerequisiteMinMark) {
                            found = true;
                            break;
                        }
                    } else if (prerequisiteName.equals("English FAL") || prerequisiteName.equals("English HL")) {
                        if ((studentSubjectName.equals("English FAL") || studentSubjectName.equals("English HL"))
                                && studentSubjectMark >= prerequisiteMinMark) {
                            found = true;
                            break;
                        }
                    } else {
                        if (studentSubjectName.equalsIgnoreCase(prerequisiteName) && studentSubjectMark >= prerequisiteMinMark) {
                            found = true;
                            break;
                        }
                    }
                }

                if (!found) {
                    hasAllPrerequisites = false;
                    break;
                }
            }

            if (!hasAllPrerequisites) {
                qualificationIterator.remove();
            }
        }

        return apsQualifiedQualifications;
    }
    }
