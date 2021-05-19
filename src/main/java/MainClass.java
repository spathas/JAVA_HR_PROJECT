import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class MainClass {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:../resources/HR_DATABASE";

    //  Database credentials
    static final String USER = "spathas";
    static final String PASS = "spathas1";

    public static void main(String[] args) {

        //Server connection
        Server h2 = new Server(JDBC_DRIVER, DB_URL, USER, PASS);
        UserController userController = new UserController();


//        Company c = new Company("Test", "test@example.com", "Greece", "2105566695", "Somewhere 1");
        User nick = new User("Nick", "Smith", "50", "nick@example.com", "6985544778", "Computer Science", false);


        try {
            userController.createTableUser(h2);

            userController.insertUser(h2, nick);

            userController.fetchUsers(h2);

            userController.fetchUserById(h2, "0");

            userController.dropTableUser(h2);



        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();

        }
    }
}