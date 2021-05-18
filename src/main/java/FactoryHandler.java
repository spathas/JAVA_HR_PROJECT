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

    static void dropTable(Server server, String tableName) throws SQLException, ClassNotFoundException {
        server.connectionOpen();

        server.executeUpdateQuery("DROP TABLE " + tableName + ";");

        server.connectionClose();
    }

}
