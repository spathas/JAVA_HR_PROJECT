import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class UserController implements FactoryHandler {
    private final HashMap<String, String> userTable = new HashMap<>();

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
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Table user already exists");
        }
    }

    public void insert(User user) {
        HashMap<String, String> userData = user.getUserMap();

        try {
            FactoryHandler.insert("user", userData);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Table user not found or your user data are wrong");
        }
    }

    public void update(User user) {
        int id = user.getId();

        try {
            FactoryHandler.update("user", id, user.getUserMap());
        } catch (SQLException | ClassNotFoundException sqlError) {
            System.out.println("Table user or column not found");
        }
    }

    public HashMap<Integer, User> getAll() {
        HashMap<Integer, User> users = new HashMap<>();

        HashMap<Integer, HashMap<String, String>> map = null;
        try {
            map = FactoryHandler.getAll("user", this.userTable);
        } catch (SQLException sqlError) {
            sqlError.printStackTrace();
        } catch (ClassNotFoundException notFoundException) {
            System.out.println("Table user not found");
        }

        for(Integer id : map.keySet()) {
            users.put( id, new User(map.get(id)) );
        }

        return users;
    }

    public User getById(int userID) {
        try {
            return new User( FactoryHandler.getFiltering("user", this.userTable, "where id = " + userID).get(userID) );
        } catch (SQLException | ClassNotFoundException sqlError) {
            System.out.println("Table user not found or your filter is wrong!");
        }
        return null;
    }

    public void drop() {
        try {
            FactoryHandler.drop("user");
        } catch (SQLException | ClassNotFoundException sqlError) {
            System.out.println("Table user not found");
        }
    }

    public void mockData() throws SQLException, ClassNotFoundException, IOException, ParseException {

        //JSON parser object to parse read file
        JSONParser parser = new JSONParser();

        JSONArray jsonUsers = (JSONArray) parser.parse(new FileReader("MockData/users.json"));

        for (Object u : jsonUsers)
        {
            //Setup Objects
            JSONObject jsonObj = (JSONObject) u;
            User user = new User((String) jsonObj.get("name"), (String) jsonObj.get("surname"), Integer.parseInt((String) jsonObj.get("age")), (String) jsonObj.get("email"),
                    (String) jsonObj.get("phone"), (String) jsonObj.get("education"), (boolean) jsonObj.get("works"));
            //Convert user to hash map
            HashMap<String, String> userData = user.getUserMap();
            //Insert data to DB
            FactoryHandler.insert("user", userData);
        }

    }

}
