import javax.swing.*;
import java.awt.*;

class MainFrameComponent extends JFrame {

    private String title;
    private boolean isResizable;
    private Color bg_color;
    private Image icon;

    MainFrameComponent() {
        // Frame init
        this.title = "HR SYSTEM";
        this.isResizable = false;
        this.bg_color = Color.darkGray;
        this.icon = new ImageIcon("public/images/logo.png").getImage();

        setupFrame();
        addPanels();
    }

    private void setupFrame() {
        this.setTitle(this.title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(this.isResizable);
        this.setSize(1280, 740);
        this.setVisible(true);
        this.getContentPane().setBackground(this.bg_color);
        this.setIconImage(icon);
        this.setLayout(new BorderLayout(10,10));
//        this.pack();
    }

    private void addPanels() {
        this.add(new ContentComponent(), BorderLayout.CENTER);
        this.add(new SideMenuComponent(), BorderLayout.WEST);
    }

}
