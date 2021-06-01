package src;

import java.sql.*;

public class Server {
    Connection conn= null;
    Statement stmt=null;
    PreparedStatement pstmt=null;

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
        this.conn = DriverManager.getConnection(url,user,password);
    }

    public void executeInsertQuery(String query) throws SQLException {
        pstmt = conn.prepareStatement(query);
        pstmt.execute();
//        System.out.println("Query: [ " + query + " ] executed.");
    }

    public void executeUpdateQuery(String query) throws SQLException {
        stmt = conn.createStatement();
        stmt.executeUpdate(query);
//        System.out.println("Query: [ " + query + " ] executed.");
    }

    public ResultSet executeQuery(String query) throws SQLException {
        stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }

    public void connectionClose() throws SQLException {
        if(pstmt != null) pstmt.close();
        if(stmt != null) stmt.close();
        conn.close();

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