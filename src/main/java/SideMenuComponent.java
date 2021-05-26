import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;

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
        this.setBackground(Color.lightGray);
        this.setLayout(new GridLayout(8,1, 1,15));
        this.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
        this.setPreferredSize(new Dimension(280, 100));
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
        menuItemsFocusableChanged(false);
        eventActionButtonClick();
    }

    private void menuItemsFocusableChanged(boolean focusable) {
        jobPostings_btn.setFocusable(focusable);
        jobApplicants_btn.setFocusable(focusable);
        jobCompanies_btn.setFocusable(focusable);
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
