package Views;

import Controllers.CompanyController;
import Controllers.JobApplicantController;
import Controllers.JobCategoryController;
import Controllers.JobPostingController;
import Models.Company;
import Models.JobApplicant;
import Models.JobPosting;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

// Generate the form which we can insert a new entity in application
public class FormComponent extends JPanel {

    HashMap<String, JTextField> map = new HashMap<>();
    Views.ContentComponent content;

    JobPostingController jobPostingController = new JobPostingController();
    JobApplicantController jobApplicantController = new JobApplicantController();
    CompanyController companyController = new CompanyController();
    JobCategoryController jobCategoryController = new JobCategoryController();

    public FormComponent(Views.ContentComponent content, String selector) {
        this.content = content;
        this.setLayout(new GridLayout(setupNewElements(selector),1, 0, 20));
        this.setBackground(Color.lightGray);
        this.setVisible(true);
    }
    public FormComponent(Views.ContentComponent content, String selector, int id) {
        this.content = content;
        this.setLayout(new GridLayout(setupGetElements(selector, id),1, 0, 20));
        this.setBackground(Color.lightGray);
        this.setVisible(true);
    }

        private int setupGetElements(String selector, int id) {
            HashMap<String, String> getterMap;

            if(selector.equals("jobApplicants_btn")) {
                JobApplicant obj = jobApplicantController.getById(id);
                getterMap = new HashMap<>(obj.getMap());
            }else if(selector.equals("companies_btn")) {
                Company obj = companyController.getById(id);
                getterMap = new HashMap<>(obj.getMap());
            } else {
                JobPosting obj = jobPostingController.getById(id);
                getterMap = new HashMap<>(obj.getMap());
            }

        for (String label : getterMap.keySet()) {

            // Get the name of company or the role of categories to display a readable content.
            String value = getterMap.get(label);
            if(label.equals("JOB_CATEGORY")) value = jobCategoryController.getById(Integer.parseInt(value)).getRole();
            if(label.equals("COMPANY")) value = companyController.getById(Integer.parseInt(value)).getName();

            Views.FromComponent_element el = new Views.FromComponent_element(label, value);
            this.map.put(label, el.getTextField());
            this.add(el);
        }

        this.add(new Views.FormComponent_buttons(this.content, this, "getElement"));

        return getterMap.size() + 1;

    }

    private int setupNewElements(String selector) {

        HashMap<String, String> map = switch (selector) {
            case "jobApplicants_btn" -> jobApplicantController.getJobApplicantTable();
            case "companies_btn" -> companyController.getCompanyTable();
            default -> jobPostingController.getJobPostingTable();
        };

        SortedSet<String> keySet = new TreeSet<>(map.keySet());

        for (String label : keySet) {
            if(!label.equals("ID")) {

                // Init placeholders
                String value = map.get(label);
                if(label.equals("COMPANY")) value = "Insert the name of company (e.g Software House Company)";
                else if(label.equals("JOB_CATEGORY")) value = "Insert the role of category (e.g Developer)";
                else if(value.contains("NOT NULL")) value = "Required";
                else if(value.contains("BOOLEAN")) value = "Accepted values only TRUE of FALSE";
                else value = "";

                Views.FromComponent_element el = new Views.FromComponent_element(label, value);
                this.map.put(label, el.getTextField());
                this.add(el);
            }
        }
        this.add(new Views.FormComponent_buttons(this.content, this, "newElement"));

        return keySet.size(); // We don't use + 1 here because ID row never user so we have this result -> [size -1 +1 = size]!
    }

