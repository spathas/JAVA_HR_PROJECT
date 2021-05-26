import javax.swing.*;
import java.awt.*;

public class ContentComponent_menu extends JPanel {
    // search bar and btns
    private boolean isVisible;

    public ContentComponent_menu() {
        this.isVisible = false;
        setupMenu();
    }

    private void setupMenu() {
        this.setLayout(new FlowLayout());
        this.setVisible(this.isVisible);
    }
}
