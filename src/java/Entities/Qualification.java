package Entities;

import java.util.ArrayList;

public class Qualification {

    private String name;
    private String code;
    private int minimun_aps;
    private String faculty;
    private ArrayList<PrerequisiteSubject> prerequisite_subjects;

    public Qualification() {
    }

    public Qualification(String name, String code,
            int minimun_aps, String faculty, ArrayList<PrerequisiteSubject> prerequisite_subjects) {
        this.name = name;
        this.code = code;
        this.minimun_aps = minimun_aps;
        this.faculty = faculty;
        this.prerequisite_subjects = prerequisite_subjects;
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

    public int getMinimun_aps() {
        return minimun_aps;
    }

    public void setMinimun_aps(int minimun_aps) {
        this.minimun_aps = minimun_aps;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public ArrayList<PrerequisiteSubject> getPrerequisite_subjects() {
        return prerequisite_subjects;
    }

    public void setPrerequisite_subjects(ArrayList<PrerequisiteSubject> prerequisite_subjects) {
        this.prerequisite_subjects = prerequisite_subjects;
    }

}
