import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideMenuComponent extends JPanel implements ActionListener {

    ContentComponent content;

    JLabel logo = new JLabel("HR System");
    JButton jobPostings_btn = new JButton("Postings");
    JButton jobApplicants_btn = new JButton("Applicants");
    JButton companies_btn = new JButton("Companies");
    JButton resetAll_btn = new JButton("Reset Database");


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

    private void setupLogo() {
        logo.setFont(new Font(null,Font.BOLD,18));
        logo.setForeground(Color.BLUE);
        logo.setIcon(new ImageIcon(new ImageIcon("./public/images/logo.png")
                .getImage()
                .getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
    }

    private void menuButtonsSetup(JButton button, Color bgColor, Color fontColor) {
        button.setFont(new Font(null,Font.BOLD,18));
        button.setForeground(fontColor);
        button.setBackground(bgColor);
        button.setFocusable(false);
    }

    private void addMenuItems() {
        // Setup items
        setupLogo();
        menuButtonsSetup(jobPostings_btn, Color.BLUE, Color.WHITE);
        menuButtonsSetup(jobApplicants_btn, Color.BLUE, Color.WHITE);
        menuButtonsSetup(companies_btn, Color.BLUE, Color.WHITE);
        menuButtonsSetup(resetAll_btn, Color.RED, Color.WHITE);
        eventActionButtonClick();

        // Add items
        this.add(logo);
        this.add(jobPostings_btn);
        this.add(jobApplicants_btn);
        this.add(companies_btn);
        this.add(resetAll_btn);
    }

    private void eventActionButtonClick() {
        jobPostings_btn.addActionListener(this);
        jobApplicants_btn.addActionListener(this);
        companies_btn.addActionListener(this);
        resetAll_btn.addActionListener(this);
    }

    private void resetAllData() {
        JobApplicantController jobApplicantController = new JobApplicantController();
        CompanyController companyController = new CompanyController();
        JobPostingController jobPostingController = new JobPostingController();
        JobCategoryController jobCategoryController = new JobCategoryController();

        // RESET DATABASE DATA WITH MOCK DATA
        try{
            jobCategoryController.mockData();

            companyController.mockData();

            jobPostingController.mockData();

            jobApplicantController.mockData();

        } catch (Exception se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jobApplicants_btn) {
            content.setSelector("jobApplicants_btn");
            content.addItems();
        }
        if(e.getSource() == jobPostings_btn) {
            content.setSelector("jobPostings_btn");
            content.addItems();
        }
        if(e.getSource() == companies_btn) {
            content.setSelector("companies_btn");
            content.addItems();
        }
        if(e.getSource() == resetAll_btn) {
            int answer = JOptionPane.showConfirmDialog(
                    content,
                    "Are you sure you want to reset all data in application?",
                    "ATTENTION!",
                    JOptionPane.YES_NO_OPTION);

            // ANSWER IS YES
            if(answer == 0) {
                resetAllData();
                resetAll_btn.setBackground(Color.GREEN.darker());
            }

        }
    }

}
