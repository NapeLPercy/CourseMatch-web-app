package coursematch.utils;

import java.util.Map;

public class MatrixEndorsement {

    private static final int MIN_HOME_LANGUAGE_MARK = 40;
    private static final int MIN_HIGH_CREDIT_MARK = 40;
    private static final int MIN_OTHER_MARK = 30;
    private static final int MIN_SUBJECTS_PASS = 6;

    private Map<String, String[]> studentSubjects;

    public MatrixEndorsement(Map<String, String[]> studentSubjects) {
        this.studentSubjects = studentSubjects;
    }

    public MatrixEndorsement() {
    }

    public void setStudentSubjects(Map<String, String[]> studentSubjects) {
        this.studentSubjects = studentSubjects;
    }

    public String getStudentEndorsement() {
        if (isBachelor()) {
            return "Bachelor";
        } else if (isDiploma()) {
            return "Diploma";
        } else if (isCertificate()) {
            return "Higher Certificate";
        }
        return "Does not have NSC";
    }

    private boolean isBachelor() {
        return hasMinimumMarks(MIN_HIGH_CREDIT_MARK, 4)
                && hasMinimumMarks(MIN_OTHER_MARK, 2)
                && hasPassedMinimumSubjects();
    }

    private boolean isDiploma() {
        return hasMinimumMarks(MIN_HIGH_CREDIT_MARK, 4)
                && hasMinimumMarks(MIN_OTHER_MARK, 2)
                && hasPassedMinimumSubjects();
    }

    private boolean isCertificate() {
        return hasMinimumMarks(MIN_HIGH_CREDIT_MARK, 2)
                && hasMinimumMarks(MIN_OTHER_MARK, 3)
                && hasPassedMinimumSubjects();
    }

    private boolean hasMinimumMarks(int minimumMark, int requiredCount) {
        int count = 0;
        for (Map.Entry<String, String[]> entry : studentSubjects.entrySet()) {
            String subjectName = entry.getKey();
            int mark = Integer.parseInt(entry.getValue()[0]);

            if (isHomeLanguage(subjectName) && mark < MIN_HOME_LANGUAGE_MARK) {
                return false;
            } else if (mark >= minimumMark) {
                count++;
            }
        }
        return count >= requiredCount;
    }

    private boolean hasPassedMinimumSubjects() {
        long failedSubjects = studentSubjects.values().stream()
                .map(subject -> Integer.parseInt(subject[0]))
                .filter(mark -> mark < MIN_OTHER_MARK)
                .count();
        return studentSubjects.size() - failedSubjects >= MIN_SUBJECTS_PASS;
    }

    private boolean isHomeLanguage(String subjectName) {
        return subjectName.endsWith("HL");
    }

}

/*package coursematch.utils;

import java.util.Map;

public class MatrixEndorsement {

    private Map<String, String[]> studentSubjects;

    public MatrixEndorsement(Map<String, String[]> studentSubjects) {
        this.studentSubjects = studentSubjects;
    }

    public MatrixEndorsement() {
    }

    public void setStudentSubjects(Map<String, String[]> studentSubjects) {
        this.studentSubjects = studentSubjects;
    }

    public String getStudentEndorsement() {

        if (isBarchelor()) {
            return "Barchelor";
        } else if (isDiploma()) {
            return "Diploma";
        } else if (isCertificate()) {
            return "Higher Certificate";
        }
        return "Does not have NCS";
    }//end

    /*
   Requirements:  40% for home language
                         :  50% for four other high credit subjects (excluding Life Orientation)
                         :  At least 30% for two other subjects
                         :  Must pass 6 out of 7 subjects
     
    private boolean isBarchelor() {

        boolean is_barchelor = false;
        int over_fifty_perc = 0;
        int over_thirty_perc = 0;
        int failed_subject_number = 0;

        for (Map.Entry<String, String[]> entry : studentSubjects.entrySet()) {

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
     
    //v1 -> 
    private boolean isDiploma() {

        boolean is_diploma = false;
        int over_fourty_perc = 0;
        int over_thirty_perc = 0;
        int failed_subject_number = 0;

        for (Map.Entry<String, String[]> entry : studentSubjects.entrySet()) {

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
     
    private boolean isCertificate() {

        boolean is_certificate = false;
        int over_fourty_perc = 0;
        int over_thirty_perc = 0;
        int failed_subject_number = 0;

        for (Map.Entry<String, String[]> entry : studentSubjects.entrySet()) {

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
 */
