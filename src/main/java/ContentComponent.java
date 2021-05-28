import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ContentComponent extends JPanel {

    public static ContentComponent_data data = new ContentComponent_data();

    public static ContentComponent_data c = ContentComponent.setupComponent("jobApplicants_btn", data);

    public ContentComponent() {
        setupContent();
    }

    private void setupContent() {
        this.setLayout(new BorderLayout(0,5));
        this.setBackground(Color.white);

        addItems();
    }

    public static ContentComponent_data setupComponent(String selector, ContentComponent_data data) {
        int columns = 1;
        data.setupColumns(selector);
        int rows = data.setupRows(selector);

        data.setLayout(new GridLayout(rows,columns,0,15));
        data.validate();
        data.repaint();

        return data;
    }

    private void addItems() {
        this.add(c, BorderLayout.CENTER);
        this.add(new ContentComponent_menu(), BorderLayout.NORTH);
    }
}
