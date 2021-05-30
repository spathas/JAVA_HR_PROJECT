import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ContentComponent extends JPanel {

    private String selector = "jobPostings_btn";

    ContentComponent_menu menu = new ContentComponent_menu(this);

    private ContentComponent_data data = new ContentComponent_data(this,  "jobPostings_btn");
    public ContentComponent_data jobApplicantsView = new ContentComponent_data(this, "jobApplicants_btn");
    public ContentComponent_data jobPostingsView = new ContentComponent_data(this, "jobPostings_btn");
    public ContentComponent_data companiesView = new ContentComponent_data(this, "companies_btn");


    public ContentComponent() {
        this.setLayout(new BorderLayout(0,5));
        this.setBackground(Color.white);
        addItems();
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getSelector() {
        return selector;
    }

    public void addItems() {
        this.removeAll();

        switch (this.selector) {
            case "jobApplicants_btn" -> this.data = jobApplicantsView;
            case "companies_btn" -> this.data = companiesView;
            default -> this.data = jobPostingsView;
        }
        this.add(data, BorderLayout.CENTER);
        this.add(menu, BorderLayout.NORTH);
        this.validate();
        this.repaint();
    }

    public void addFormNew() {
        this.removeAll();

        this.add(new FormComponent(this, this.selector), BorderLayout.CENTER);
        this.validate();
        this.repaint();
    }


    public void addFormGet(int id) {
        this.removeAll();

        this.add(new FormComponent(this, this.selector, id), BorderLayout.CENTER);
        this.validate();
        this.repaint();
    }
}
