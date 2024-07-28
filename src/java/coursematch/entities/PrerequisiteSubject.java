package coursematch.entities;

public class PrerequisiteSubject {

    private String name;
    private int minimumMark;

    public PrerequisiteSubject() {
    }

    public PrerequisiteSubject(String name, int minimumMark) {
        this.name = name;
        this.minimumMark = minimumMark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinimumMark() {
        return minimumMark;
    }

    public void setMinimumMark(int minimumMark) {
        this.minimumMark = minimumMark;
    }

}
