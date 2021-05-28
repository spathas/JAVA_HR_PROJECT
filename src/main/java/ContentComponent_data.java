import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ContentComponent_data extends JPanel {

    JobPostingController jobPostingController = new JobPostingController();
    JobApplicantController jobApplicantController = new JobApplicantController();
    CompanyController companyController = new CompanyController();



    public void setupColumns(String selector) {
        HashMap<String, String> map = switch (selector) {
            case "jobApplicants_btn" -> jobApplicantController.getJobApplicantTable();
            case "jobCompanies_btn" -> companyController.getCompanyTable();
            default -> jobPostingController.getJobPostingTable();
        };

        ContentComponent_data__element element = new ContentComponent_data__element(1, map.size());
        element.setupColumns(map);
        this.add(element);
    }

    public int setupRows(String selector) {
        // Add if or switch there /////////////////////////////////////////////////////
        if(selector.equals("jobApplicants_btn")) {
            HashMap<Integer, JobApplicant> map = jobApplicantController.getAll();

            for(int key : map.keySet()) {
                HashMap<String,String> obj = map.get(key).getMap();
                ContentComponent_data__element element = new ContentComponent_data__element(1, map.size());
                element.setupRows(obj);
                this.add(element);
            }
            return map.size() + 1; // + 1 because of reference row.
        }
        if(selector.equals("jobCompanies_btn")) {
            HashMap<Integer, Company> map = companyController.getAll();

            for(int key : map.keySet()) {
                HashMap<String,String> obj = map.get(key).getMap();
                ContentComponent_data__element element = new ContentComponent_data__element(1, map.size());
                element.setupRows(obj);
                this.add(element);
            }
            return map.size() + 1; // + 1 because of reference row.
        }

        HashMap<Integer, JobPosting> map = jobPostingController.getAll();

        for(int key : map.keySet()) {
            HashMap<String,String> obj = map.get(key).getMap();
            ContentComponent_data__element element = new ContentComponent_data__element(1, map.size());
            element.setupRows(obj);
            this.add(element);
        }
        return map.size() + 1; // + 1 because of reference row.
    }
}
