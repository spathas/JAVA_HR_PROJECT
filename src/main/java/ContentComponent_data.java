import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ContentComponent_data extends JPanel {

    ContentComponent content;

    JobPostingController jobPostingController = new JobPostingController();
    JobApplicantController jobApplicantController = new JobApplicantController();
    CompanyController companyController = new CompanyController();

    // Constructor to return getAll functions per controller.
    public ContentComponent_data(ContentComponent content, String selector) {
        this.content = content;
        int columns = 1;
        this.setupColumns(selector);
        int rows = this.setupRows(selector);

        this.setLayout(new GridLayout(rows,columns,0,20));
        this.validate();
        this.repaint();
    }

    // Constructor to return Applicants per Posting and Postings per Applicants.
    public ContentComponent_data(ContentComponent content, String selectorColumns, String selectorRows, int id) {
        this.content = content;
        this.setupColumns(selectorColumns);
        this.setupRows(selectorRows, id);

        this.setLayout(new GridLayout(10,1,0,20));
        this.validate();
        this.repaint();
    }

    public void setupColumns(String selector) {
        HashMap<String, String> map = switch (selector) {
            case "jobApplicants_btn" -> jobApplicantController.getJobApplicantTable();
            case "companies_btn" -> companyController.getCompanyTable();
            default -> jobPostingController.getJobPostingTable();
        };

        ContentComponent_data__element element = new ContentComponent_data__element(this.content, 1, map.size(), 0);
        element.setupColumns(map);
        this.add(element);
    }

    // Setup rows for getAll func for controller.
    public int setupRows(String selector) {
        // Add if or switch there /////////////////////////////////////////////////////
        if(selector.equals("jobApplicants_btn")) {
            HashMap<Integer, JobApplicant> map = jobApplicantController.getAll();

            for(int key : map.keySet()) {
                HashMap<String,String> obj = map.get(key).getMap();
                ContentComponent_data__element element = new ContentComponent_data__element(this.content, 1, map.size(), key);
                element.setupRows(obj);
                this.add(element);
            }
            return map.size() + 1; // + 1 because of reference row.
        }
        if(selector.equals("companies_btn")) {
            HashMap<Integer, Company> map = companyController.getAll();

            for(int key : map.keySet()) {
                HashMap<String,String> obj = map.get(key).getMap();
                ContentComponent_data__element element = new ContentComponent_data__element(this.content, 1, map.size(), key);
                element.setupRows(obj);
                this.add(element);
            }
            return map.size() + 1; // + 1 because of reference row.
        }

        HashMap<Integer, JobPosting> map = jobPostingController.getAll();

        for(int key : map.keySet()) {
            HashMap<String,String> obj = map.get(key).getMap();
            ContentComponent_data__element element = new ContentComponent_data__element(this.content, 1, map.size(), key);
            element.setupRows(obj);
            this.add(element);
        }
        return map.size() + 1; // + 1 because of reference row.
    }

    // Overload upper method.
    //Setup rows for Applicant -> Postings / Posting -> Applicants.
    public void setupRows(String selector, int id) {
        // Add if or switch there /////////////////////////////////////////////////////
        if (selector.equals("jobPostings_btn")) {
            JobPosting jobPosting = jobPostingController.getById(id);
            HashMap<Integer, JobApplicant> map = jobApplicantController.getApplicantsViaJobPosting(jobPosting);

            for (int key : map.keySet()) {
                HashMap<String, String> obj = map.get(key).getMap();
                ContentComponent_data__element element = new ContentComponent_data__element(this.content, 1, map.size(), key);
                element.setupRows(obj);
                this.add(element);
            }
            return;
        }
        if (selector.equals("companies_btn")) {
            Company company = companyController.getById(id);
            HashMap<Integer, JobPosting> map = jobPostingController.getPostingsViaCompany(company);

            for (int key : map.keySet()) {
                HashMap<String, String> obj = map.get(key).getMap();
                ContentComponent_data__element element = new ContentComponent_data__element(this.content, 1, map.size(), key);
                element.setupRows(obj);
                this.add(element);
            }
            return;
        }

        if (selector.equals("jobApplicants_btn")) {
            JobApplicant jobApplicant = jobApplicantController.getById(id);
            HashMap<Integer, JobPosting> map = jobPostingController.getPostingsViaApplicant(jobApplicant);

            for (int key : map.keySet()) {
                HashMap<String, String> obj = map.get(key).getMap();
                ContentComponent_data__element element = new ContentComponent_data__element(this.content, 1, map.size(), key);
                element.setupRows(obj);
                this.add(element);
            }
        }
    }

}
