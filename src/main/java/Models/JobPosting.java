package Models;

import java.util.HashMap;

public class JobPosting {
    Controllers.CompanyController companyController = new Controllers.CompanyController();
    Controllers.JobCategoryController jobCategoryController = new Controllers.JobCategoryController();

    private int id;
    private Company company;
    private String title;
    private String description;
    private JobCategory jobCategory;
    private int salary;
    private boolean fullTime;

    public JobPosting(Company company, String title, String description, JobCategory jobCategory, int salary, boolean fullTime) {
        this.company = company;
        this.title = title;
        this.description = description;
        this.jobCategory = jobCategory;
        this.salary = salary;
        this.fullTime = fullTime;
    }

    public JobPosting(int id, Company company, String title, String description, JobCategory jobCategory, int salary, boolean fullTime) {
        this.id = id;
        this.company = company;
        this.title = title;
        this.description = description;
        this.jobCategory = jobCategory;
        this.salary = salary;
        this.fullTime = fullTime;
    }

    public JobPosting(HashMap<String, String> map) {
        this.id = Integer.parseInt(map.get("ID"));
        this.company = (companyController.getById(Integer.parseInt(map.get("COMPANY"))) != null)
                ? companyController.getById(Integer.parseInt(map.get("COMPANY"))) : new Company();
        this.title = map.get("TITLE");
        this.description = map.get("DESCRIPTION");
        this.jobCategory = (jobCategoryController.getById(Integer.parseInt(map.get("JOB_CATEGORY"))) != null)
                ? jobCategoryController.getById(Integer.parseInt(map.get("JOB_CATEGORY"))) : new JobCategory();
        this.salary = Integer.parseInt(map.get("SALARY"));
        this.fullTime = Boolean.parseBoolean(map.get("FULL_TIME"));
    }

///////GETTERS///////////

    public int getId() { return id; }

    public Company getCompany() { return company; }

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public JobCategory getJobCategory() { return jobCategory; }

    public int getSalary() { return salary; }

    public boolean isFullTime() { return fullTime; }

    public HashMap<String,String> getMap() {
        HashMap<String,String> jobPostingMap = new HashMap<>();

        if(getId() > 0) jobPostingMap.put("ID", Integer.toString(getId()));
        jobPostingMap.put("COMPANY", Integer.toString(getCompany().getId()));
        jobPostingMap.put("TITLE", getTitle());
        jobPostingMap.put("DESCRIPTION", getDescription());;
        jobPostingMap.put("JOB_CATEGORY", Integer.toString(getJobCategory().getId()));
        jobPostingMap.put("SALARY", Integer.toString(getSalary()));
        jobPostingMap.put("FULL_TIME", Boolean.toString(isFullTime()));

        return jobPostingMap;
    }

    @Override
    public String toString() {
        return "Company: " + this.getCompany().getName() + " \n " + "Title: " + this.getTitle() + " \n " + "Description: " + this.getDescription() + " \n "
                + "Job Category: " + this.getJobCategory().toString() + " \n " + "Salary: " + this.getSalary()+ " \n "
                + "Full Time: " + this.isFullTime() + "\n";
    }
}

