public class MainClass {
    // JDBC driver name and database URL

    MainFrameComponent frame = new MainFrameComponent();
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./src/main/resources/HR_DATABASE";

    //  Database credentials
    static final String USER = "spathas";
    static final String PASS = "spathas1";

    public static void main(String[] args) {
        JobApplicantController jobApplicantController = new JobApplicantController();
        CompanyController companyController = new CompanyController();
        JobPostingController jobPostingController = new JobPostingController();
        JobCategoryController jobCategoryController = new JobCategoryController();
//
//        try{
//            jobCategoryController.mockData();
//
//            companyController.mockData();
//
//            jobPostingController.mockData();
//
//            jobApplicantController.mockData();
//
//        } catch (Exception se) {
//            //Handle errors for JDBC
//            se.printStackTrace();
//        }
//        System.out.println(jobPostingController.getAll());

        MainFrameComponent frame = new MainFrameComponent();
    }
}