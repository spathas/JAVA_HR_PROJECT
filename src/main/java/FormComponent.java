import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class FormComponent extends JPanel {

    HashMap<String, JTextField> map = new HashMap<>();

    ContentComponent content;

    JobPostingController jobPostingController = new JobPostingController();
    JobApplicantController jobApplicantController = new JobApplicantController();
    CompanyController companyController = new CompanyController();
    JobCategoryController jobCategoryController = new JobCategoryController();

    public FormComponent(ContentComponent content, String selector) {
        this.content = content;
        this.setLayout(new GridLayout(setupNewElements(selector),1, 0, 20));
        this.setBackground(Color.lightGray);
        this.setVisible(true);
    }
    public FormComponent(ContentComponent content, String selector, int id) {
        this.content = content;
        this.setLayout(new GridLayout(setupGetElements(selector, id),1, 0, 20));
        this.setBackground(Color.lightGray);
        this.setVisible(true);
    }

    private int setupGetElements(String selector, int id) {
        if(selector.equals("jobApplicants_btn")) {
            JobApplicant obj = jobApplicantController.getById(id);
            HashMap<String, String> applicantMap = new HashMap<>(obj.getMap());

            for (String label : applicantMap.keySet())
                this.add(new FromComponent_element(label, applicantMap.get(label)));

            this.add(new FormComponent_buttons(this.content, this, "getElement"));

            return applicantMap.size() + 1;
        }

        if(selector.equals("companies_btn")) {
            Company obj = companyController.getById(id);
            HashMap<String, String> companyMap = new HashMap<>(obj.getMap());

            for (String label : companyMap.keySet())
                this.add(new FromComponent_element(label, companyMap.get(label)));

            this.add(new FormComponent_buttons(this.content, this, "getElement"));

            return companyMap.size() + 1;
        }

        JobPosting obj = jobPostingController.getById(id);
        HashMap<String, String> postingMap = new HashMap<>(obj.getMap());

        for (String label : postingMap.keySet())
            this.add(new FromComponent_element(label, postingMap.get(label)));

        this.add(new FormComponent_buttons(this.content, this, "getElement"));

        return postingMap.size() + 1;

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
                FromComponent_element el = new FromComponent_element(label, "WRITE YOUR " + label);
                this.map.put(label, el.getTextField());
                this.add(el);
            }
        }
        this.add(new FormComponent_buttons(this.content, this, "newElement"));

        return keySet.size(); // We don't use + 1 here because ID row never user so we have this result -> [size -1 +1 = size]!
    }

    public void insertNewElement() {
        HashMap<String, String> insertMap = new HashMap<>();
        for(String key : map.keySet()) {
            System.out.println(key + map.get(key).getText());
            insertMap.put(key, map.get(key).getText());
        }

        jobPostingController.insert(new JobPosting(
                companyController.getByName(map.get("COMPANY").getText()),
                map.get("TITLE").getText(),
                map.get("DESCRIPTION").getText(),
                jobCategoryController.getByRole(map.get("JOB_CATEGORY").getText()),
                Integer.parseInt(map.get("SALARY").getText()),
                Boolean.parseBoolean(map.get("FULL_TIME").getText())
        ));
    }


}
