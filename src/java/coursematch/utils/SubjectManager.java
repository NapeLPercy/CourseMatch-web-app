package coursematch.utils;

import coursematch.entities.Subject;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class SubjectManager {

    private Map<String, String[]> selectedSubjects;

    public SubjectManager() {
    }

    public SubjectManager(Map<String, String[]> selectedSubjects) {
        this.selectedSubjects = selectedSubjects;
    }

    public Map<String, String[]> getSelectedSubjects() {
        return selectedSubjects;
    }

    public void setSelectedSubjects(Map<String, String[]> selectedSubjects) {
        this.selectedSubjects = selectedSubjects;
    }

    // Compute the six APS subjects: 3 compulsory + 3 highest electives
    public Map<String, String[]> computeApsSubjects() {
        Map<String, String[]> compulsorySubjects = collectThreeCompulsorySubjects();
        Map<String, String[]> electiveSubjects = collectThreeHighestElectives();

        Map<String, String[]> apsSubjects = new HashMap<>(compulsorySubjects);
        apsSubjects.putAll(electiveSubjects);

        return apsSubjects;
    }

    // Method to get 3 compulsory subjects
    private Map<String, String[]> collectThreeCompulsorySubjects() {
        return selectedSubjects.entrySet().stream()
                .filter(entry -> isCompulsorySubject(entry.getKey()))
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    // Determine and Collect 3 highest elective subjects
    private Map<String, String[]> collectThreeHighestElectives() {
        return selectedSubjects.entrySet().stream()
                .filter(entry -> !isCompulsorySubject(entry.getKey()) && !"subjects[Life Orientation]".equals(entry.getKey()))
                .sorted((e1, e2) -> Integer.compare(
                Integer.parseInt(e2.getValue()[0]),
                Integer.parseInt(e1.getValue()[0])
        ))
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    // Check if the subject is a compulsory one (Maths, Home Language, or First Additional Language)
    private boolean isCompulsorySubject(String subjectName) {
        return subjectName.endsWith("HL]") || subjectName.endsWith("FAL]")
                || subjectName.equals("subjects[Mathematics]") || subjectName.equals("subjects[Math Literacy]");
    }

    // Iterate through the map to get rid of all unselected subjects(Thier mark value will be 0)
    public Map<String, String[]> extractSelectedSubjects(Map<String, String[]> allAvailableSubjects) {

        Iterator<Map.Entry<String, String[]>> iterator = allAvailableSubjects.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String[]> entry = iterator.next();
            if (entry.getValue()[0].equals("0")) {
                iterator.remove();
            }
        }
        return allAvailableSubjects;
    }//end

    // Extract a subject name
    private String extractSubjectName(String subjectName) {

        for (int i = 0; i < subjectName.length(); i++) {
            if (subjectName.charAt(i) == '[') {
                subjectName = subjectName.substring(i + 1, subjectName.length() - 1);
            }
        }
        return subjectName;
    }

    // Turns Map into a Subject ArrayList
    public List<Subject> computeSubjectList(Map<String, String[]> selectedSubjects) {

        List<Subject> subjects = new ArrayList<>();

        for (Entry<String, String[]> entry : selectedSubjects.entrySet()) {

            String subjectName = extractSubjectName(entry.getKey());
            int subjectMark = Integer.parseInt(entry.getValue()[0]);

            subjects.add(new Subject(subjectName, subjectMark));
        }
        return subjects;
    }

}
