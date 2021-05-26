import javax.swing.*;
import java.awt.*;

class MainFrame extends JFrame {

    private String title;
    private boolean isResizable;
    private Color bg_color;
    private Image icon;

    MainFrame() {
        this.title = "Postings";
        this.isResizable = true;
        this.bg_color = Color.darkGray;
        this.icon = new ImageIcon("public/images/customer.png").getImage();
    }

    public void basicComponent() {
//        Setup frame
        this.setTitle(this.title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(this.isResizable);
        this.setSize(1240, 920);
        this.setVisible(true);
        this.getContentPane().setBackground(this.bg_color);
        this.setIconImage(icon);
        this.setLayout(new BorderLayout(5,5));
        this.pack();

//        Add panels
        this.add(new SideMenu(), BorderLayout.WEST);
        this.add(new Content(), BorderLayout.CENTER);
    }
}
