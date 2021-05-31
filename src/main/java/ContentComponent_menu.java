import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContentComponent_menu extends JPanel implements ActionListener {

    ContentComponent content;

    // search bar and btn
    JLabel titleLabel = new JLabel();
    JButton newBtn = new JButton("New element");

    public ContentComponent_menu(ContentComponent content) {
        this.content = content;
        setupTitle();
        setupButton();
        this.setLayout(null);
        this.setBackground(Color.BLUE);
        this.setPreferredSize(new Dimension(0,100));
        this.setVisible(true);

        this.add(titleLabel);
        this.add(newBtn);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newBtn) content.addFormNew();
    }
}
