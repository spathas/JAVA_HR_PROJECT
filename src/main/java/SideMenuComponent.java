import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideMenuComponent extends JPanel implements ActionListener {

    JLabel logo = new JLabel("Human Resources System");
    JButton jobPostings_btn = new JButton("Postings");
    JButton jobApplicants_btn = new JButton("Applicants");
    JButton jobCompanies_btn = new JButton("Companies");


    SideMenuComponent() {
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
        jobPostings_btn.setFont(new Font(null,Font.BOLD,20));
        jobPostings_btn.setForeground(Color.WHITE);
        jobApplicants_btn.setFont(new Font(null,Font.BOLD,20));
        jobApplicants_btn.setForeground(Color.WHITE);
        jobCompanies_btn.setFont(new Font(null,Font.BOLD,20));
        jobCompanies_btn.setForeground(Color.WHITE);
        jobPostings_btn.setBackground(Color.BLUE);
        jobApplicants_btn.setBackground(Color.BLUE);
        jobCompanies_btn.setBackground(Color.BLUE);
//        logo.setForeground(Color.WHITE);
        // Focusable
        jobPostings_btn.setFocusable(false);
        jobApplicants_btn.setFocusable(false);
        jobCompanies_btn.setFocusable(false);
    }

    private void eventActionButtonClick() {
        jobPostings_btn.addActionListener(this);
        jobApplicants_btn.addActionListener(this);
        jobCompanies_btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jobApplicants_btn) System.out.println("applicants");
        if(e.getSource() == jobPostings_btn) System.out.println("posting");
        if(e.getSource() == jobCompanies_btn) System.out.println("companies");
    }
}
