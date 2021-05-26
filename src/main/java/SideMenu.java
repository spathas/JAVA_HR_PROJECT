import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;

public class SideMenu extends JPanel implements ActionListener {

    JLabel logo = new JLabel("LOGO");
    JButton jobPostings_btn = new JButton("Postings");
    JButton jobApplicants_btn = new JButton("Applicants");
    JButton jobCompanies_btn = new JButton("Companies");


    SideMenu() {
//        Setup
        super();
        this.setBackground(Color.lightGray);
        this.setLayout(new GridLayout(8,1, 1,15));
        this.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
        this.setPreferredSize(new Dimension(280, 100));

//        Add menu buttons
        this.add(logo);
        this.add(jobPostings_btn);
        this.add(jobApplicants_btn);
        this.add(jobCompanies_btn);
        // Focusable
        jobPostings_btn.setFocusable(false);
        jobApplicants_btn.setFocusable(false);
        jobCompanies_btn.setFocusable(false);
//        jobPostings_btn.setBorder(new RoundedBorder(10));
//        Events
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
