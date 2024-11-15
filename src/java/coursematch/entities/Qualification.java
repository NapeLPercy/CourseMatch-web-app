package coursematch.entities;

import java.util.*;

public class Qualification {

    private String name;
    private String code;
    private int minimumAps;
    private String minumumEndorsement;
    private int duration;
    private List<PrerequisiteSubject> prerequisiteSubjects;

    public Qualification() {
    }

    public Qualification(String name, String code, int minimumAps, int duration, List<PrerequisiteSubject> prerequisiteSubjects) {
        this.name = name;
        this.code = code;
        this.minimumAps = minimumAps;
        this.duration = duration;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getMinumumEndorsement() {
        return minumumEndorsement;
    }

    public void setMinumumEndorsement(String minumumEndorsement) {
        this.minumumEndorsement = minumumEndorsement;
    }

    public List<PrerequisiteSubject> getPrerequisiteSubjects() {
        return prerequisiteSubjects;
    }

    public void setPrerequisiteSubjects(ArrayList<PrerequisiteSubject> prerequisiteSubjects) {
        this.prerequisiteSubjects = prerequisiteSubjects;
    }

}
