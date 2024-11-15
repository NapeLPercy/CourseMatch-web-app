package coursematch.entities;
public class User {

    private String idNumber;
    private String name;
    private String surname;
    private String gender;
    private Account account;

    public User(String  idNumber, String name, String surname, String gender, Account account) {
        this. idNumber =  idNumber;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.account = account;
    }

    public String getIdNumber() {
        return  idNumber;
    }

    public void setIdNumber(String  idNumber) {
        this. idNumber =  idNumber;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
