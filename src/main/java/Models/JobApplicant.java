package Models;

import java.util.HashMap;

public class JobApplicant {
    Controllers.JobCategoryController jobCategoryController = new Controllers.JobCategoryController();

    private int id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String phone;
    private String education;
    private JobCategory jobCategory; // Change to JobCategory obj.
    private boolean works;

    public JobApplicant(String name, String surname, int age, String email, String phone, String education, JobCategory jobCategory, boolean works) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.education = education;
        this.jobCategory = jobCategory;
        this.works = works;
    }

    public JobApplicant(int id, String name, String surname, int age, String email, String phone, String education, JobCategory jobCategory, boolean works) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.education = education;
        this.jobCategory = jobCategory;
        this.works = works;
    }

    public JobApplicant(HashMap<String, String> map) {
        this.id = Integer.parseInt(map.get("ID"));
        this.name = map.get("NAME");
        this.surname = map.get("SURNAME");
        this.age = Integer.parseInt(map.get("AGE"));
        this.email = map.get("EMAIL");
        this.phone = map.get("PHONE");
        this.education = map.get("EDUCATION");
        this.jobCategory = (jobCategoryController.getById(Integer.parseInt(map.get("JOB_CATEGORY"))) != null)
                ? jobCategoryController.getById(Integer.parseInt(map.get("JOB_CATEGORY"))) : new JobCategory();
        this.works = Boolean.parseBoolean(map.get("WORKS"));
    }

    ////////SETTERS///////
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) { this.surname = surname; }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEducation(String education) { this.education = education; }

    public void setJobCategory(JobCategory jobCategory) {
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

    public String getEducation() { return education; }

    public JobCategory getJobCategory() {
        return jobCategory;
    }

    public boolean isWorks() {
        return works;
    }

    public HashMap<String,String> getMap() {
        HashMap<String,String> applicantMAp = new HashMap<>();

        if(getId() > 0) applicantMAp.put("ID", Integer.toString(getId()));
        applicantMAp.put("NAME", getName());
        applicantMAp.put("SURNAME", getSurname());
        applicantMAp.put("AGE", Integer.toString(getAge()));
        applicantMAp.put("EMAIL", getEmail());
        applicantMAp.put("PHONE", getPhone());
        applicantMAp.put("EDUCATION", getEducation());
        applicantMAp.put("JOB_CATEGORY", Integer.toString(getJobCategory().getId()));
        applicantMAp.put("WORKS", Boolean.toString(isWorks()));

        return applicantMAp;
    }

    @Override
    public String toString() {
        return this.getName() + " | " + this.getSurname()+ " | " + this.getAge() + " | " + this.getEmail() + " | " +
                this.getPhone() + " | " + this.getEducation() + " | " +  this.getJobCategory().getRole() + " | " + this.isWorks();
    }
}
