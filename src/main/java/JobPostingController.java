import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class JobPostingController {

    private final HashMap<String, String> JobPostingTable = new HashMap<>();

    public JobPostingController() {
        JobPostingTable.put("ID", "INTEGER auto_increment PRIMARY KEY");
        JobPostingTable.put("COMPANY", "INTEGER NOT NULL");
        JobPostingTable.put("TITLE", "VARCHAR2(50) NOT NULL");
        JobPostingTable.put("DESCRIPTION", "VARCHAR2(4000) NOT NULL");
        JobPostingTable.put("JOB_CATEGORY", "INTEGER NOT NULL"); //Change to object
        JobPostingTable.put("SENIORITY", "VARCHAR2(10)");
        JobPostingTable.put("SALARY", "INTEGER");
        JobPostingTable.put("FULL_TIME", "BOOLEAN");
    }

    // Create a new job posting
    public void create() {
        try {
            FactoryHandler.create("JobPosting", this.JobPostingTable);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Table JobPosting already exists");
        }
    }

    // Insert new posting
    public void insert(JobPosting jobPosting) {
        HashMap<String, String> jobPostingData = jobPosting.getMap();

        try {
            FactoryHandler.insert("JobPosting", jobPostingData);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Table JobPosting or current JobPosting not found");
        }
    }

    // Delete a job posting
    public void delete(int jobPostingId) {
        try {
            JobPosting jobPosting = getById(jobPostingId);
            FactoryHandler.deleteRowById("JobPosting", Integer.toString(jobPosting.getId()));
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Table JobPosting or current JobPosting not found");
        }
    }

    // Update posting
    public void update(JobPosting jobPosting) {
        int id = jobPosting.getId();

        try {
            FactoryHandler.update("JobPosting", id, jobPosting.getMap());
        } catch (SQLException | ClassNotFoundException sqlError) {
            System.out.println("Table JobPosting or column not found");
        }
    }

    // Get all postings
    public HashMap<Integer, JobPosting> getAll() {
        HashMap<Integer, JobPosting> jobPostings = new HashMap<>();

        HashMap<Integer, HashMap<String, String>> map = null;
        try {
            map = FactoryHandler.getAll("JobPosting", this.JobPostingTable, "");
        } catch (SQLException sqlError) {
            sqlError.printStackTrace();
        } catch (ClassNotFoundException notFoundException) {
            System.out.println("Table JobPosting not found");
        }

        for(Integer id : map.keySet()) {
            jobPostings.put( id, new JobPosting(map.get(id)) );
        }

        return jobPostings;
    }

    // Return all jobPosting via user profile
    public HashMap<Integer, JobPosting> getPostingsViaUserProfile(JobApplicant jobApplicant) {
        String categoryId = Integer.toString(jobApplicant.getJobCategory().getId());
        String filter = "WHERE JOB_CATEGORY = " + categoryId;

        HashMap<Integer, JobPosting> jobPostings = new HashMap<>();

        HashMap<Integer, HashMap<String, String>> map = null;
        try {
            map = FactoryHandler.getAll("JobPosting", this.JobPostingTable, filter);
        } catch (SQLException sqlError) {
            sqlError.printStackTrace();
        } catch (ClassNotFoundException notFoundException) {
            System.out.println("Table JobPosting not found");
        }

        for(Integer id : map.keySet()) {
            jobPostings.put( id, new JobPosting(map.get(id)) );
        }

        return jobPostings;
    }

    // Get posting by id
    public JobPosting getById(int jobPostingId) {
        try {
            return new JobPosting( FactoryHandler.getFiltering("JobPosting", this.JobPostingTable, "ID", Integer.toString(jobPostingId))
                    .get(jobPostingId) );
        } catch (SQLException | ClassNotFoundException sqlError) {
            System.out.println("Table JobPosting not found or your filter is wrong!");
        }
        return null;
    }

    //Drop table
    public void drop() {
        try {
            FactoryHandler.drop("JobPosting");
        } catch (SQLException | ClassNotFoundException sqlError) {
            System.out.println("Table JobPosting not found");
        }
    }

    //Insert mock data
    public void mockData() throws SQLException, ClassNotFoundException, IOException, ParseException {

        drop();
        create();

        CompanyController companyController = new CompanyController();
        JobCategoryController jobCategoryController = new JobCategoryController();

        //JSON parser object to parse read file
        JSONParser parser = new JSONParser();

        JSONArray jsonJobPostings = (JSONArray) parser.parse(new FileReader("public/MockData/jobPostings.json"));

        for (Object posting : jsonJobPostings) {
            //Setup Objects
            JSONObject jsonObj = (JSONObject) posting;

            Long company = (Long) jsonObj.get("COMPANY");
            Long salary = (Long) jsonObj.get("SALARY");
            Long jobCategory = (Long) jsonObj.get("JOB_CATEGORY");

            JobPosting jobPosting = new JobPosting(
                    (companyController.getById(company.intValue()) != null)
                            ? companyController.getById(company.intValue()) : new Company(),
                    (String) jsonObj.get("TITLE"),
                    (String) jsonObj.get("DESCRIPTION"),
                    (jobCategoryController.getById(jobCategory.intValue()) != null)
                            ? jobCategoryController.getById(jobCategory.intValue()) : new JobCategory(),
                    (String) jsonObj.get("SENIORITY"),
                    salary.intValue(),
                    (boolean) jsonObj.get("FULL_TIME")
            );
            // Convert jobApplicant to hash map
            HashMap<String, String> jobPostingData = jobPosting.getMap();
            // Insert data to DB
            FactoryHandler.insert("jobPosting", jobPostingData);
        }

        System.out.println("Mock data insertion.\n");
        getAll();
    }

    // Apply to job posting by user
        // Create a new row at table applied


}