    public void insertNewElement(String selector)  {
        if(selector.equals("jobApplicants_btn")) {

            ////////// VALIDATION CONSTRAINTS ///////////
            String validationErrorMessage = "";

            try {
                jobCategoryController.getByRole(map.get("JOB_CATEGORY").getText());
            } catch (NullPointerException | NumberFormatException ex) {
                validationErrorMessage += "Validation Error: Job category role is wrong or unsetted.\n";
            }

            try {
                Integer.parseInt(map.get("AGE").getText());
            } catch (NullPointerException | NumberFormatException ex) {
                validationErrorMessage += "Validation Error: Age must have 3 numeric character.\n";
            }

            try {
                Integer.parseInt(map.get("PHONE").getText());
            } catch (NullPointerException | NumberFormatException ex) {
                validationErrorMessage += "Validation Error: Phone must have numeric characters.\n";
            }

            if(map.get("PHONE").getText().length() != 10) validationErrorMessage += "Validation Error: Phone must have 10 characters.\n";

            if (!map.get("EMAIL").getText().contains("@") || !map.get("EMAIL").getText().contains("."))
                validationErrorMessage += "Validation Error: Email is wrong.\n";

            if (!map.get("WORKS").getText().toLowerCase().contains("true"))
                if (!map.get("WORKS").getText().toLowerCase().contains("false"))
                    if (map.get("WORKS").getText().toLowerCase().length() == 5)
                        validationErrorMessage += "Validation Error: Works can get values [true / false] only.\n";

            if (validationErrorMessage != "") {
                JOptionPane.showMessageDialog(this, validationErrorMessage, "Validation Error", JOptionPane.ERROR_MESSAGE);
                new Exception(validationErrorMessage);
                content.addFormNew();
            }

            ///////// INSERT OBJECT ////////////////////
            jobApplicantController.insert(new JobApplicant(
                    map.get("NAME").getText(),
                    map.get("SURNAME").getText(),
                    Integer.parseInt(map.get("AGE").getText()),
                    map.get("EMAIL").getText(),
                    map.get("PHONE").getText(),
                    map.get("EDUCATION").getText(),
                    jobCategoryController.getByRole(map.get("JOB_CATEGORY").getText()),
                    Boolean.parseBoolean(map.get("WORKS").getText())
                ));

            return;
        }
        if(selector.equals("companies_btn")) {

            ////////// VALIDATION CONSTRAINTS ///////////
            String validationErrorMessage = "";

            try {
                Integer.parseInt(map.get("PHONE").getText());
            } catch (NullPointerException | NumberFormatException ex) {
                validationErrorMessage += "Validation Error: Phone must have numeric characters.\n";
            }

            if(map.get("PHONE").getText().length() != 10) validationErrorMessage += "Validation Error: Phone must have 10 characters.\n";

            if (!map.get("EMAIL").getText().contains("@") || !map.get("EMAIL").getText().contains("."))

                if (validationErrorMessage != "") {
                    JOptionPane.showMessageDialog(this, validationErrorMessage, "Validation Error", JOptionPane.ERROR_MESSAGE);
                    new Exception(validationErrorMessage);
                    content.addFormNew();
                }

            ///////// INSERT OBJECT ////////////////////
            companyController.insert(new Company(
                    map.get("NAME").getText(),
                    map.get("EMAIL").getText(),
                    map.get("COUNTRY").getText(),
                    map.get("PHONE").getText(),
                    map.get("ADDRESS").getText()
            ));
            return;
        }

        ////////// VALIDATION CONSTRAINTS ///////////
        String validationErrorMessage = "";

        try {
            companyController.getByName(map.get("COMPANY").getText());
        } catch (NullPointerException | NumberFormatException ex) {
            validationErrorMessage += "Validation Error: Company name is wrong or unsetted.\n";
        }

        try {
            jobCategoryController.getByRole(map.get("JOB_CATEGORY").getText());
        } catch (NullPointerException | NumberFormatException ex) {
            validationErrorMessage += "Validation Error: Job category role is wrong or unsetted.\n";
        }

        if (!map.get("FULL_TIME").getText().toLowerCase().contains("true"))
            if (!map.get("FULL_TIME").getText().toLowerCase().contains("false"))
                if (map.get("FULL_TIME").getText().toLowerCase().length() == 5)
                    validationErrorMessage += "Validation Error: Full_Time can get values [true / false] only.\n";

        if (validationErrorMessage != "") {
            JOptionPane.showMessageDialog(this, validationErrorMessage, "Validation Error", JOptionPane.ERROR_MESSAGE);
            new Exception(validationErrorMessage);
            content.addFormNew();
        }

        ///////// INSERT OBJECT ////////////////////
        jobPostingController.insert(new JobPosting(
                companyController.getByName(map.get("COMPANY").getText()),
                map.get("TITLE").getText(),
                map.get("DESCRIPTION").getText(),
                jobCategoryController.getByRole(map.get("JOB_CATEGORY").getText()),
                map.get("SALARY").getText().equals("") ? 0 : Integer.parseInt(map.get("SALARY").getText()),
                Boolean.parseBoolean(map.get("FULL_TIME").getText())
        ));

    }

