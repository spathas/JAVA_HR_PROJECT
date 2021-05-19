import java.sql.SQLException;
import java.util.HashMap;

public class CompanyController implements FactoryHandler {

    final HashMap<String, String> companyTable = new HashMap<>();

    public CompanyController() {
        companyTable.put("id", "INTEGER auto_increment");
        companyTable.put("name", "varchar(50) NOT NULL UNIQUE");
        companyTable.put("email", "VARCHAR(50) NOT NULL UNIQUE");
        companyTable.put("country", "VARCHAR(20) NOT NULL");
        companyTable.put("phone", "VARCHAR(20)");
        companyTable.put("address", "VARCHAR(50)");
    }

    public void create() {
        try {
            FactoryHandler.create("company", this.companyTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insert(Company company) throws SQLException, ClassNotFoundException {
        HashMap<String, String> userData = company.getCompanyMap();
        FactoryHandler.insert("company", userData);
    }

    public void update(Company company) throws SQLException, ClassNotFoundException {
        int id = company.getId();

        FactoryHandler.update("company", id, company.getCompanyMap());
    }

    public HashMap<Integer, Company> getAll() throws SQLException, ClassNotFoundException {
        HashMap<Integer, Company> companies = new HashMap<>();

        HashMap<Integer, HashMap<String, String>> map = FactoryHandler.getAll("company", this.companyTable);

        for(Integer id : map.keySet()) {
            companies.put( id, new Company(map.get(id)) );
        }

        return companies;
    }

    public Company getById(int companyID) throws SQLException, ClassNotFoundException {
        System.out.println(FactoryHandler.getById("company", this.companyTable, "where id = " + companyID).get(companyID));
        return new Company(
                FactoryHandler.getById("company", this.companyTable, "where id = " + companyID)
                        .get(companyID)
        );
    }

    public void drop() {
        try {
            FactoryHandler.drop("company");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    public void mockData() {
//        Company shell = new Company("Shell S.A.", "");
//    }
}
