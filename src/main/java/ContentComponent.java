import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ContentComponent extends JPanel {

    ContentComponent_menu menu = new ContentComponent_menu();

    private ContentComponent_data data = new ContentComponent_data("jobPostings_btn");
    public ContentComponent_data jobApplicantsView = new ContentComponent_data("jobApplicants_btn");
    public ContentComponent_data jobPostingsView = new ContentComponent_data("jobPostings_btn");
    public ContentComponent_data companiesView = new ContentComponent_data("companies_btn");


    public ContentComponent() {
        setupContent();
        addItems("jobPostings_btn");
    }

    private void setupContent() {
        this.setLayout(new BorderLayout(0,5));
        this.setBackground(Color.white);
    }

    public void addItems(String selector) {
        this.remove(jobApplicantsView);
        this.remove(companiesView);
        this.remove(jobPostingsView);

        switch (selector) {
            case "jobApplicants_btn" -> this.data = jobApplicantsView;
            case "companies_btn" -> this.data = companiesView;
            default -> this.data = jobPostingsView;
        }
        this.add(data, BorderLayout.CENTER);
        this.add(menu, BorderLayout.NORTH);
        this.validate();
        this.repaint();
    }
}
