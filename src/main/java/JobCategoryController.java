import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class JobCategoryController {

    private final HashMap<String, String> JobCategoryTable = new HashMap<>();

    public JobCategoryController() {
        JobCategoryTable.put("id", "INTEGER auto_increment PRIMARY KEY");
        JobCategoryTable.put("category", "VARCHAR2(50) NOT NULL");
        JobCategoryTable.put("role", "VARCHAR2(50) NOT NULL");
    }

    // Create a new job category
    public void create() {
        try {
            FactoryHandler.create("JobCategory", this.JobCategoryTable);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Table JobCategory already exists");
        }
    }

    // Insert new category
    public void insert(JobCategory jobCategory) {
        HashMap<String, String> jobCategoryData = jobCategory.getMap();

        try {
            FactoryHandler.insert("JobCategory", jobCategoryData);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Table JobCategory or current JobCategory not found");
        }
    }

    // Delete a job category
    public void delete(int jobCategoryId) {
        try {
            JobCategory jobCategory = getById(jobCategoryId);
            FactoryHandler.deleteRowById("JobCategory", Integer.toString(jobCategory.getId()));
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Table JobCategory or current JobCategory not found");
        }
    }

    // Update category
    public void update(JobCategory jobCategory) {
        int id = jobCategory.getId();

        try {
            FactoryHandler.update("JobCategory", id, jobCategory.getMap());
        } catch (SQLException | ClassNotFoundException sqlError) {
            System.out.println("Table JobCategory or column not found");
        }
    }

    // Get all categories
    public HashMap<Integer, JobCategory> getAll() {
        HashMap<Integer, JobCategory> jobCategories = new HashMap<>();

        HashMap<Integer, HashMap<String, String>> map = null;
        try {
            map = FactoryHandler.getAll("JobCategory", this.JobCategoryTable, "");
        } catch (SQLException sqlError) {
            sqlError.printStackTrace();
        } catch (ClassNotFoundException notFoundException) {
            System.out.println("Table JobCategory not found");
        }

        for (Integer id : map.keySet()) {
            jobCategories.put(id, new JobCategory(map.get(id)));
        }

        return jobCategories;
    }

    // Get category by id
    public JobCategory getById(int jobCategoryId) {
        try {
            return new JobCategory(FactoryHandler.getFiltering("JobCategory", this.JobCategoryTable, "id", Integer.toString(jobCategoryId))
                    .get(jobCategoryId));
        } catch (SQLException | ClassNotFoundException sqlError) {
            System.out.println("Table JobCategory not found or your filter is wrong!");
        }
        return null;
    }

    //Drop table
    public void drop() {
        try {
            FactoryHandler.drop("JobCategory");
        } catch (SQLException | ClassNotFoundException sqlError) {
            System.out.println("Table JobCategory not found");
        }
    }

    //Insert mock data
    public void mockData() throws SQLException, ClassNotFoundException, IOException, ParseException {

        drop();
        create();

        //JSON parser object to parse read file
        JSONParser parser = new JSONParser();

        JSONArray jsonJobCategories = (JSONArray) parser.parse(new FileReader("MockData/jobCategories.json"));

        for (Object category : jsonJobCategories) {
            //Setup Objects
            JSONObject jsonObj = (JSONObject) category;
            JobCategory jobCategory = new JobCategory(
                    (String) jsonObj.get("category"),
                    (String) jsonObj.get("role")
            );
            // Convert jobApplicant to hash map
            HashMap<String, String> jobCategoryData = jobCategory.getMap();
            // Insert data to DB
            FactoryHandler.insert("JobCategory", jobCategoryData);
        }

        System.out.println("Mock data insertion.\n");
        getAll();
    }
}
