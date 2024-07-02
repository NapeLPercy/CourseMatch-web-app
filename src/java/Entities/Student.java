package Entities;

import java.util.ArrayList;

public class Student {

    private String name;
    private String surname;
    private int APS;
    private String endorsement;
    private ArrayList<Subject> subject_list;

    public Student(String name, String surname, int APS, String endorsement, ArrayList<Subject> subject_list) {
        this.name = name;
        this.surname = surname;
        this.APS = APS;
        this.endorsement = endorsement;
        this.subject_list = subject_list;
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

    public ArrayList<Subject> getSubject_list() {
        return subject_list;
    }

    public void setSubject_list(ArrayList<Subject> subject_list) {
        this.subject_list = subject_list;
    }

}
