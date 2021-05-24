import java.util.HashMap;

public class JobPosting {
    CompanyController companyController = new CompanyController();
    JobCategoryController jobCategoryController = new JobCategoryController();

    private int id;
    private Company company;
    private String title;
    private String description;
    private JobCategory jobCategory; //Change to JobCategory obj
    private String seniority;
    private int salary;
    private boolean fullTime;

    public JobPosting(Company company, String title, String description, JobCategory jobCategory, String seniority, int salary, boolean fullTime) {
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
        this.company = (companyController.getById(Integer.parseInt(map.get("company"))) != null)
                ? companyController.getById(Integer.parseInt(map.get("company"))) : new Company();
        this.title = map.get("title");
        this.description = map.get("description");
        this.jobCategory = (jobCategoryController.getById(Integer.parseInt(map.get("jobCategory"))) != null)
                ? jobCategoryController.getById(Integer.parseInt(map.get("jobCategory"))) : new JobCategory();
        this.seniority = map.get("seniority");
        this.salary = Integer.parseInt(map.get("salary"));
        this.fullTime = Boolean.parseBoolean(map.get("fullTime"));
    }

    public void setId(int id) { this.id = id; }

    ///////SETTERS///////////
    public void setCompany(Company company) { this.company = company; }

    public void setTitle(String title) { this.title = title; }

    public void setDescription(String description) { this.description = description; }

    public void setJobCategory(JobCategory jobCategory) { this.jobCategory = jobCategory; }

    public void setSeniority(String seniority) { this.seniority = seniority; }

    public void setSalary(int salary) { this.salary = salary; }

    public void setFullTime(boolean fullTime) { this.fullTime = fullTime; }

///////GETTERS///////////

    public int getId() { return id; }

    public Company getCompany() { return company; }

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public JobCategory getJobCategory() { return jobCategory; }

    public String getSeniority() { return seniority; }

    public int getSalary() { return salary; }

    public boolean isFullTime() { return fullTime; }

    public HashMap<String,String> getJobPostingMap() {
        HashMap<String,String> jobPostingMap = new HashMap<>();

        jobPostingMap.put("company", Integer.toString(getCompany().getId()));
        jobPostingMap.put("title", getTitle());
        jobPostingMap.put("description", getDescription());;
        jobPostingMap.put("jobCategory", Integer.toString(getJobCategory().getId()));
        jobPostingMap.put("seniority", getSeniority());
        jobPostingMap.put("salary", Integer.toString(getSalary()));
        jobPostingMap.put("fullTime", Boolean.toString(isFullTime()));

        return jobPostingMap;
    }

    @Override
    public String toString() {
        return "Company: " + this.getCompany().getName() + " \n " + "Title: " + this.getTitle() + " \n " + "Description: " + this.getDescription() + " \n "
                + "Job Category: " + this.getJobCategory().getRole() + " \n " + "Seniority: " + this.getSeniority()+ " \n "
                + "Salary: " + this.getSalary()+ " \n " + "Full Time: " + this.isFullTime() + "\n";
    }
}

