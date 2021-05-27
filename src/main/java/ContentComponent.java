import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ContentComponent extends JPanel {

    public ContentComponent() {
        setupContent();
        revalidate();
    }

    private void setupContent() {
        this.setLayout(new BorderLayout(0,5));
        this.setBackground(Color.white);
//        this.setSize(100,100);
//        this.setBackground(Color.BLUE);

        addItems();
    }

    private void addItems() {
        this.add(new ContentComponent_data(), BorderLayout.CENTER);
        this.add(new ContentComponent_menu(), BorderLayout.NORTH);
    }
}
