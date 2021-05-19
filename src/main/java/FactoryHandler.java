import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public interface FactoryHandler {

    static void createTable(Server server, String tableName, HashMap<String, String> values) throws SQLException, ClassNotFoundException {
        server.connectionOpen();

        String query = "CREATE TABLE " + tableName + " (";

        for (String value: values.keySet()) {
            query = query.concat(value + " " + values.get(value) + ",");
        }
        query = query.substring(0, query.length()-1).concat(");");

        server.executeUpdateQuery(query);

        System.out.println("Table company completed.");
        server.connectionClose();
    }

    static void insertTable(Server server, String tableName, HashMap<String, String> table) throws SQLException, ClassNotFoundException {
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

        //Output
        System.out.println("Insert 1 row");
    }

    static HashMap<Integer, HashMap<String, String>> fetchObjects(Server server, String tableName, HashMap<String, String> table) throws SQLException, ClassNotFoundException {

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

        return objectsMap;
    }

    //Overloads
    static HashMap<Integer, HashMap<String, String>> fetchObjects(Server server, String tableName, HashMap<String, String> table, String filter) throws SQLException, ClassNotFoundException {

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

        return objectsMap;
    }

    static void dropTable(Server server, String tableName) throws SQLException, ClassNotFoundException {
        server.connectionOpen();

        server.executeUpdateQuery("DROP TABLE " + tableName + ";");

        server.connectionClose();
    }

}