    public void updateElement(String selector) {
        if(selector.equals("jobApplicants_btn")) {

            ////////// VALIDATION CONSTRAINTS ///////////
            String validationErrorMessage = "";

            try {
                jobCategoryController.getByRole(map.get("JOB_CATEGORY").getText());
            } catch (NullPointerException | NumberFormatException ex) {
                validationErrorMessage += "Validation Error: Job category role is wrong or unsetted.\n";
            }

            try {
                Integer.parseInt(map.get("AGE").getText());
            } catch (NullPointerException | NumberFormatException ex) {
                validationErrorMessage += "Validation Error: Age must have 3 numeric character.\n";
            }

            try {
                Integer.parseInt(map.get("PHONE").getText());
            } catch (NullPointerException | NumberFormatException ex) {
                validationErrorMessage += "Validation Error: Phone must have numeric characters.\n";
            }

            if(map.get("PHONE").getText().length() != 10) validationErrorMessage += "Validation Error: Phone must have 10 characters.\n";

            if (!map.get("EMAIL").getText().contains("@") || !map.get("EMAIL").getText().contains("."))
                validationErrorMessage += "Validation Error: Email is wrong.\n";

            if (!map.get("WORKS").getText().toLowerCase().contains("true"))
                if (!map.get("WORKS").getText().toLowerCase().contains("false"))
                    if (map.get("WORKS").getText().toLowerCase().length() == 5)
                        validationErrorMessage += "Validation Error: Works can get values [true / false] only.\n";

            if (validationErrorMessage != "") {
                JOptionPane.showMessageDialog(this, validationErrorMessage, "Validation Error", JOptionPane.ERROR_MESSAGE);
                new Exception(validationErrorMessage);
                content.addFormNew();
            }

            ///////// INSERT OBJECT ////////////////////
            jobApplicantController.update(new JobApplicant(
                    Integer.parseInt(map.get("ID").getText()),
                    map.get("NAME").getText(),
                    map.get("SURNAME").getText(),
                    Integer.parseInt(map.get("AGE").getText()),
                    map.get("EMAIL").getText(),
                    map.get("PHONE").getText(),
                    map.get("EDUCATION").getText(),
                    jobCategoryController.getByRole(map.get("JOB_CATEGORY").getText()),
                    Boolean.parseBoolean(map.get("WORKS").getText())
            ));
            return;
        }
        if(selector.equals("companies_btn")) {
            ////////// VALIDATION CONSTRAINTS ///////////
            String validationErrorMessage = "";

            try {
                Integer.parseInt(map.get("PHONE").getText());
            } catch (NullPointerException | NumberFormatException ex) {
                validationErrorMessage += "Validation Error: Phone must have numeric characters.\n";
            }

            if(map.get("PHONE").getText().length() != 10) validationErrorMessage += "Validation Error: Phone must have 10 characters.\n";

            if (!map.get("EMAIL").getText().contains("@") || !map.get("EMAIL").getText().contains("."))

                if (validationErrorMessage != "") {
                    JOptionPane.showMessageDialog(this, validationErrorMessage, "Validation Error", JOptionPane.ERROR_MESSAGE);
                    new Exception(validationErrorMessage);
                    content.addFormNew();
                }

            ///////// INSERT OBJECT ////////////////////
            companyController.update(new Company(
                    Integer.parseInt(map.get("ID").getText()),
                    map.get("NAME").getText(),
                    map.get("EMAIL").getText(),
                    map.get("COUNTRY").getText(),
                    map.get("PHONE").getText(),
                    map.get("ADDRESS").getText()
            ));
            return;
        }

        ////////// VALIDATION CONSTRAINTS ///////////
        String validationErrorMessage = "";

        try {
            companyController.getByName(map.get("COMPANY").getText());
        } catch (NullPointerException | NumberFormatException ex) {
            validationErrorMessage += "Validation Error: Company name is wrong or unsetted.\n";
        }

        try {
            jobCategoryController.getByRole(map.get("JOB_CATEGORY").getText());
        } catch (NullPointerException | NumberFormatException ex) {
            validationErrorMessage += "Validation Error: Job category role is wrong or unsetted.\n";
        }

        if (!map.get("FULL_TIME").getText().toLowerCase().contains("true"))
            if (!map.get("FULL_TIME").getText().toLowerCase().contains("false"))
                if (map.get("FULL_TIME").getText().toLowerCase().length() == 5)
                    validationErrorMessage += "Validation Error: Full_Time can get values [true / false] only.\n";

        if (validationErrorMessage != "") {
            JOptionPane.showMessageDialog(this, validationErrorMessage, "Validation Error", JOptionPane.ERROR_MESSAGE);
            new Exception(validationErrorMessage);
            content.addFormNew();
        }

        ///////// INSERT OBJECT ////////////////////
        jobPostingController.update(new JobPosting(
                Integer.parseInt(map.get("ID").getText()),
                companyController.getByName(map.get("COMPANY").getText()),
                map.get("TITLE").getText(),
                map.get("DESCRIPTION").getText(),
                jobCategoryController.getByRole(map.get("JOB_CATEGORY").getText()),
                Integer.parseInt(map.get("SALARY").getText()),
                Boolean.parseBoolean(map.get("FULL_TIME").getText())
        ));
    }

    public void deleteElement(String selector) {
        if(selector.equals("jobApplicants_btn")) {
            jobApplicantController.delete(Integer.parseInt(map.get("ID").getText()));
        }
        if(selector.equals("companies_btn")) {
            companyController.delete(Integer.parseInt(map.get("ID").getText()));
        }
        if(selector.equals("jobPostings_btn")) {
            jobPostingController.delete(Integer.parseInt(map.get("ID").getText()));
        }
    }

    public void findElement(String selector) {
        int id = Integer.parseInt(map.get("ID").getText());

        if(selector.equals("jobPostings_btn")) {
            content.findRelationships("jobApplicants_btn", id);
        }
        if(selector.equals("companies_btn")) {
            //We need different selector there to setup column for jobPostings
            content.findRelationships("jobPostings_btn", id);
        }
        if(selector.equals("jobApplicants_btn")) {
            content.findRelationships("jobPostings_btn", id);
        }
    }
}
