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

    public ContentComponent_data__element(ContentComponent content, int id) {
        this.content = content;
        this.id = id;

        this.setFont(new Font(null,Font.BOLD,15));
        this.setOpaque(true);
        this.setForeground(Color.WHITE);
        this.setBackground(Color.BLUE);

        this.setLayout(new GridBagLayout());

        this.setFocusable(false);
        this.addActionListener(this);
        this.revalidate();
        this.repaint();
    }

    public void setupRows(HashMap<String,String> obj) {
        SortedSet<String> keySet = new TreeSet<>(obj.keySet());

        int count = 0;
        for (String objKey : keySet) {

            // Css code to wrap the text content.
            JLabel label = new JLabel(String.format("<html><body style=\"text-align: justify;  text-justify: inter-word;\">%s</body></html>", obj.get(objKey)));
            label.setBorder(new EmptyBorder(10, 10, 10, 10));
            label.setSize(10, 10);
            label.setOpaque(true);
            label.setForeground(Color.WHITE);
            label.setBackground(Color.BLUE);

            //Setup layout constraints
            GridBagConstraints gc = new GridBagConstraints();
            gc.fill = GridBagConstraints.BOTH;
            gc.gridx = count;
            gc.gridy = 0;
            gc.weightx = 0.1;
            gc.weighty = 0.1;
            // Set a bigger space for description component.
            if(objKey.equals("DESCRIPTION")) {
                gc.weightx = 0.7;
                label.setFont(new Font(null,Font.BOLD,10));
            }

            this.add(label, gc);

            count++;
        }
    }

    public void setupColumns(HashMap<String, String> map) {
        SortedSet<String> keySet = new TreeSet<>(map.keySet());

        int count = 0;
        for(String key : keySet) {
            JLabel label = new JLabel(key);
            label.setBorder(new EmptyBorder(10,10,10,10));
            label.setFont(new Font(null,Font.BOLD,15));
            label.setOpaque(true);
            label.setForeground(Color.WHITE);
            label.setBackground(Color.BLUE);

            //Setup layout constraints
            GridBagConstraints gc = new GridBagConstraints();
            gc.fill = GridBagConstraints.CENTER;
            gc.gridx = count;
            gc.gridy = 1;
            gc.weightx = 0.1;
            gc.weighty = 0.1;
            // Set a bigger space for description component.
            if(key.equals("DESCRIPTION")) gc.weightx = 1;

            this.add(label, gc);
            count++;
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
