import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideMenuComponent extends JPanel implements ActionListener {

    ContentComponent content;
    private String selector = "jobApplicants_btn";

    JLabel logo = new JLabel("HR System");
    JButton jobPostings_btn = new JButton("Postings");
    JButton jobApplicants_btn = new JButton("Applicants");
    JButton jobCompanies_btn = new JButton("Companies");


    SideMenuComponent() {
        setupFrame();
        addMenuItems();
    }

    SideMenuComponent(ContentComponent content) {
        this.content = content;
        setupFrame();
        addMenuItems();
    }

    private void setupFrame() {
        this.setPreferredSize(new Dimension(260,0));
        this.setBackground(Color.lightGray);
        this.setLayout(new GridLayout(8,1, 1,15));
        this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    private void addMenuItems() {
        this.add(logo);
        this.add(jobPostings_btn);
        this.add(jobApplicants_btn);
        this.add(jobCompanies_btn);

        // Logo icon
        logo.setIcon(new ImageIcon(new ImageIcon("./public/images/logo.png")
                .getImage()
                .getScaledInstance(70, 70, Image.SCALE_DEFAULT)));

        // Setup items
        menuButtonsSetup();
        eventActionButtonClick();
    }

    private void menuButtonsSetup() {
        // Colors
        jobPostings_btn.setFont(new Font(null,Font.BOLD,18));
        jobPostings_btn.setForeground(Color.WHITE);
        jobPostings_btn.setBackground(Color.BLUE);
        jobPostings_btn.setFocusable(false);

        jobApplicants_btn.setFont(new Font(null,Font.BOLD,18));
        jobApplicants_btn.setForeground(Color.WHITE);
        jobApplicants_btn.setBackground(Color.BLUE);
        jobApplicants_btn.setFocusable(false);

        jobCompanies_btn.setFont(new Font(null,Font.BOLD,18));
        jobCompanies_btn.setForeground(Color.WHITE);
        jobCompanies_btn.setBackground(Color.BLUE);
        jobCompanies_btn.setFocusable(false);


        logo.setFont(new Font(null,Font.BOLD,18));
        logo.setForeground(Color.BLUE);
    }

    private void eventActionButtonClick() {
        jobPostings_btn.addActionListener(this);
        jobApplicants_btn.addActionListener(this);
        jobCompanies_btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jobApplicants_btn) ContentComponent.setupComponent("jobApplicants_btn", ContentComponent.data);
        if(e.getSource() == jobPostings_btn) ContentComponent.setupComponent("jobPostings_btn", ContentComponent.data);
        if(e.getSource() == jobCompanies_btn) ContentComponent.setupComponent("jobCompanies_btn", ContentComponent.data);
    }

    public String getSelector() {
        return selector;
    }
}
