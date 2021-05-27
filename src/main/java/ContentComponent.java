import javax.swing.*;
import java.awt.*;

public class ContentComponent extends JPanel {

    GridBagConstraints c = new GridBagConstraints();

    public ContentComponent() {
        setupContent();
    }

    private void setupContent() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.lightGray);
        this.setLayout(new GridLayout(2,1, 2,0));
//        this.setSize(100,100);
//        this.setBackground(Color.BLUE);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;

        addItems();
    }

    private void addItems() {
        this.add(new ContentComponent_data(), BorderLayout.EAST);
        this.add(new ContentComponent_menu(), BorderLayout.WEST);
    }
}
