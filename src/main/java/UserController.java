import java.sql.SQLException;
import java.util.HashMap;

public class UserController implements FactoryHandler {
    final HashMap<String, String> userTable = new HashMap<>();

    public UserController() {
        userTable.put("id", "INTEGER auto_increment PRIMARY KEY");
        userTable.put("name", "VARCHAR2(50) NOT NULL");
        userTable.put("surname", "VARCHAR2(50) NOT NULL");
        userTable.put("age", "VARCHAR2(3) NOT NULL");
        userTable.put("email", "VARCHAR2(50) NOT NULL UNIQUE");
        userTable.put("phone", "VARCHAR2(20) UNIQUE");
        userTable.put("education", "VARCHAR2(50)");
        userTable.put("works", "BOOLEAN");
    }

    public void create() {
        try {

            FactoryHandler.create("user", this.userTable);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insert(User user) throws SQLException, ClassNotFoundException {
        HashMap<String, String> userData = user.getUserMap();

        FactoryHandler.insert("user", userData);
    }

    public HashMap<Integer, User> getAll() throws SQLException, ClassNotFoundException {
        HashMap<Integer, User> users = new HashMap<>();

        HashMap<Integer, HashMap<String, String>> map = FactoryHandler.getAll("user", this.userTable);

        for(Integer id : map.keySet()) {
            users.put( id, new User(map.get(id)) );
        }

        return users;
    }

    public void update(User user) throws SQLException, ClassNotFoundException {
        int id = user.getId();

        FactoryHandler.update("user", id, user.getUserMap());
    }

    public User getById(int userID) throws SQLException, ClassNotFoundException {
        return new User( FactoryHandler.getById("user", this.userTable, "where id = " + userID).get(userID) );
    }

    public void drop() {
        try {
            FactoryHandler.drop("user");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void mockingUserData() throws SQLException, ClassNotFoundException{
//        User nick = new User("Nick", "Smith", );
    }

}
