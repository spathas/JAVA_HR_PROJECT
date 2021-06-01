import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ContentComponent extends JPanel {

    /////// VARIABLES - OBJECTS //////////////////
    private String selector = "jobPostings_btn";
    private int pageJobApplicants = 1;
    private int pageCompanies = 1;
    private int pageJobPostings = 1;

    public SideMenuComponent menu;

    private ContentComponent_data data = new ContentComponent_data(this,  "jobPostings_btn", this.pageJobPostings);
    public ContentComponent_data jobApplicantsView = new ContentComponent_data(this, "jobApplicants_btn", this.pageJobApplicants);
    public ContentComponent_data companiesView = new ContentComponent_data(this, "companies_btn", this.pageCompanies);
    public ContentComponent_data jobPostingsView = new ContentComponent_data(this, "jobPostings_btn", this.pageJobPostings);


    /////// CONSTRUCTORS ///////////////////
    public ContentComponent() {
        this.setLayout(new BorderLayout(0,5));
        this.setBackground(Color.white);
        addItems();
    }

    ///////// GETTERS - SETTERS //////////////////////
    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getSelector() {
        return selector;
    }

    public void setMenu(SideMenuComponent menu) {
        this.menu = menu;
    }

    public void incrementPage() {
        switch (this.getSelector()) {
            case "jobApplicants_btn" -> this.pageJobApplicants += 1;
            case "companies_btn" -> this.pageCompanies += 1;
            default -> this.pageJobPostings += 1;
        };
    }

    public void decrementPage() {
        switch (this.getSelector()) {
            case "jobApplicants_btn" -> {
                if(this.pageJobApplicants > 1) this.pageJobApplicants -= 1;
            }
            case "companies_btn" -> {
                if(this.pageCompanies > 1) this.pageCompanies -= 1;
            }
            default -> {
                if(this.pageJobPostings > 1) this.pageJobPostings -= 1;
            }
        };
        addItems();
    }

    ///////// Content Elements Handlers ////////////
    public void refreshObjects() {
        switch (this.getSelector()) {
            case "jobApplicants_btn":
                this.jobApplicantsView = new ContentComponent_data(this, "jobApplicants_btn", this.pageJobApplicants);
                break;
            case "companies_btn":
                this.companiesView = new ContentComponent_data(this, "companies_btn", this.pageCompanies);
                break;
            case "jobPostings_btn":
                this.jobPostingsView = new ContentComponent_data(this, "jobPostings_btn", this.pageJobPostings);
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
    public void findRelationships(String selectorScope, int id) {
        int page = switch (this.getSelector()) {
            case "jobApplicants_btn" -> this.pageJobApplicants;
            case "companies_btn" -> this.pageCompanies;
            default -> this.pageJobPostings;
        };

        this.removeAll();

        // !!! IMPORTANT !!!
        // We need to swap current selector with scope selector at this time,
        // because when we search from e.g company screen for jobPostings through this company
        // we have to swap screen view (to jobPosting view). It is not so sense but it works well.
        // Sorry :D
        String selectorCurrent = this.selector;
        this.selector = selectorScope;
        this.data = new ContentComponent_data(this, selectorScope, selectorCurrent, page, id);

        this.add(data, BorderLayout.CENTER);
        this.add( new ContentComponent_menu(this), BorderLayout.NORTH);
        this.validate();
        this.repaint();
    }
}
