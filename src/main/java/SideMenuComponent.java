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
        this.add(companies_btn);

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

        companies_btn.setFont(new Font(null,Font.BOLD,18));
        companies_btn.setForeground(Color.WHITE);
        companies_btn.setBackground(Color.BLUE);
        companies_btn.setFocusable(false);


        logo.setFont(new Font(null,Font.BOLD,18));
        logo.setForeground(Color.BLUE);
    }

    private void eventActionButtonClick() {
        jobPostings_btn.addActionListener(this);
        jobApplicants_btn.addActionListener(this);
        companies_btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jobApplicants_btn) content.addItems("jobApplicants_btn");
        if(e.getSource() == jobPostings_btn) content.addItems("jobPostings_btn");
        if(e.getSource() == companies_btn) content.addItems("companies_btn");
    }

}
