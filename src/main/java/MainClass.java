public class MainClass {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./src/main/resources/HR_DATABASE";

    //  Database credentials
    static final String USER = "spathas";
    static final String PASS = "spathas1";

    public static void main(String[] args) {

        MainFrameComponent frame = new MainFrameComponent();

//        JobApplicantController jobApplicantController = new JobApplicantController();
//        CompanyController companyController = new CompanyController();
//        JobPostingController jobPostingController = new JobPostingController();
//        JobCategoryController jobCategoryController = new JobCategoryController();
//
//        TEST FACTORY FUNC

//        try{
//            //////JOB CATEGORY///////
//            jobCategoryController.mockData();
////            System.out.println(jobCategoryController.getById(1));
//
//            //////COMPANY///////
//            companyController.mockData();
//
////            Company c1 = companyController.getById(1);
////            c1.setName("Name Updated");
////            companyController.update(c1);
//
//            //////JOB POSTING///////
//            jobPostingController.mockData();
////            JobApplicant nickolas = jobApplicantController.getById(1);
////            jobPostingController.getPostingsViaUserProfile(nickolas);
//
////            JobPosting posting = jobPostingController.getById(1);
////            System.out.println(posting.toString());
//
//            ///////JOP APPLICANT/////////
//            jobApplicantController.mockData();
////            jobApplicantController.getIfWorks(false);
////            jobApplicantController.delete(2);
////
////            JobApplicant nickolas = jobApplicantController.getById(1);
////            nickolas.setName("Nickolas");
////            nickolas.setAge(20);
////            jobApplicantController.update(nickolas);
////            jobApplicantController.getAll();
//
//            JobApplicant nickolas = jobApplicantController.getById(1);
//            jobPostingController.getPostingsViaUserProfile(nickolas);
//
//            JobPosting posting = jobPostingController.getById(1);
//            System.out.println(posting.toString());
//
////            /////JOP APPLICANT/////////
////            jobApplicantController.mockData();
////            jobApplicantController.getIfWorks(false);
////            jobApplicantController.delete(2);
////
//////            JobApplicant nickolas = jobApplicantController.getById(1);
////            nickolas.setName("Nickolas");
////            nickolas.setAge(20);
////            jobApplicantController.update(nickolas);
////            jobApplicantController.getAll();
////
////            jobPostingController.getPostingsViaUserProfile(nickolas);
////
////            JobPosting job1 = jobPostingController.getById(1);
////            jobApplicantController.getApplicantsViaJobPosting(job1);
//
//
//        } catch (Exception se) {
//            //Handle errors for JDBC
//            se.printStackTrace();
//        }//Handle errors for Class.forName

    }
}