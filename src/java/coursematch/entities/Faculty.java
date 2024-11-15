package coursematch.entities;

import coursematch.utils.tut.FacultyOperations;
import java.util.ArrayList;
import java.util.List;

public abstract class Faculty implements FacultyOperations {

    private List<Qualification> qualifications;
    private String name;

    public Faculty() {
        qualifications = new ArrayList<>();
    }

    public Faculty(List<Qualification> qualifications, String name) {
        this.qualifications = qualifications;
        this.name = name;
    }

    public List<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
