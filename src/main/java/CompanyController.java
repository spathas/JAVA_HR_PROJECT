import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class CompanyController implements FactoryHandler {

    final HashMap<String, String> companyTable = new HashMap<>();

    public CompanyController() {
        companyTable.put("ID", "INTEGER auto_increment PRIMARY KEY");
        companyTable.put("NAME", "VARCHAR(50) NOT NULL UNIQUE");
        companyTable.put("EMAIL", "VARCHAR(50) NOT NULL UNIQUE");
        companyTable.put("COUNTRY", "VARCHAR(20) NOT NULL");
        companyTable.put("PHONE", "VARCHAR(20)");
        companyTable.put("ADDRESS", "VARCHAR(50)");
    }

    public void create() {
        try {
            FactoryHandler.create("company", this.companyTable);
        } catch (SQLException | ClassNotFoundException throwable) {
            System.out.println("Table company already exists!");
        }
    }

    public void insert(Company company) {
        HashMap<String, String> userData = company.getMap();
        try {
            FactoryHandler.insert("company", userData);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Table company not found or your company data are wrong");
        }
    }

    public void delete(int companyId) {
        try {
            Company company = getById(companyId);
            FactoryHandler.deleteRowById("company", Integer.toString(company.getId()));
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Table JobApplicant or current jobApplicant not found");
        }
    }

    public void update(Company company) {
        int id = company.getId();
        try {
            FactoryHandler.update("company", id, company.getMap());
        } catch (SQLException | ClassNotFoundException sqlError) {
            System.out.println("Table user not found or your filter is wrong!");
        }
    }

    public HashMap<Integer, Company> getAll() {
        HashMap<Integer, Company> companies = new HashMap<>();

        HashMap<Integer, HashMap<String, String>> map = null;
        try {
            map = FactoryHandler.getAll("company", this.companyTable, "");
        } catch (SQLException sqlError) {
            sqlError.printStackTrace();
        } catch (ClassNotFoundException notFoundException) {
            System.out.println("Table company not found");
        }

        if(map == null) return null;

        for(Integer id : map.keySet()) {
            companies.put( id, new Company(map.get(id)) );
        }

        return companies;
    }

    public Company getById(int companyID) {
        try {
            return new Company(
                    FactoryHandler.getFiltering("company", this.companyTable, "ID", Integer.toString(companyID))
            );
        } catch (SQLException | ClassNotFoundException sqlError) {
            System.out.println("Table company not found or your filter is wrong!");
        }
        return null;
    }

    public Company getByName(String companyName) {
        try {
            return new Company(
                    FactoryHandler.getFiltering("company", this.companyTable, "NAME", "'"+companyName+"'")
            );
        } catch (SQLException | ClassNotFoundException sqlError) {
            System.out.println("Table company not found or your filter is wrong!");
        }
        return null;
    }

    public void drop() {
        try {
            FactoryHandler.drop("company");
        } catch (SQLException | ClassNotFoundException sqlError) {
            System.out.println("Table company not found");
        }
    }

    public void mockData() throws IOException, ParseException, SQLException, ClassNotFoundException {

        drop();
        create();

        //JSON parser object to parse read file
        JSONParser parser = new JSONParser();

        JSONArray jsonCompanies = (JSONArray) parser.parse(new FileReader("public/MockData/companies.json"));

        for (Object u : jsonCompanies)
        {
            //Setup Objects
            JSONObject jsonObj = (JSONObject) u;
            Company company = new Company(
                    (String) jsonObj.get("NAME"),
                    (String) jsonObj.get("EMAIL"),
                    (String) jsonObj.get("COUNTRY"),
                    (String) jsonObj.get("PHONE"),
                    (String) jsonObj.get("ADDRESS")
            );
            //Convert companies to hash map
            HashMap<String, String> companyData = company.getMap();
            //Insert data to DB
            FactoryHandler.insert("company", companyData);
        }

        System.out.println("Mock data insertion.\n");
        getAll();
    }

    public HashMap<String, String> getCompanyTable() { return companyTable; }
}
