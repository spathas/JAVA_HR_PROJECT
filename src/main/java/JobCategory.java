import java.util.HashMap;

public class JobCategory {

    private int id;
    private String category;
    private String role;

    public JobCategory() {
        this.category = "Unknown category";
        this.role = "Unknown role";
    }

    public JobCategory(String category, String role) {
        this.category = category;
        this.role = role;
    }

    public JobCategory(HashMap<String, String> map) {
        this.id = Integer.parseInt(map.get("ID"));
        this.category = map.get("CATEGORY");
        this.role = map.get("ROLE");
    }


    ////////SETTERS////////
    public void setId(int id) { this.id = id; }

    public void setCategory(String category) { this.category = category; }

    public void setRole(String role) { this.role = role; }

    ////////GETTERS////////
    public int getId() { return id; }

    public String getCategory() { return category; }

    public String getRole() { return role; }

    public HashMap<String,String> getMap() {
        HashMap<String,String> jobCategoryMap = new HashMap<>();

        jobCategoryMap.put("CATEGORY", getCategory());
        jobCategoryMap.put("ROLE", getRole());

        return jobCategoryMap;
    }

    @Override
    public String toString() {
        return this.getCategory() + " | " + this.getRole();
    }
}
