package coursematch.entities;

import java.util.ArrayList;
import java.util.List;

public class University {

    private String name;
    private String abbreviation;
    private String url;
    List<Faculty> faculties;

    public University() {
        faculties = new ArrayList<>();
    }

    public University(String name, String abbreviation, String url, List<Faculty> faculties) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.url = url;
        this.faculties = faculties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

}
