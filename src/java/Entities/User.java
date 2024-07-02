package Entities;
public class User {

    private String id_number;
    private String name;
    private String surname;
    private String gender;
    private Account account;

    public User(String id_number, String name, String surname, String gender, Account account) {
        this.id_number = id_number;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.account = account;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
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
