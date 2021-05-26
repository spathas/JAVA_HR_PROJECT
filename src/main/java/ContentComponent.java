import javax.swing.*;
import java.awt.*;

public class ContentComponent extends JPanel {



    public ContentComponent() {
        setupContent();
    }

    private void setupContent() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout());
//        this.setSize(100,100);
//        this.setBackground(Color.BLUE);
    }

    private void addItems() {
        this.add(new ContentComponent_menu(), BorderLayout.NORTH);
        this.add(new ContentComponent_data(), BorderLayout.CENTER);
    }
}
