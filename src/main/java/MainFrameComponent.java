import javax.swing.*;
import java.awt.*;

class MainFrameComponent extends JFrame {

    private String title = "HR SYSTEM";
    private boolean isResizable = false;
    private Color bg_color = Color.white;
    private Image icon = new ImageIcon("public/images/logo.png").getImage();

    MainFrameComponent() {
        setupFrame();
        addPanels();
        revalidate();
    }

    private void setupFrame() {
        this.setTitle(this.title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setResizable(this.isResizable);
        this.setSize(new Dimension(1440,900));
        this.setPreferredSize(new Dimension(1440, 900));
        this.setVisible(true);
        this.getContentPane().setBackground(this.bg_color);
        this.setIconImage(icon);
        this.setLayout(new BorderLayout(5,5));
        this.pack();
    }

    private void addPanels() {
        this.add(new ContentComponent(), BorderLayout.CENTER);
        this.add(new SideMenuComponent(), BorderLayout.WEST);
    }

}
