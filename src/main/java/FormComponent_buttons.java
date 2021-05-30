import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;

public class FormComponent_buttons extends JPanel implements ActionListener {

    ContentComponent content;
    FormComponent form;

    JButton deleteButton = new JButton("DELETE");
    JButton updateButton = new JButton("UPDATE");
    JButton cancelButton = new JButton("CANCEL");
    JButton submitButton = new JButton("SUBMIT");

    public FormComponent_buttons(ContentComponent content, FormComponent form, String typeOfContent) {
        this.content = content;
        this.form = form;
        this.setBackground(Color.lightGray);
        this.setLayout(new FlowLayout(FlowLayout.TRAILING));


        if(typeOfContent.equals("newElement")) {
            setupSubmitButton();
            setupCancelButton();

            this.add(submitButton);
            this.add(cancelButton);
        }

        if(typeOfContent.equals("getElement")) {
            setupDeleteButton();
            setUpdateButton();
            setupCancelButton();

            this.add(updateButton);
            this.add(deleteButton);
            this.add(cancelButton);
        }
    }

    private void setupDeleteButton() {
        deleteButton.setFont(new Font(null, Font.BOLD, 18));
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        deleteButton.setFocusable(false);
        deleteButton.setPreferredSize(new Dimension(150, 50));

//        deleteButton.addActionListener(this);
    }

    private void setUpdateButton() {
        updateButton.setFont(new Font(null, Font.BOLD, 18));
        updateButton.setBackground(Color.YELLOW.darker());
        updateButton.setForeground(Color.WHITE);
        updateButton.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        updateButton.setFocusable(false);
        updateButton.setPreferredSize(new Dimension(150, 50));

//        updateButton.addActionListener(this);
    }

    private void setupCancelButton() {
        cancelButton.setFont(new Font(null, Font.BOLD, 18));
        cancelButton.setBackground(Color.GRAY);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        cancelButton.setFocusable(false);
        cancelButton.setPreferredSize(new Dimension(150, 50));

        cancelButton.addActionListener(this);
    }

    private void setupSubmitButton() {
        submitButton.setFont(new Font(null, Font.BOLD, 18));
        submitButton.setBackground(Color.GREEN.darker());
        submitButton.setForeground(Color.WHITE);
        submitButton.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        submitButton.setFocusable(false);
        submitButton.setPreferredSize(new Dimension(150, 50));

        submitButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancelButton) {
            content.addItems();
        }
        if(e.getSource() == submitButton) {
            form.insertNewElement();
        }
    }
}
