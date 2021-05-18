import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class UserController implements FactoryHandler {
    final HashMap<String, String> userTable = new HashMap<>();

    public UserController() {
        userTable.put("id", "INTEGER auto_increment");
        userTable.put("name", "VARCHAR2(50) NOT NULL");
        userTable.put("surname", "VARCHAR2(50) NOT NULL");
        userTable.put("email", "VARCHAR2(50) NOT NULL");
        userTable.put("phone", "VARCHAR2(20)");
        userTable.put("education", "VARCHAR2(50)");
        userTable.put("works", "BOOLEAN");
    }

    public void createTableUser(Server server) {
        try {

            FactoryHandler.createTable(server, "User", userTable);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dropTableUser(Server server) throws SQLException, ClassNotFoundException {
        FactoryHandler.dropTable(server, "user");
    }

    public ArrayList<User> fetchUsers(Server server) throws SQLException, ClassNotFoundException {

        ArrayList<User> usersObj = new ArrayList<User>();

        server.connectionOpen();
        ResultSet resultSet = server.executeQuery("SELECT * FROM userTable");

        while (resultSet.next()) {
            HashMap<String, String> values = new HashMap<String, String>();
            for(String key : this.userTable.keySet()) {
                values.put(key, resultSet.getString(key));
            }
            usersObj.add(new User(values) );
        }

        server.connectionClose();
        return usersObj;
    }

    static Company fetchCompanyById(Server server,String tableName, String companyID) throws SQLException, ClassNotFoundException {
        server.connectionOpen();

        ResultSet resultSet = server.executeQuery("SELECT id, name, email, country, phone, address FROM company WHERE id = " + companyID);

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

    public void insertUser(Server server, User user) throws SQLException, ClassNotFoundException {
        server.connectionOpen();

        String query = "INSERT INTO USER (";

        for(String key : this.userTable.keySet()) {
            query = query.concat(key + ",");
        }

        query = query.substring(0, query.length()-1).concat(") VALUES (");

        for(String key : this.userTable.keySet()) {
            query = query.concat("'" + this.userTable.get(key) + "' ,");
        }

        query = query.substring(0, query.length()-3).concat(");");

//        server.executeUpdateQuery("INSERT INTO USER (name, email, country, phone, address) " +
//                "VALUES (" +
//                "'" + company.getName() + "', " +
//                "'" + company.getEmail() + "', " +
//                "'" + company.getCountry() + "', " +
//                "'" + company.getPhone() + "', " +
//                "'" + company.getAddress() + "'" +
//                "); ");

        server.connectionClose();
    }


}
