import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class ContentComponent_data__element extends JPanel {

    private int rows;
    private int columns;

    public ContentComponent_data__element(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        this.setFont(new Font(null,Font.BOLD,15));
        this.setOpaque(true);
        this.setForeground(Color.WHITE);
        this.setBackground(Color.BLUE);
        this.setLayout(new GridLayout(rows,columns,15,15));
        this.setBorder(new EmptyBorder(10,5,10,5));
        this.revalidate();
        this.repaint();
    }

    public void setupRows(HashMap<String,String> obj) {
        SortedSet<String> keySet = new TreeSet<>(obj.keySet());

        for (String objKey : keySet) {
            JLabel label = new JLabel(obj.get(objKey));
            label.setSize(10,10);
            label.setOpaque(true);
            label.setForeground(Color.WHITE);
            label.setBackground(Color.BLUE);

            this.add(label);
        }
    }

    public void setupColumns(HashMap<String, String> map) {
        SortedSet<String> keySet = new TreeSet<>(map.keySet());

        for(String key : keySet) {
            JLabel label = new JLabel(key);
            label.setFont(new Font(null,Font.BOLD,15));
            label.setOpaque(true);
            label.setForeground(Color.WHITE);
            label.setBackground(Color.BLUE);

            this.add(label);
        }
    }

}
