package coursematch.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class APSCalculator {

    private Map<String, String[]> studentSubjectsInfo;

    public APSCalculator() {
    }

    public APSCalculator(Map<String, String[]> studentSubjectsInfo) {
        this.studentSubjectsInfo = studentSubjectsInfo;
    }

    public void setStudentSubjectsInfo(Map<String, String[]> studentSubjectsInfo) {
        this.studentSubjectsInfo = studentSubjectsInfo;
    }

    private Map<String, String[]> getThreeCompulsorySubjects() {

        Map<String, String[]> compulsory_subjects = new HashMap<>();

        for (Map.Entry<String, String[]> entry : studentSubjectsInfo.entrySet()) {

            String subject_name = entry.getKey();

            if (isCompulsorySubject(subject_name)) {
                compulsory_subjects.put(subject_name, entry.getValue());
            }
        }

        return compulsory_subjects;
    }//end

    //Return an arraylist of all elective subjects
    private ArrayList<String> getAllElectiveSubjects() {

        ArrayList elective_subjects = new ArrayList<>();

        for (Map.Entry<String, String[]> entry : studentSubjectsInfo.entrySet()) {

            String subject_name = entry.getKey();

            if (!isCompulsorySubject(subject_name) && !subject_name.equals("subjects[Life Orientation]")) {
                //elective_subjects.put(subject_name, entry.getValue());

                String[] subject_mark = entry.getValue();
                String subject_and_mark = subject_name + "#" + subject_mark[0];
                elective_subjects.add(subject_and_mark);
            }
        }
        return elective_subjects;
    }//end

    // Sorts elective marks from highest to lowest because 3 highest elective subjects will be used to calculate APS
    private ArrayList<String> sortElectiveSubjects() {
        ArrayList<String> elective_subjects = getAllElectiveSubjects();

        // Using Comparator to sort based on the marks
        Collections.sort(elective_subjects, (String first_subject_mark, String next_subject_mark) -> {
            int mark1 = Integer.parseInt(first_subject_mark.split("#")[1]);
            int mark2 = Integer.parseInt(next_subject_mark.split("#")[1]);

            return Integer.compare(mark2, mark1);
        });

        return elective_subjects;
    }//end

    //Determines three highest elective subjects from an arraylist and returns them as a map
    private Map<String, String[]> getThreeElectiveSubjects() {

        ArrayList<String> sorted_elective_subjects = sortElectiveSubjects();
        Map<String, String[]> three_elective_subjects = new HashMap<>();

        for (int i = 0; i <= 2; i++) {
            String subject_name = sorted_elective_subjects.get(i).split("#")[0];
            String subject_mark = sorted_elective_subjects.get(i).split("#")[1];
            String[] subject_mark_array = {subject_mark};

            three_elective_subjects.put(subject_name, subject_mark_array);
        }

        return three_elective_subjects;
    }//end

    //Combines 3 elective subjects and 3 compulsory subjects. This subjects will be used to calculate the APS
    private Map<String, String[]> getAPSSubjects() {

        Map<String, String[]> three_compulsory_subjects = getThreeCompulsorySubjects();

        Map<String, String[]> three_elective_subjects = getThreeElectiveSubjects();

        Map<String, String[]> six_aps_subjects = new HashMap<>();

        for (Map.Entry<String, String[]> entry : three_compulsory_subjects.entrySet()) {
            six_aps_subjects.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, String[]> entry : three_elective_subjects.entrySet()) {
            six_aps_subjects.put(entry.getKey(), entry.getValue());
        }

        return six_aps_subjects;
    }//end

    public int calculateAPS() {

        int student_aps = 0;

        Map<String, String[]> aps_subjects = getAPSSubjects();

        for (Map.Entry<String, String[]> entry : aps_subjects.entrySet()) {

            int subject_mark = Integer.parseInt(entry.getValue()[0]);
            int subject_level = getAchievementLevel(subject_mark);

            student_aps += subject_level;
        }

        return student_aps;
    }//end

    //determines a level for each subject percentage/mark
    private int getAchievementLevel(int subject_mark) {
        int level = 0;

        if (subject_mark >= 80) {
            level = 7;
        } else if (subject_mark >= 70) {
            level = 6;
        } else if (subject_mark >= 60) {
            level = 5;
        } else if (subject_mark >= 50) {
            level = 4;
        } else if (subject_mark >= 40) {
            level = 3;
        } else if (subject_mark >= 30) {
            level = 2;
        } else {
            level = 1;
        }
        return level;
    }//end

    //checks if a subject is Math, Home Language or First Additional Language. returns false if the subject is an Elective Subject
    //A subject can only be an elective or compulsory, not both.
    private boolean isCompulsorySubject(String subject_name) {
        int length = subject_name.length();
        return subject_name.substring(length - 3, length - 1).equals("HL")
                || subject_name.equals("subjects[Mathematics]") || subject_name.equals("subjects[Math Literacy]")
                || subject_name.substring(length - 4, length - 1).equals("FAL");
    }//end

    public Map<String, String[]> getEndorsementSubjects() {
        return getAPSSubjects();
    }
}
