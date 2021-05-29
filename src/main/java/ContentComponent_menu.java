import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContentComponent_menu extends JPanel implements ActionListener {

    // search bar and btns
    JLabel searchBarLabel = new JLabel("  Search: ");
    JTextField searchBarTextField = new JTextField();
    JButton newBtn = new JButton("New element");

    public ContentComponent_menu() {
        setupSearchBar();
        setupButton();
        this.setLayout(null);
        this.setBackground(Color.lightGray);
        this.setPreferredSize(new Dimension(0,100));
        this.setVisible(true);

        this.add(searchBarLabel);
        this.add(searchBarTextField);
        this.add(newBtn);
    }

    private void setupSearchBar() {
        //Label
        searchBarLabel.setFont(new Font(null, Font.BOLD, 18));
        searchBarLabel.setBackground(Color.BLUE);
        searchBarLabel.setForeground(Color.WHITE);
        searchBarLabel.setOpaque(true);
        searchBarLabel.setBounds(50,10,100,80);

        //Text
        searchBarTextField.setFont(new Font(null, Font.BOLD, 18));
        searchBarTextField.setBackground(Color.BLUE);
        searchBarTextField.setForeground(Color.WHITE);
        searchBarTextField.setOpaque(true);
        searchBarTextField.setBounds(150,10,400,80);
        searchBarTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }

    private void setupButton() {
        newBtn.setFont(new Font(null, Font.BOLD, 18));
        newBtn.setBackground(Color.BLUE);
        newBtn.setForeground(Color.WHITE);
        newBtn.setFocusable(false);
        newBtn.setBounds(800,10,200,80);
        newBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newBtn) System.out.println("newBtn");
    }
}
