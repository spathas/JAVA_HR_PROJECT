import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class CompanyController implements FactoryHandler {

    final HashMap<String, String> companyTable = new HashMap<>();

    public CompanyController() {
        companyTable.put("id", "INTEGER auto_increment PRIMARY KEY");
        companyTable.put("name", "varchar(50) NOT NULL UNIQUE");
        companyTable.put("email", "VARCHAR(50) NOT NULL UNIQUE");
        companyTable.put("country", "VARCHAR(20) NOT NULL");
        companyTable.put("phone", "VARCHAR(20)");
        companyTable.put("address", "VARCHAR(50)");
    }

    public void create() {
        try {
            FactoryHandler.create("company", this.companyTable);
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println("Table company already exists!");
        }
    }

    public void insert(Company company) {
        HashMap<String, String> userData = company.getCompanyMap();
        try {
            FactoryHandler.insert("company", userData);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Table company not found or your company data are wrong");
        }
    }

    public void update(Company company) {
        int id = company.getId();
        try {
            FactoryHandler.update("company", id, company.getCompanyMap());
        } catch (SQLException | ClassNotFoundException sqlError) {
            System.out.println("Table user not found or your filter is wrong!");
        }
    }

    public HashMap<Integer, Company> getAll() {
        HashMap<Integer, Company> companies = new HashMap<>();

        HashMap<Integer, HashMap<String, String>> map = null;
        try {
            map = FactoryHandler.getAll("company", this.companyTable);
        } catch (SQLException sqlError) {
            sqlError.printStackTrace();
        } catch (ClassNotFoundException notFoundException) {
            System.out.println("Table company not found");
        }

        for(Integer id : map.keySet()) {
            companies.put( id, new Company(map.get(id)) );
        }

        return companies;
    }

    public Company getById(int companyID) {
        try {
            return new Company(
                    FactoryHandler.getFiltering("company", this.companyTable, "where id = " + companyID)
                            .get(companyID)
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
        //JSON parser object to parse read file
        JSONParser parser = new JSONParser();

        JSONArray jsonCompanies = (JSONArray) parser.parse(new FileReader("MockData/companies.json"));

        for (Object u : jsonCompanies)
        {
            //Setup Objects
            JSONObject jsonObj = (JSONObject) u;
            Company company = new Company((String) jsonObj.get("name"), (String) jsonObj.get("email"), (String) jsonObj.get("country"),
                    (String) jsonObj.get("phone"), (String) jsonObj.get("address"));
            //Convert companies to hash map
            HashMap<String, String> companyData = company.getCompanyMap();
            //Insert data to DB
            FactoryHandler.insert("company", companyData);
        }
    }
}
