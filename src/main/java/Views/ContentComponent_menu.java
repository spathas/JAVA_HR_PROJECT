package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContentComponent_menu extends JPanel implements ActionListener {

    ContentComponent content;

    // search bar and btn
    JLabel titleLabel = new JLabel();
    JButton newBtn = new JButton("New element");
    JButton pageBackward_btn = new JButton();
    JButton pageForward_btn = new JButton();

    public ContentComponent_menu(ContentComponent content) {
        this.content = content;
        this.setLayout(null);
        this.setBackground(Color.BLUE);
        this.setPreferredSize(new Dimension(0,100));
        this.setVisible(true);

        setupTitle();
        setupButton();
        setupPageButtons();

        this.add(titleLabel);
        this.add(newBtn);
        this.add(pageBackward_btn);
        this.add(pageForward_btn);
    }

    private void setupTitle() {
        //Label
        titleLabel.setText("Job Postings");
        if(content.getSelector().equals("jobApplicants_btn")) titleLabel.setText("Job Applicants");
        if(content.getSelector().equals("companies_btn")) titleLabel.setText("Companies");

        titleLabel.setFont(new Font(null, Font.BOLD, 32));
        titleLabel.setBackground(Color.BLUE);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBounds(50,10,300,80);
    }

    private void setupButton() {
        newBtn.setFont(new Font(null, Font.BOLD, 18));
        newBtn.setBackground(Color.GREEN.darker());
        newBtn.setForeground(Color.WHITE);
        newBtn.setFocusable(false);
        newBtn.setBounds(800,10,200,80);
        newBtn.addActionListener(this);
    }

    private void setupPageButtons() {
        Image imageBackward = new ImageIcon("public/images/backward-arrow.png").getImage();
        ImageIcon iconBackward = new ImageIcon(imageBackward.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH));
        pageBackward_btn.setIcon(iconBackward);

        pageBackward_btn.setBackground(Color.WHITE.darker());
        pageBackward_btn.setForeground(Color.BLACK);
        pageBackward_btn.setFocusable(false);
        pageBackward_btn.setBounds(1020,10,50,80);
        pageBackward_btn.addActionListener(this);

        Image imageForward = new ImageIcon("public/images/forward-arrow.png").getImage();
        ImageIcon iconForward = new ImageIcon(imageForward.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH));
        pageForward_btn.setIcon(iconForward);

        pageForward_btn.setBackground(Color.WHITE.darker());
        pageForward_btn.setForeground(Color.BLACK);
        pageForward_btn.setFocusable(false);
        pageForward_btn.setBounds(1080,10,50,80);
        pageForward_btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newBtn) {
            content.addFormNew();

        }
        if(e.getSource() == pageBackward_btn) {
            content.decrementPage();
            content.refreshObjects();
            content.addItems();
        }
        if(e.getSource() == pageForward_btn) {
            content.incrementPage();
            content.refreshObjects();
            content.addItems();
        }
    }
}
