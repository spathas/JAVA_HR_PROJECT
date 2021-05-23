import java.util.HashMap;

public class JobPosting {
    CompanyController companyController = new CompanyController();

    private int id;
    private Company company;
    private String title;
    private Long description;
    private String jobCategory; //Change to JobCategory obj
    private String seniority;
    private String salary;
    private boolean fullTime;

    public JobPosting(Company company, String title, Long description, String jobCategory, String seniority, String salary, boolean fullTime) {
        this.company = company;
        this.title = title;
        this.description = description;
        this.jobCategory = jobCategory;
        this.seniority = seniority;
        this.salary = salary;
        this.fullTime = fullTime;
    }

    public JobPosting(HashMap<String, String> map) {
        this.id = Integer.parseInt(map.get("id"));
        this.company = companyController.getByName(map.get("company"));
        this.title = map.get("title");
        this.description = Long.parseLong(map.get("description"));;
        this.jobCategory = map.get("jobCategory");
        this.seniority = map.get("seniority");
        this.salary = map.get("salary");
        this.fullTime = Boolean.parseBoolean(map.get("fullTime"));
    }

    public void setId(int id) { this.id = id; }

    ///////SETTERS///////////
    public void setCompany(Company company) { this.company = company; }

    public void setTitle(String title) { this.title = title; }

    public void setDescription(Long description) { this.description = description; }

    public void setJobCategory(String jobCategory) { this.jobCategory = jobCategory; }

    public void setSeniority(String seniority) { this.seniority = seniority; }

    public void setSalary(String salary) { this.salary = salary; }

    public void setFullTime(boolean fullTime) { this.fullTime = fullTime; }

///////GETTERS///////////

    public int getId() { return id; }

    public Company getCompany() { return company; }

    public String getTitle() { return title; }

    public Long getDescription() { return description; }

    public String getJobCategory() { return jobCategory; }

    public String getSeniority() { return seniority; }

    public String getSalary() { return salary; }

    public boolean isFullTime() { return fullTime; }

    public HashMap<String,String> getJobPostingMap() {
        HashMap<String,String> jobPostingMap = new HashMap<>();

        this.company = companyController.getByName(jobPostingMap.get("company"));
        this.title = jobPostingMap.get("title");
        this.description = Long.parseLong(jobPostingMap.get("description"));;
        this.jobCategory = jobPostingMap.get("jobCategory");
        this.seniority = jobPostingMap.get("seniority");
        this.salary = jobPostingMap.get("salary");
        this.fullTime = Boolean.parseBoolean(jobPostingMap.get("fullTime"));

        return jobPostingMap;
    }
}

