import java.util.HashMap;

public class JobApplicant {

    private int id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String phone;
    private String jobCategory; // Change to JobCategory obj.
    private boolean works;

    public JobApplicant(String name, String surname, int age, String email, String phone, String jobCategory, boolean works) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.jobCategory = jobCategory;
        this.works = works;
    }

    public JobApplicant(HashMap<String, String> map) {
        this.id = Integer.parseInt(map.get("id"));
        this.name = map.get("name");
        this.surname = map.get("surname");
        this.age = Integer.parseInt(map.get("age"));
        this.email = map.get("email");
        this.phone = map.get("phone");
        this.jobCategory = map.get("education");
        this.works = Boolean.parseBoolean(map.get("works"));
    }

    ////////SETTERS///////
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
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

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public boolean isWorks() {
        return works;
    }

    public HashMap<String,String> getJobApplicantMap() {
        HashMap<String,String> applicantMAp = new HashMap<>();

        applicantMAp.put("name", getName());
        applicantMAp.put("surname", getSurname());
        applicantMAp.put("age", Integer.toString(getAge()));
        applicantMAp.put("email", getEmail());
        applicantMAp.put("phone", getPhone());
        applicantMAp.put("education", getJobCategory());
        applicantMAp.put("works", Boolean.toString(isWorks()));

        return applicantMAp;
    }

    @Override
    public String toString() {
        return " || " + this.getName() + " | " + this.getSurname()+ " | " + this.getAge() + " | " + this.getEmail() + " | " +
                this.getPhone() + " | " + this.getJobCategory() + " | " + this.isWorks();
    }
}
