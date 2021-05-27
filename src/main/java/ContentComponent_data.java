import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ContentComponent_data extends JPanel {

    JobPostingController jobPostingController = new JobPostingController();
    JobApplicantController jobApplicantController = new JobApplicantController();
    CompanyController companyController = new CompanyController();


    public ContentComponent_data() {
        int columns = setupColumns();
        int rows = setupRows();
        setupComponent(rows, columns);
    }

    private void setupComponent(int rows, int columns) {
        this.setLayout(new GridLayout(rows,columns));
    }

    private int setupColumns() {
        HashMap<String, String> map = new HashMap<String,String>();
        // Add if or switch there /////////////////////////////////////////////////////
        map = companyController.getCompanyTable();

        for(String key : map.keySet()) {
            this.add(new JLabel(key)); //Create a component
        }

        return map.size();
    }

    private int setupRows() {
        // Add if or switch there /////////////////////////////////////////////////////
        HashMap<Integer, Company> map = companyController.getAll();

        for(int key : map.keySet()) {

            HashMap<String,String> obj = map.get(key).getMap(); //Create a component
            SortedSet<String> values = new TreeSet<>(obj.values());

            System.out.println(values);
            for (String objKey : values) {
                System.out.println(objKey);
                this.add(new JLabel(objKey));
            }
        }

        return map.size();
    }
}
