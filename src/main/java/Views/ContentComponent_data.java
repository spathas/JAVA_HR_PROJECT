package Views;

import Controllers.CompanyController;
import Controllers.JobApplicantController;
import Controllers.JobPostingController;
import Models.Company;
import Models.JobApplicant;
import Models.JobPosting;

import javax.swing.*;
import java.awt.*;
import java.util.*;

// Data manipulation view
// Generate the panel of application which display data.
public class ContentComponent_data extends JPanel {

    ContentComponent content;

    JobPostingController jobPostingController = new JobPostingController();
    JobApplicantController jobApplicantController = new JobApplicantController();
    CompanyController companyController = new CompanyController();

    // Constructor to return getAll functions per controller.
    public ContentComponent_data(ContentComponent content, String selector, int page) {
        this.content = content;
        this.setupColumns(selector);
        int rows = this.setupRows(selector, page);

        this.setLayout(new GridLayout(rows,1,0,20));
        this.validate();
        this.repaint();
    }

    // Constructor to return Applicants per Posting and Postings per Applicants.
    public ContentComponent_data(ContentComponent content, String selectorColumns, String selectorRows, int page, int id) {
        this.content = content;
        this.setupColumns(selectorColumns);
        this.setupRows(selectorRows, page, id);

        this.setLayout(new GridLayout(5,1,0,20));
        this.validate();
        this.repaint();
    }

    public void setupColumns(String selector) {
        HashMap<String, String> map = switch (selector) {
            case "jobApplicants_btn" -> jobApplicantController.getJobApplicantTable();
            case "companies_btn" -> companyController.getCompanyTable();
            default -> jobPostingController.getJobPostingTable();
        };

        ContentComponent_data__element element = new ContentComponent_data__element(this.content, 0);
        element.setupColumns(map);
        this.add(element);
    }

    // Setup rows for getAll func for controller.
    public int setupRows(String selector, int page) {

        int pagination;
        int start;
        int count;

        // Add if or switch there /////////////////////////////////////////////////////
        if(selector.equals("jobApplicants_btn")) {
            HashMap<Integer, JobApplicant> map = jobApplicantController.getAll();

            pagination = page * 5;
            start = pagination - 5;
            count = 0;

            for(int key : map.keySet()) {
                if(count >= start && count < pagination) {
                    HashMap<String,String> obj = map.get(key).getMap();
                    ContentComponent_data__element element = new ContentComponent_data__element(this.content, key);
                    element.setupRows(obj);
                    this.add(element);
                }
                count++;
            }
            return 5 +1; // Rows + 1 because of reference row.
        }
        if(selector.equals("companies_btn")) {
            HashMap<Integer, Company> map = companyController.getAll();

            pagination = page * 4;
            start = pagination - 4;
            count = 0;

            for(int key : map.keySet()) {
                if(count >= start && count < pagination) {
                HashMap<String,String> obj = map.get(key).getMap();
                ContentComponent_data__element element = new ContentComponent_data__element(this.content, key);
                element.setupRows(obj);
                this.add(element);
                }
                count++;
            }
            return 5;
        }

        HashMap<Integer, JobPosting> map = jobPostingController.getAll();

        pagination = page * 2;
        start = pagination - 2;
        count = 0;

        for(int key : map.keySet()) {
            if(count >= start && count < pagination) {
            HashMap<String,String> obj = map.get(key).getMap();
            ContentComponent_data__element element = new ContentComponent_data__element(this.content, key);
            element.setupRows(obj);
            this.add(element);
            }
            count++;
        }
        return 3; // + 1 because of references row.
    }

    // Overload upper method.
    //Setup rows for Applicant -> Postings / Posting -> Applicants.
    public int setupRows(String selector,int page, int id) {

        int pagination;
        int start;
        int count;

        // Add if or switch there /////////////////////////////////////////////////////
        if (selector.equals("jobPostings_btn")) {
            JobPosting jobPosting = jobPostingController.getById(id);
            HashMap<Integer, JobApplicant> map = jobApplicantController.getApplicantsViaJobPosting(jobPosting);

            pagination = page * 2;
            start = pagination - 2;
            count = 0;

            for (int key : map.keySet()) {
                if(count >= start && count < pagination) {
                HashMap<String, String> obj = map.get(key).getMap();
                ContentComponent_data__element element = new ContentComponent_data__element(this.content, key);
                element.setupRows(obj);
                this.add(element);
                }
                count++;
            }
            return 2 + 1; // Rows + 1 because of reference row.
        }
        if (selector.equals("companies_btn")) {
            Company company = companyController.getById(id);
            HashMap<Integer, JobPosting> map = jobPostingController.getPostingsViaCompany(company);

            pagination = page * 4;
            start = pagination - 4;
            count = 0;

            for (int key : map.keySet()) {
                if(count >= start && count < pagination) {
                HashMap<String, String> obj = map.get(key).getMap();
                ContentComponent_data__element element = new ContentComponent_data__element(this.content, key);
                element.setupRows(obj);
                this.add(element);
                }
                count++;
            }
            return 5;
        }

        if (selector.equals("jobApplicants_btn")) {
            JobApplicant jobApplicant = jobApplicantController.getById(id);
            HashMap<Integer, JobPosting> map = jobPostingController.getPostingsViaApplicant(jobApplicant);

            pagination = page * 5;
            start = pagination - 5;
            count = 0;

            for (int key : map.keySet()) {
                if(count >= start && count < pagination) {
                HashMap<String, String> obj = map.get(key).getMap();
                ContentComponent_data__element element = new ContentComponent_data__element(this.content, key);
                element.setupRows(obj);
                this.add(element);
                }
                count++;
            }
        }
        return 6;
    }

}
