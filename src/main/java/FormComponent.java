import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class FormComponent extends JPanel {

    ContentComponent content;

    JobPostingController jobPostingController = new JobPostingController();
    JobApplicantController jobApplicantController = new JobApplicantController();
    CompanyController companyController = new CompanyController();

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

            this.add(new FormComponent_buttons(this.content, "getElement"));

            return applicantMap.size() + 1;
        }

        if(selector.equals("companies_btn")) {
            Company obj = companyController.getById(id);
            HashMap<String, String> companyMap = new HashMap<>(obj.getMap());

            for (String label : companyMap.keySet())
                this.add(new FromComponent_element(label, companyMap.get(label)));

            this.add(new FormComponent_buttons(this.content, "getElement"));

            return companyMap.size() + 1;
        }

        JobPosting obj = jobPostingController.getById(id);
        HashMap<String, String> postingMap = new HashMap<>(obj.getMap());

        for (String label : postingMap.keySet())
            this.add(new FromComponent_element(label, postingMap.get(label)));

        this.add(new FormComponent_buttons(this.content, "getElement"));

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
                this.add(new FromComponent_element(label, "WRITE YOUR " + label));
            }
        }
        this.add(new FormComponent_buttons(this.content, "newElement"));

        return keySet.size(); // We don't use + 1 here because ID row never user so we have this result -> [size -1 +1 = size]!
    }


}
