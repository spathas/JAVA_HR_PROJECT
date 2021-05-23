public class MainClass {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./src/main/resources/HR_DATABASE";

    //  Database credentials
    static final String USER = "spathas";
    static final String PASS = "spathas1";

    public static void main(String[] args) {

        JobApplicantController jobApplicantController = new JobApplicantController();
        CompanyController companyController = new CompanyController();

 //        TEST FACTORY FUNC
        jobApplicantController.drop();
        companyController.drop();
        try{
            ///////USER/////////
            jobApplicantController.create();
            jobApplicantController.mockData();
            jobApplicantController.getAll();
            jobApplicantController.getById(1);
            jobApplicantController.delete(2);

            JobApplicant nickolas = jobApplicantController.getById(1);
            nickolas.setName("Nickolas");
            nickolas.setAge(20);
            jobApplicantController.update(nickolas);
            jobApplicantController.getAll();

            //////COMPANY///////
            companyController.create();
            companyController.mockData();
            companyController.getAll();
            companyController.getById(1);

            Company c1 = companyController.getById(1);
            c1.setName("Name Updated");
            companyController.update(c1);

        } catch (Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }//Handle errors for Class.forName

    }
}