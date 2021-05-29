import java.util.HashMap;

public class JobCategory {

    private int id;
    private String category;
    private String role;
    private String seniority;

    public JobCategory() {
        this.category = "Unknown category";
        this.role = "Unknown role";
        this.seniority = "Unknown seniority";
    }

    public JobCategory(String category, String role, String seniority) {
        this.category = category;
        this.role = role;
        this.seniority = seniority;
    }

    public JobCategory(HashMap<String, String> map) {
        this.id = Integer.parseInt(map.get("ID"));
        this.category = map.get("CATEGORY");
        this.role = map.get("ROLE");
        this.seniority = map.get("SENIORITY");
    }

    ////////GETTERS////////
    public int getId() { return id; }

    public String getCategory() { return category; }

    public String getRole() { return role; }

    public String getSeniority() { return seniority; }

    public HashMap<String,String> getMap() {
        HashMap<String,String> jobCategoryMap = new HashMap<>();

        jobCategoryMap.put("CATEGORY", getCategory());
        jobCategoryMap.put("ROLE", getRole());
        jobCategoryMap.put("SENIORITY", getSeniority());

        return jobCategoryMap;
    }

    @Override
    public String toString() {
        return this.getCategory() + " | " + this.getRole() + " | " + this.getSeniority();
    }
}
