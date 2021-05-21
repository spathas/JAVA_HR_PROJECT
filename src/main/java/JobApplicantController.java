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
        jobApplicantTable.put("id", "INTEGER auto_increment PRIMARY KEY");
        jobApplicantTable.put("name", "VARCHAR2(50) NOT NULL");
        jobApplicantTable.put("surname", "VARCHAR2(50) NOT NULL");
        jobApplicantTable.put("age", "VARCHAR2(3) NOT NULL");
        jobApplicantTable.put("email", "VARCHAR2(50) NOT NULL UNIQUE");
        jobApplicantTable.put("phone", "VARCHAR2(20) UNIQUE");
        jobApplicantTable.put("education", "VARCHAR2(50)");
        jobApplicantTable.put("works", "BOOLEAN");
    }

    public void create() {
        try {
            FactoryHandler.create("jobApplicant", this.jobApplicantTable);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Table jobApplicant already exists");
        }
    }

    public void insert(JobApplicant jobApplicant) {
        HashMap<String, String> jobApplicantData = jobApplicant.getJobApplicantMap();

        try {
            FactoryHandler.insert("jobApplicant", jobApplicantData);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Table jobApplicant or current jobApplicant not found");
        }
    }

    public void delete(int jobApplicantId) {
        try {
            JobApplicant jobApplicant = getById(jobApplicantId);
            FactoryHandler.deleteRowById("jobApplicant", Integer.toString(jobApplicant.getId()));
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Table jobApplicant or current jobApplicant not found");
        }
    }

    public void update(JobApplicant jobApplicant) {
        int id = jobApplicant.getId();

        try {
            FactoryHandler.update("jobApplicant", id, jobApplicant.getJobApplicantMap());
        } catch (SQLException | ClassNotFoundException sqlError) {
            System.out.println("Table jobApplicant or column not found");
        }
    }

    public HashMap<Integer, JobApplicant> getAll() {
        HashMap<Integer, JobApplicant> jobApplicants = new HashMap<>();

        HashMap<Integer, HashMap<String, String>> map = null;
        try {
            map = FactoryHandler.getAll("jobApplicant", this.jobApplicantTable);
        } catch (SQLException sqlError) {
            sqlError.printStackTrace();
        } catch (ClassNotFoundException notFoundException) {
            System.out.println("Table jobApplicant not found");
        }

        for(Integer id : map.keySet()) {
            jobApplicants.put( id, new JobApplicant(map.get(id)) );
        }

        return jobApplicants;
    }

    public JobApplicant getById(int jobApplicantID) {
        try {
            return new JobApplicant( FactoryHandler.getFiltering("jobApplicant", this.jobApplicantTable, "where id = " + jobApplicantID).get(jobApplicantID) );
        } catch (SQLException | ClassNotFoundException sqlError) {
            System.out.println("Table jobApplicant not found or your filter is wrong!");
        }
        return null;
    }

    public void drop() {
        try {
            FactoryHandler.drop("jobApplicant");
        } catch (SQLException | ClassNotFoundException sqlError) {
            System.out.println("Table jobApplicant not found");
        }
    }

    public void mockData() throws SQLException, ClassNotFoundException, IOException, ParseException {

        //JSON parser object to parse read file
        JSONParser parser = new JSONParser();

        JSONArray jsonJobApplicants = (JSONArray) parser.parse(new FileReader("MockData/jobApplicants.json"));

        for (Object u : jsonJobApplicants)
        {
            //Setup Objects
            JSONObject jsonObj = (JSONObject) u;
            JobApplicant jobApplicant = new JobApplicant((String) jsonObj.get("name"), (String) jsonObj.get("surname"), Integer.parseInt((String) jsonObj.get("age")), (String) jsonObj.get("email"),
                    (String) jsonObj.get("phone"), (String) jsonObj.get("education"), (boolean) jsonObj.get("works"));
            //Convert jobApplicant to hash map
            HashMap<String, String> jobApplicantData = jobApplicant.getJobApplicantMap();
            //Insert data to DB
            FactoryHandler.insert("jobApplicant", jobApplicantData);
        }

    }

    // Return all jobApplicants who work.

}
