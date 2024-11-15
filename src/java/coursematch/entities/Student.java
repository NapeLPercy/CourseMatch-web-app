package coursematch.entities;

import java.util.List;

public class Student {

    private String name;
    private String surname;
    private int APS;
    private String endorsement;
    private List<Subject> subjects;
    private int studentId;

    public Student(String name, String surname, int APS, String endorsement, List<Subject> subjects) {
        this.name = name;
        this.surname = surname;
        this.APS = APS;
        this.endorsement = endorsement;
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAPS() {
        return APS;
    }

    public void setAPS(int APS) {
        this.APS = APS;
    }

    public String getEndorsement() {
        return endorsement;
    }

    public void setEndorsement(String endorsement) {
        this.endorsement = endorsement;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
