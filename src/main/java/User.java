import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class User {

    int id;
    String name;
    String surname;
    String age;
    String email;
    String phone;
    String education;
    boolean works;

    public User() {
    }

    public User(String name, String surname, String age, String email, String phone, String education, boolean works) {
        this.name = name;
        this.surname = surname;
        this.age = age;
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

    ////////SETTERS///////
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(String age) {
        this.age = age;
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
    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAge() {
        return age;
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

    public HashMap<String,String> getUserMap() {
        HashMap<String,String> userMap = new HashMap<>();

        userMap.put("name", getName());
        userMap.put("surname", getSurname());
        userMap.put("age", getAge());
        userMap.put("email", getEmail());
        userMap.put("phone", getPhone());
        userMap.put("education", getEducation());
        userMap.put("works", Boolean.toString(isWorks()));

        return userMap;
    }

    @Override
    public String toString() {
        return " || " + this.getName() + " | " + this.getSurname()+ " | " + this.getAge() + " | " + this.getEmail() + " | " +
                this.getPhone() + " | " + this.getEducation() + " | " + this.isWorks();
    }
}
