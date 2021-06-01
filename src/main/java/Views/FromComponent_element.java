package Views;

import javax.swing.*;
import java.awt.*;

public class FromComponent_element extends JPanel {

    JLabel label = new JLabel();
    JTextField textField = new JTextField();

    public FromComponent_element(String labelText, String textFieldText) {
        this.setFont(new Font(null,Font.BOLD, 18));
        this.setBackground(Color.BLUE);
        this.setForeground(Color.WHITE);
        this.setPreferredSize(new Dimension(0,100));
        this.setLayout(new FlowLayout(FlowLayout.LEADING));


        setupLabel(labelText);
        setupTextField(textFieldText);
        this.add(label);
        this.add(textField);
    }

    // Setup label
    private void setupLabel(String labelText) {
        label.setText(" " + labelText + ": ");
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setFont(new Font(null, Font.BOLD,18));
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(200, 100));

        if(labelText.equals("ID")) textField.setEditable(false);
    }

    private void setupTextField(String textFieldText) {
        textField.setText(textFieldText);
        textField.setFont(new Font(null, Font.BOLD,18));
        textField.setBackground(Color.BLUE);
        textField.setForeground(Color.WHITE);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        textField.setCaretColor(Color.WHITE);
        textField.setPreferredSize(new Dimension(800, 100));
    }

    public JTextField getTextField() {
        return textField;
    }
}
