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
        JobPostingController jobPostingController = new JobPostingController();
        JobCategoryController jobCategoryController = new JobCategoryController();

        MainFrameComponent frame = new MainFrameComponent();


//        try{
//            //////JOB CATEGORY///////
//            jobCategoryController.mockData();
//
//            //////COMPANY///////
//            companyController.mockData();
//
//            //////JOB POSTING///////
//            jobPostingController.mockData();
//
//            ///////JOP APPLICANT/////////
//            jobApplicantController.mockData();//
//
//        } catch (Exception se) {
//            //Handle errors for JDBC
//            se.printStackTrace();
//        }//Handle errors for Class.forName

    }
}