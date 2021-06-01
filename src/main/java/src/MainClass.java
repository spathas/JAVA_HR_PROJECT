package src;

import Views.MainFrameComponent;

public class MainClass {
    // JDBC driver name and database URL

    Views.MainFrameComponent frame = new MainFrameComponent();
    public static final String JDBC_DRIVER = "org.h2.Driver";
    public static final String DB_URL = "jdbc:h2:./src/main/resources/HR_DATABASE";

    //  Database credentials
    public static final String USER = "spathas";
    public static final String PASS = "spathas1";

    public static void main(String[] args) {

        MainFrameComponent frame = new MainFrameComponent();
    }
}