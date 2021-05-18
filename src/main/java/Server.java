import java.sql.*;

public class Server {
    Connection conn= null;
    Statement stmt=null;
    PreparedStatement pstmp=null;

    String driver;
    String url;
    String user;
    String password;

    public Server(String driver, String url, String user, String password) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void connectionOpen()
            throws SQLException, ClassNotFoundException {
        // Register H2 driver
        Class.forName(driver);

        //Open a connection

        System.out.println("Connecting to database...");
        System.out.println("");
        this.conn = DriverManager.getConnection(url,user,password);
    }

    public void executeInsertQuery(String query) throws SQLException {
        pstmp = conn.prepareStatement(query);
        pstmp.execute();
        System.out.println("Query: [ " + query + " ] executed.");
    }

    public void executeUpdateQuery(String query) throws SQLException {
        stmt = conn.createStatement();
        stmt.executeUpdate(query);
        System.out.println("Query: [ " + query + " ] executed.");
    }

    public ResultSet executeQuery(String query) throws SQLException {
        stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }

    public void connectionClose() throws SQLException {
        stmt.close();
        conn.close();

        System.out.println("");
        System.out.println("Goodbye!");
        try{
            if(stmt!=null) stmt.close();
        } catch(SQLException se2) {
        } // nothing we can do
        try {
            if(conn!=null) conn.close();
        } catch(SQLException se){
            se.printStackTrace();
        }
    }

}