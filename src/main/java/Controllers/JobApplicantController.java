package Controllers;

import Models.JobApplicant;
import Models.JobCategory;
import Models.JobPosting;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class JobApplicantController implements FactoryHandler {
    private final HashMap<String, String> jobApplicantTable = new HashMap<>();

    public JobApplicantController() {
        jobApplicantTable.put("ID", "INTEGER auto_increment PRIMARY KEY");
        jobApplicantTable.put("NAME", "VARCHAR2(50) NOT NULL");
        jobApplicantTable.put("SURNAME", "VARCHAR2(50) NOT NULL");
        jobApplicantTable.put("AGE", "VARCHAR2(3) NOT NULL");
        jobApplicantTable.put("EMAIL", "VARCHAR2(50) NOT NULL UNIQUE");
        jobApplicantTable.put("PHONE", "VARCHAR2(10) NOT NULL UNIQUE");
        jobApplicantTable.put("EDUCATION", "VARCHAR2(50)");
        jobApplicantTable.put("JOB_CATEGORY", "VARCHAR2(50)");
        jobApplicantTable.put("WORKS", "BOOLEAN");
    }

    public void create() {
        try {
            FactoryHandler.create("JobApplicant", this.jobApplicantTable);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Table JobApplicant already exists");
        }
    }

    public void insert(JobApplicant jobApplicant) {
        HashMap<String, String> jobApplicantData = jobApplicant.getMap();

        try {
            FactoryHandler.insert("JobApplicant", jobApplicantData);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Table JobApplicant or Applicant data are wrong!");
        }

    }

    public void delete(int jobApplicantId) {
        try {
            JobApplicant jobApplicant = getById(jobApplicantId);
            FactoryHandler.deleteRowById("JobApplicant", Integer.toString(jobApplicant.getId()));
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Table JobApplicant or current jobApplicant not found");
        }
    }

    public void update(JobApplicant jobApplicant) {
        int id = jobApplicant.getId();

        try {
            FactoryHandler.update("JobApplicant", id, jobApplicant.getMap());
        } catch (SQLException | ClassNotFoundException sqlError) {
            System.out.println("Table JobApplicant or column not found");
        }

        getById(id);
    }

    public HashMap<Integer, JobApplicant> getAll() {
        HashMap<Integer, JobApplicant> jobApplicants = new HashMap<>();

        HashMap<Integer, HashMap<String, String>> map = null;
        try {
            map = FactoryHandler.getAll("JobApplicant", this.jobApplicantTable, "");
        } catch (SQLException sqlError) {
            sqlError.printStackTrace();
        } catch (ClassNotFoundException notFoundException) {
            System.out.println("Table JobApplicant not found");
        }

        for(Integer id : map.keySet()) {
            jobApplicants.put( id, new JobApplicant(map.get(id)) );
        }

        return jobApplicants;
    }

    // Return all JobApplicants via posting description
    public HashMap<Integer, JobApplicant> getApplicantsViaJobPosting(JobPosting jobPosting) {
        String categoryId = Integer.toString(jobPosting.getJobCategory().getId());
        String filter = "WHERE JOB_CATEGORY = " + categoryId;

        HashMap<Integer, JobApplicant> jobApplicants = new HashMap<>();

        HashMap<Integer, HashMap<String, String>> map = null;
        try {
            map = FactoryHandler.getAll("JobApplicant", this.jobApplicantTable, filter);
        } catch (SQLException sqlError) {
            sqlError.printStackTrace();
        } catch (ClassNotFoundException notFoundException) {
            System.out.println("Table JobApplicant not found");
        }

        for(Integer id : map.keySet()) {
            jobApplicants.put( id, new JobApplicant(map.get(id)) );
        }

        return jobApplicants;
    }

    // Return all jobApplicants who NOT work.
    public HashMap<Integer, JobApplicant> getIfWorks(boolean isWork) {
        String work = isWork ? Boolean.toString(isWork) : "false";
        String filter = " WHERE WORKS = " + work;

        HashMap<Integer, JobApplicant> jobApplicants = new HashMap<>();

        HashMap<Integer, HashMap<String, String>> map = null;
        try {
            map = FactoryHandler.getAll("JobApplicant", this.jobApplicantTable, filter);
        } catch (SQLException sqlError) {
            sqlError.printStackTrace();
        } catch (ClassNotFoundException notFoundException) {
            System.out.println("Table JobApplicant not found");
        }

        if(map == null) return null;

        for(Integer id : map.keySet()) {
            jobApplicants.put( id, new JobApplicant(map.get(id)) );
        }

        return jobApplicants;
    }

    public JobApplicant getById(int jobApplicantID) {
        try {
            return new JobApplicant( FactoryHandler.getFiltering("JobApplicant", this.jobApplicantTable, "ID",
                    Integer.toString(jobApplicantID)) );
        } catch (SQLException | ClassNotFoundException sqlError) {
            System.out.println("Table JobApplicant not found or your filter is wrong!");
        }
        return null;
    }

    public void drop() {
        try {
            FactoryHandler.drop("JobApplicant");
        } catch (SQLException | ClassNotFoundException sqlError) {
            System.out.println("Table JobApplicant not found");
        }
    }

    public void mockData() throws SQLException, ClassNotFoundException, IOException, ParseException {
        JobCategoryController jobCategoryController = new JobCategoryController();

        drop();
        create();

        //JSON parser object to parse read file
        JSONParser parser = new JSONParser();

        JSONArray jsonJobApplicants = (JSONArray) parser.parse(new FileReader("public/MockData/jobApplicants.json"));

        for (Object u : jsonJobApplicants)
        {
            //Setup Objects
            JSONObject jsonObj = (JSONObject) u;

            Long jobCategory = (Long) jsonObj.get("JOB_CATEGORY");

            JobApplicant jobApplicant = new JobApplicant(
                    (String) jsonObj.get("NAME"),
                    (String) jsonObj.get("SURNAME"),
                    Integer.parseInt((String) jsonObj.get("AGE")),
                    (String) jsonObj.get("EMAIL"),
                    (String) jsonObj.get("PHONE"),
                    (String) jsonObj.get("EDUCATION"),
                    (jobCategoryController.getById(jobCategory.intValue()) != null)
                            ? jobCategoryController.getById(jobCategory.intValue()) : new JobCategory(),
                    (boolean) jsonObj.get("WORKS")
            );
            //Convert jobApplicant to hash map
            HashMap<String, String> jobApplicantData = jobApplicant.getMap();
            //Insert data to DB
            FactoryHandler.insert("JobApplicant", jobApplicantData);
        }

        System.out.println("Mock data insertion.\n");
        getAll();

    }

    public HashMap<String, String> getJobApplicantTable() { return jobApplicantTable; }
}
