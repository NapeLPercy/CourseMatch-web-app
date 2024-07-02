package Entities;
import java.util.ArrayList;

public class University {
    
    private String name;
    private String abbreviation;
    private byte[] logo;
    ArrayList<Qualification> qualifications;

     public University(){}
     
    public University(String name, String abbreviation, byte[] logo, ArrayList<Qualification> qualifications) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.logo = logo;
        this.qualifications = qualifications;
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

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public ArrayList<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(ArrayList<Qualification> qualifications) {
        this.qualifications = qualifications;
    }
    
    
}
