import java.sql.SQLException;
import java.util.HashMap;

public class UserController implements FactoryHandler {
    final HashMap<String, String> userTable = new HashMap<>();

    public UserController() {
        userTable.put("id", "INTEGER auto_increment");
        userTable.put("name", "VARCHAR2(50) NOT NULL");
        userTable.put("surname", "VARCHAR2(50) NOT NULL");
        userTable.put("age", "VARCHAR2(3) NOT NULL");
        userTable.put("email", "VARCHAR2(50) NOT NULL");
        userTable.put("phone", "VARCHAR2(20)");
        userTable.put("education", "VARCHAR2(50)");
        userTable.put("works", "BOOLEAN");
    }

    public void createTableUser(Server server) {
        try {

            FactoryHandler.createTable(server, "user", this.userTable);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dropTableUser(Server server) {
        try {
            FactoryHandler.dropTable(server, "user");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, User> fetchUsers(Server server) throws SQLException, ClassNotFoundException {
        HashMap<Integer, User> users = new HashMap<>();

        HashMap<Integer, HashMap<String, String>> map = FactoryHandler.fetchObjects(server, "user", this.userTable);

        for(Integer id : map.keySet()) {
            users.put( id, new User(map.get(id)) );
        }

        return users;
    }

    public User fetchUserById(Server server, String userID) throws SQLException, ClassNotFoundException {
           return new User(
                   FactoryHandler.fetchObjects(server, "user", this.userTable, "where id = " + userID)
                   .get(userID)
           );
    }

    public void insertUser(Server server, User user) throws SQLException, ClassNotFoundException {
        HashMap<String, String> userData = user.getUserMap();

        FactoryHandler.insertTable(server, "user", userData);
    }

    static void mockingUserData(Server server) throws SQLException, ClassNotFoundException{
//        User nick = new User("Nick", "Smith", );
    }

}
