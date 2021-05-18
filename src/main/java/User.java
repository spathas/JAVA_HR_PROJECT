import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class User {

    int id;
    String name;
    String surname;
    String email;
    String phone;
    String education;
    boolean works;

    public User() {
    }

    public User(int id, String name, String surname, String email, String phone, String education, boolean works) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.education = education;
        this.works = works;
    }

    public User(HashMap<String, String> map) {
        this.id = Integer.parseInt(map.get("id"));
        this.name = map.get("name");
        this.surname = map.get("surname");
        this.email = map.get("email");
        this.phone = map.get("phone");
        this.education = map.get("education");
        this.works = Boolean.parseBoolean(map.get("works"));
    }

    //////SETTERS///////
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setWorks(boolean works) {
        this.works = works;
    }

    ///////GETTERS///////
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getEducation() {
        return education;
    }

    public boolean isWorks() {
        return works;
    }
}
