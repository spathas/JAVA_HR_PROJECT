import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;

public class ContentComponent_data extends JPanel {

    JobPostingController jobPostingController = new JobPostingController();
    JobApplicantController jobApplicantController = new JobApplicantController();
    CompanyController companyController = new CompanyController();

    public ContentComponent_data() {
        this.setBackground(Color.lightGray);

        //Setup elements component
        int columns = 1;
        setupColumns();
        int rows = setupRows();
        setupComponent(rows, columns);
    }

    private void setupComponent(int rows, int columns) {
        this.setLayout(new GridLayout(rows,columns,0,15));
    }

    private void setupColumns() {
        HashMap<String, String> map;
        // Add if or switch there /////////////////////////////////////////////////////
//        map = companyController.getCompanyTable();
        map = jobApplicantController.getJobApplicantTable();
        ContentComponent_data__element element = new ContentComponent_data__element(1, map.size());
        element.setupColumns(map);
        this.add(element);
//        System.out.println(map.size());
    }

    private int setupRows() {
        // Add if or switch there /////////////////////////////////////////////////////
        HashMap<Integer, JobApplicant> map = jobApplicantController.getAll();

        for(int key : map.keySet()) {

            HashMap<String,String> obj = map.get(key).getMap();
            ContentComponent_data__element element = new ContentComponent_data__element(1, map.size());
            element.setupRows(obj);
            this.add(element);
        }
        return map.size() + 1; // + 1 because of reference row.
    }
}
