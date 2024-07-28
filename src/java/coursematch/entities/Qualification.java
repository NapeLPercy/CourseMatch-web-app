package coursematch.entities;

import java.util.ArrayList;

public class Qualification {

    private String name;
    private String code;
    private int minimumAps;
    private String faculty;
    private ArrayList<PrerequisiteSubject> prerequisiteSubjects;

    public Qualification() {
    }

    public Qualification(String name, String code,
            int minimumAps, String faculty, ArrayList<PrerequisiteSubject> prerequisiteSubjects) {
        this.name = name;
        this.code = code;
        this.minimumAps = minimumAps;
        this.faculty = faculty;
        this.prerequisiteSubjects = prerequisiteSubjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getMinimumAps() {
        return minimumAps;
    }

    public void setMinimumAps(int minimumAps) {
        this.minimumAps = minimumAps;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public ArrayList<PrerequisiteSubject> getPrerequisiteSubjects() {
        return prerequisiteSubjects;
    }

    public void setPrerequisiteSubjects(ArrayList<PrerequisiteSubject> prerequisiteSubjects) {
        this.prerequisiteSubjects = prerequisiteSubjects;
    }

}
