package coursematch.utils;

import java.util.Map;

public class AdmissionPointScore {

    private Map<String, String[]> apsSubjects;

    public AdmissionPointScore() {
    }

    public AdmissionPointScore(Map<String, String[]> apsSubjects) {
        this.apsSubjects = apsSubjects;
    }

    public Map<String, String[]> getApsSubjects() {
        return apsSubjects;
    }

    public void setApsSubjects(Map<String, String[]> apsSubjects) {
        this.apsSubjects = apsSubjects;
    }

    // Method to calculate the APS score
    public int calculateAPS() {
        int totalAPS = 0;

        // Loop through all APS subjects and calculate the total APS score
        for (Map.Entry<String, String[]> entry : apsSubjects.entrySet()) {
            int subjectMark = Integer.parseInt(entry.getValue()[0]);
            totalAPS += calculateAchievementLevel(subjectMark);
        }

        return totalAPS;
    }

    // Determines the achievement level based on the subject percentage/mark
    private int calculateAchievementLevel(int subjectMark) {
        if (subjectMark >= 80) return 7;
        if (subjectMark >= 70) return 6;
        if (subjectMark >= 60) return 5;
        if (subjectMark >= 50) return 4;
        if (subjectMark >= 40) return 3;
        if (subjectMark >= 30) return 2;
        return 1; // Anything below 30% is considered level 1
    }
    
}
