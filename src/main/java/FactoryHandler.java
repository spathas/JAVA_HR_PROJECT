import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public interface FactoryHandler {
    // JDBC driver name and database URL
    String JDBC_DRIVER = MainClass.JDBC_DRIVER;
    String DB_URL = MainClass.DB_URL;

    //  Database credentials
    String USER = MainClass.USER;
    String PASS = MainClass.PASS;

    //Server connection
    Server server = new Server(JDBC_DRIVER, DB_URL, USER, PASS);

    //Create a new table
    static void create(String tableName, HashMap<String, String> values) throws SQLException, ClassNotFoundException {
        server.connectionOpen();

        String query = "CREATE TABLE " + tableName + " (";

        for (String value: values.keySet()) {
            query = query.concat(value + " " + values.get(value) + ",");
        }
        query = query.substring(0, query.length()-1).concat(");");

        server.executeUpdateQuery(query);

        System.out.println("Table " + tableName + " completed.");

        server.connectionClose();
        System.out.println("");
    }

    //Insert data to table
    static void insert(String tableName, HashMap<String, String> table) throws SQLException, ClassNotFoundException {
        server.connectionOpen();

        // Create query string
        String query = "INSERT INTO " + tableName + " (";

        for(String key : table.keySet()) {
            query = query.concat(key + ",");
        }

        query = query.substring(0, query.length()-1).concat(") VALUES (");

        for(String key : table.keySet()) {
            query = query.concat("'" + table.get(key) + "' ,");
        }

        query = query.substring(0, query.length()-2).concat(");");

        //Execute query to insert
        server.executeInsertQuery(query);

        server.connectionClose();
        System.out.println("Insert 1 row");
        System.out.println("");
    }

    static void deleteRowById(String tableName, String rowId) throws SQLException, ClassNotFoundException {
        server.connectionOpen();

        //Delete row from tableName
        String query = "DELETE FROM " + tableName + " WHERE ID = " + rowId;

        server.executeInsertQuery(query);

        server.connectionClose();
        System.out.println("Insert 1 row");
        System.out.println("");
    }

    //Update data table by id
    static void update(String tableName, int id, HashMap<String, String> values) throws SQLException, ClassNotFoundException {

        server.connectionOpen();

        String query = "UPDATE " + tableName + " SET ";
        for(String key : values.keySet()) {
            query = query.concat(key + " = '" + values.get(key) + "', ");
        }
        query = query.substring(0, query.length()-2).concat(" WHERE ID = " + id + ";");

//        System.out.println(query);

        server.executeUpdateQuery(query);
        System.out.println("Update completed!");

        server.connectionClose();
        System.out.println("");
    }

    // Get all data from a table
    static HashMap<Integer, HashMap<String, String>> getAll(String tableName, HashMap<String, String> table) throws SQLException, ClassNotFoundException {

        HashMap<Integer, HashMap<String, String>> objectsMap = new HashMap<>();
        server.connectionOpen();

        //Query
        String query = "SELECT ";
        for(String key : table.keySet()) {
            query = query.concat(key + ", ");
        }
        query = query.substring(0, query.length()-2).concat(" FROM " + tableName);
        ResultSet resultSet = server.executeQuery(query);

        //Fetch data and store in a map
        while (resultSet.next()) {
            HashMap<String, String> values = new HashMap<>();
            for(String key : table.keySet()) {
                values.put(key, resultSet.getString(key));
            }
            objectsMap.put(Integer.parseInt(values.get("id")), values);
        }

        //Output test
        for (Integer name: objectsMap.keySet()) {
            String key = name.toString();
            String value = objectsMap.get(name).toString();
            System.out.println(key + " " + value);
        }

        server.connectionClose();
        System.out.println("");

        return objectsMap;
    }

    // Get a single row from table by inserted filter query. eg: "where id = id"
    static HashMap<Integer, HashMap<String, String>> getFiltering(String tableName, HashMap<String, String> table, String filter) throws SQLException, ClassNotFoundException {

        HashMap<Integer, HashMap<String, String>> objectsMap = new HashMap<>();
        server.connectionOpen();

        //Query
        String query = "SELECT ";
        for(String key : table.keySet()) {
            query = query.concat(key + ", ");
        }
        query = query.substring(0, query.length()-2).concat(" FROM " + tableName);
        query = query.concat(" " + filter);
        ResultSet resultSet = server.executeQuery(query);

        //Fetch data and store in a map
        while (resultSet.next()) {
            HashMap<String, String> values = new HashMap<>();
            for(String key : table.keySet()) {
                values.put(key, resultSet.getString(key));
            }
            objectsMap.put(Integer.parseInt(values.get("id")), values);
        }

        server.connectionClose();
        System.out.println("");

        return objectsMap;
    }

    //Drop table
    static void drop(String tableName) throws SQLException, ClassNotFoundException {
        server.connectionOpen();

        server.executeUpdateQuery("DROP TABLE " + tableName + ";");

        server.connectionClose();
        System.out.println("Table " + tableName + "dropped!");
    }
}
