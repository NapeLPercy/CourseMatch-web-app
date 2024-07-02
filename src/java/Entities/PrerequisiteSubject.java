package Entities;

public class PrerequisiteSubject {

    private String name;
    private int minimum_mark;

    public PrerequisiteSubject() {
    }

    public PrerequisiteSubject(String name, int minimum_mark) {
        this.name = name;
        this.minimum_mark = minimum_mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinimum_mark() {
        return minimum_mark;
    }

    public void setMinimum_mark(int minimum_mark) {
        this.minimum_mark = minimum_mark;
    }

}
