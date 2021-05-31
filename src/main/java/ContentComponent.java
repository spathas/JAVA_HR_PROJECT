import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ContentComponent extends JPanel {

    private String selector = "jobPostings_btn";

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

    public void refreshObjects() {
        switch (this.getSelector()) {
            case "jobApplicants_btn":
                this.jobApplicantsView = new ContentComponent_data(this, "jobApplicants_btn");
                break;
            case "companies_btn":
                this.companiesView = new ContentComponent_data(this, "companies_btn");
                break;
            case "jobPostings_btn":
                this.jobPostingsView = new ContentComponent_data(this, "jobPostings_btn");
                break;
            default:
                break;
        }
    }

    public void addItems() {
        this.removeAll();

        switch (this.selector) {
            case "jobApplicants_btn" -> this.data = jobApplicantsView;
            case "companies_btn" -> this.data = companiesView;
            default -> this.data = jobPostingsView;
        }
        this.add(data, BorderLayout.CENTER);
        this.add( new ContentComponent_menu(this), BorderLayout.NORTH);
        this.validate();
        this.repaint();
    }

    // Return for which create a new element per category.
    public void addFormNew() {
        this.removeAll();

        this.add(new FormComponent(this, this.selector), BorderLayout.CENTER);
        this.validate();
        this.repaint();
    }

    // Return form which update or delete an element
    public void addFormGet(int id) {
        this.removeAll();

        this.add(new FormComponent(this, this.selector, id), BorderLayout.CENTER);
        this.validate();
        this.repaint();
    }

    // Return all applicants per posting map
    public void findRelationships(String selectorColumns, int id) {
        this.removeAll();

        // !!! IMPORTANT !!!
        // We need to swap current selector with scope selector at this time,
        // because when we search from e.g company screen for jobPostings through this company
        // we have to swap screen view (to jobPosting view). It is not so sense but it works well.
        // Sorry :D
        String selectorRows = this.selector;
        this.selector = selectorColumns;
        this.data = new ContentComponent_data(this, selectorColumns, selectorRows, id);

        this.add(data, BorderLayout.CENTER);
        this.add( new ContentComponent_menu(this), BorderLayout.NORTH);
        this.validate();
        this.repaint();
    }


}
