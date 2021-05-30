import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class ContentComponent_data__element extends JButton implements ActionListener {

    ContentComponent content;
    int id;

    public ContentComponent_data__element(ContentComponent content, int rows, int columns, int id) {
        this.content = content;
        this.id = id;

        this.setFont(new Font(null,Font.BOLD,15));
        this.setOpaque(true);
        this.setForeground(Color.WHITE);
        this.setBackground(Color.BLUE);
        this.setLayout(new GridLayout(rows,columns,20,20));
        this.setBorder(new EmptyBorder(10,5,10,5));
        this.setFocusable(false);
        this.addActionListener(this);
        this.revalidate();
        this.repaint();
    }

    public void setupRows(HashMap<String,String> obj) {
        SortedSet<String> keySet = new TreeSet<>(obj.keySet());

        for (String objKey : keySet) {
            JLabel label = new JLabel(obj.get(objKey));
            label.setBorder(new EmptyBorder(10,10,10,10));
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
            label.setBorder(new EmptyBorder(10,10,10,10));
            label.setFont(new Font(null,Font.BOLD,15));
            label.setOpaque(true);
            label.setForeground(Color.WHITE);
            label.setBackground(Color.BLUE);

            this.add(label);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this) {
            if(this.id != 0 )
            content.addFormGet(this.id);
        }
    }
}
