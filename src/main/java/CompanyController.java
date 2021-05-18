import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public interface CompanyController {

    static void createTableCompany(Server server) throws SQLException, ClassNotFoundException {
        server.connectionOpen();

        server.executeUpdateQuery("CREATE TABLE COMPANY" +
                "(id INTEGER auto_increment, " +
                " name VARCHAR(50) UNIQUE, " +
                " email VARCHAR(50) NOT NULL, " +
                " country VARCHAR(50) NOT NULL, " +
                " phone VARCHAR(50) UNIQUE, " +
                " address VARCHAR(50) NOT NULL, " +
                " PRIMARY KEY ( id ));");

        System.out.println("Table company completed.");
        server.connectionClose();
    }

    static HashMap<Integer, Company> fetchCompanies(Server server) throws SQLException, ClassNotFoundException {

        HashMap<Integer, Company> companies = new HashMap<Integer, Company>();

        server.connectionOpen();
        ResultSet resultSet = server.executeQuery("SELECT id, name, email, country, phone, address FROM company");

        int id;
        String name;
        String email;
        String country;
        String phone;
        String address;

        while (resultSet.next()) {
            id = resultSet.getInt("ID");
            name = resultSet.getString("name");
            email = resultSet.getString("email");
            country = resultSet.getString("country");
            phone = resultSet.getString("phone");
            address = resultSet.getString("address");
            System.out.println(id + " | " + name + " | " + email + " | " + country + " | " + phone + " | " + address + "\n");
            companies.put(id, new Company(name, email, country, phone, address));
        }

        server.connectionClose();
        return companies;
    }

    static Company fetchCompanyById(Server server, String companyID) throws SQLException, ClassNotFoundException {
        server.connectionOpen();

        ResultSet resultSet = server.executeQuery("SELECT id, name, email, country, phone, address FROM company WHERE id = " + companyID);
        System.out.println("SELECT id, name, email, country, phone, address FROM company WHERE id = " + companyID);

        String ID;
        String name = null;
        String email = null;
        String country = null;
        String phone = null;
        String address = null;

        while (resultSet.next()) {
            ID = resultSet.getString("ID");
            name = resultSet.getString("name");
            email = resultSet.getString("email");
            country = resultSet.getString("country");
            phone = resultSet.getString("phone");
            address = resultSet.getString("address");
            System.out.println(ID + " | " + name + " | " + email + " | " + country + " | " + phone + " | " + address);
        }

        server.connectionClose();

        return new Company(name, email, country, phone, address);
    }

    static void insertToCompany(Server server, Company company) throws SQLException, ClassNotFoundException {
        server.connectionOpen();

        server.executeUpdateQuery("INSERT INTO COMPANY (name, email, country, phone, address) " +
                "VALUES (" +
                "\'" + company.getName() + "\', " +
                "\'" + company.getEmail() + "\', " +
                "\'" + company.getCountry() + "\', " +
                "\'" + company.getPhone() + "\', " +
                "\'" + company.getAddress() + "\'" +
                "); ");

        server.connectionClose();
    }

    static void dropCompany(Server server) throws SQLException, ClassNotFoundException {
        server.connectionOpen();

        server.executeUpdateQuery("DROP TABLE company;");

        server.connectionClose();
    }

    static void mockingCompanyData(Server server) throws SQLException, ClassNotFoundException {
        Company c1 = new Company("Shell", "CSC-Hellas@ceg.gr", "Greece", "2109476555", "Ηροδότου 3, Αθήνα 10674");
        Company c2 = new Company("EKO", "slekka@helpe.gr", "Greece", "+302111818050", "Χειμάρας 8α, Μαρούσι 15125");
        Company c3 = new Company("Kotsovolos", "info@kotsovolos.gr", "Greece", "2102899999", "Μαρίνου Αντύπα 90, Ηράκλειο 14121");
        Company c4 = new Company("Public", "info@public.gr", "Greece", "210818133", "Καραγιώργη Σερβίας 1, Αθήνα 10563");

        insertToCompany(server, c1);
        insertToCompany(server, c2);
        insertToCompany(server, c3);
        insertToCompany(server, c4);
    }
}
