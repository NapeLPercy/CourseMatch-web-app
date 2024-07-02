package Utils;

import java.util.Map;

public class MatrixEndorsement {

    private Map<String, String[]> student_subjects;

    public MatrixEndorsement(Map<String, String[]> student_subjects) {
        this.student_subjects = student_subjects;
    }

    public MatrixEndorsement() {
    }

    public void setStudent_subjects(Map<String, String[]> student_subjects) {
        this.student_subjects = student_subjects;
    }

    public String getStudentEndorsement() {

        String endorsement = "You do not have matrix";

        if (isBarchelor()) {
            endorsement = "Barchelor";
        } else if (isDiploma()) {
            endorsement = "Diploma";
        } else if (isCertificate()) {
            endorsement = "Higher Certificate";
        }
        return endorsement;
    }//end

    /*
   Requirements:  40% for home language
                         :  50% for four other high credit subjects (excluding Life Orientation)
                         :  At least 30% for two other subjects
                         :  Must pass 6 out of 7 subjects
     */
    private boolean isBarchelor() {

        boolean is_barchelor = false;
        int over_fifty_perc = 0;
        int over_thirty_perc = 0;
        int failed_subject_number = 0;

        for (Map.Entry<String, String[]> entry : student_subjects.entrySet()) {

            String name = entry.getKey();
            int mark = Integer.parseInt(entry.getValue()[0]);

            //checks if subject is home language
            if (name.substring(name.length() - 3, name.length() - 1).equals("HL")) {
                if (mark < 40) {
                    return false;
                }
            } else {

                if (!name.equals("subjects[Life Orientation]") && mark >= 50) {
                    over_fifty_perc++;
                } else if (mark >= 30) {
                    over_thirty_perc++;
                } else {
                    failed_subject_number++;
                }
            }
        }

        if (over_fifty_perc >= 4 && over_thirty_perc <= 2 && failed_subject_number < 2) {
            is_barchelor = true;
        }
        return is_barchelor;
    }//end

    /*Determines if Student acquired a Diploma
    Requirements:  40% for home language
                          :  40% for four other high credit subjects (excluding Life Orientation)
                          :  30% for two other subjects
                          :  Must pass 6 out of 7 subjects
     */
    private boolean isDiploma() {

        boolean is_diploma = false;
        int over_fourty_perc = 0;
        int over_thirty_perc = 0;
        int failed_subject_number = 0;

        for (Map.Entry<String, String[]> entry : student_subjects.entrySet()) {

            String name = entry.getKey();
            int mark = Integer.parseInt(entry.getValue()[0]);

            //checks if subject is home language
            if (name.substring(name.length() - 3, name.length() - 1).equals("HL")) {
                if (mark < 40) {
                    return false;
                }
            } else {

                if (!name.equals("subjects[Life Orientation]") && mark >= 40) {
                    over_fourty_perc++;
                } else if (mark >= 30) {
                    over_thirty_perc++;
                } else {
                    failed_subject_number++;
                }
            }

        }

        if (over_fourty_perc >= 4 && over_thirty_perc <= 2 && failed_subject_number < 2) {
            is_diploma = true;
        }
        return is_diploma;
    }//end

    /* Determine if student acquired a Higher Certificate
    Requirements:  40% for home language
                          :  40% in two other subjects
                          : 30% for three other subjects
                          : Must pass 6 out of 7 subjects
     */
    private boolean isCertificate() {

        boolean is_certificate = false;
        int over_fourty_perc = 0;
        int over_thirty_perc = 0;
        int failed_subject_number = 0;

        for (Map.Entry<String, String[]> entry : student_subjects.entrySet()) {

            String name = entry.getKey();
            int mark = Integer.parseInt(entry.getValue()[0]);

            //checks if subject is home language
            if (name.substring(name.length() - 3, name.length() - 1).equals("HL")) {
                if (mark < 40) {
                    return false;
                }
            } else {

                if (mark >= 40) {
                    over_fourty_perc++;
                } else if (mark >= 30) {
                    over_thirty_perc++;
                } else {
                    failed_subject_number++;
                }
            }

        }

        if (over_fourty_perc >= 2 && over_thirty_perc >= 3 && failed_subject_number < 2) {
            is_certificate = true;
        }
        return is_certificate;
    }//end

}
