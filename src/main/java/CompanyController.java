import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class CompanyController {

    final HashMap<String, String> companyTable = new HashMap<>();

    public CompanyController() {
        companyTable.put("id", "INTEGER auto_increment");
        companyTable.put("name", "varchar(50)");
        companyTable.put("email", "VARCHAR(50)");
        companyTable.put("country", "VARCHAR(20)");
        companyTable.put("phone", "VARCHAR(20)");
        companyTable.put("address", "VARCHAR(50)");
    }

    public void createTableCompany(Server server) {
        try {
            FactoryHandler.createTable(server, "company", this.companyTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, Company> fetchCompanies(Server server) throws SQLException, ClassNotFoundException {
        HashMap<Integer, Company> companies = new HashMap<>();

        HashMap<Integer, HashMap<String, String>> map = FactoryHandler.fetchObjects(server, "company", this.companyTable);

        for(Integer id : map.keySet()) {
            companies.put( id, new Company(map.get(id)) );
        }

        return companies;
    }

    public Company fetchCompanyById(Server server, String companyID) throws SQLException, ClassNotFoundException {
        return new Company(
                FactoryHandler.fetchObjects(server, "user", this.companyTable, "where id = " + companyID)
                        .get(companyID)
        );
    }

    public void insertCompany(Server server, Company company) throws SQLException, ClassNotFoundException {
        HashMap<String, String> userData = company.getCompanyMap();
        FactoryHandler.insertTable(server, "company", userData);
    }

    public void dropTable(Server server) {
        try {
            FactoryHandler.dropTable(server, "company");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
