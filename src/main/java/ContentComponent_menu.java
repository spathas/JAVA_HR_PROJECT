import javax.swing.*;
import java.awt.*;

public class ContentComponent_menu extends JPanel {
    // search bar and btns
    private boolean isVisible;

    public ContentComponent_menu() {
        this.isVisible = true;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.red);
        this.setSize(new Dimension(200, 200));

        setupMenu();
    }

    private void setupMenu() {
        this.setVisible(this.isVisible);
    }
}
