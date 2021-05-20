import java.sql.SQLException;

public class MainClass {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./src/main/resources/HR_DATABASE";

    //  Database credentials
    static final String USER = "spathas";
    static final String PASS = "spathas1";

    public static void main(String[] args) {

        UserController userController = new UserController();
        CompanyController companyController = new CompanyController();


        Company c = new Company("Test", "test@example.com", "Greece", "2105566695", "Somewhere 1");
        Company c2 = new Company("Test2", "test2@example.com", "Greece", "2125566695", "Somewhere 1");
        User nick = new User("Nick", "Smith", 50, "nick@example.com", "6985544778", "Computer Science", false);
        User john = new User("John", "Luck", 45, "john@example.com", "6985544779", "Computer Science", true);


        userController.drop();
        companyController.drop();

//        TEST FACTORY FUNC
        try{
            ///////USER/////////
            userController.create();
//            userController.insert(nick);
//            userController.insert(john);
            userController.mockData();
            userController.getAll();
            userController.getById(1);

            User nickolas = userController.getById(1);
            nickolas.setName("Nickolas");
            nickolas.setAge(20);
            userController.update(nickolas);

//            userController.drop();

            //////COMPANY///////
            companyController.create();
//            companyController.insert(c);
//            companyController.insert(c2);
            companyController.mockData();
            companyController.getAll();
            companyController.getById(1);

            Company c1 = companyController.getById(1);
            c1.setName("Name Updated");
            companyController.update(c1);

//            companyController.drop();

        } catch (Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName


//            userController.mockingUserData();
    }
}