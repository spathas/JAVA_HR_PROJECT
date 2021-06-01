import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormComponent_buttons extends JPanel implements ActionListener {

    ContentComponent content;
    FormComponent form;

    JButton findButton = new JButton();
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
            setupFindButton();

            this.add(updateButton);
            this.add(deleteButton);
            this.add(cancelButton);
            this.add(findButton);
        }
    }

    private void setupDeleteButton() {
        deleteButton.setFont(new Font(null, Font.BOLD, 18));
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBorder(BorderFactory.createLineBorder(Color.RED));
        deleteButton.setFocusable(false);
        deleteButton.setPreferredSize(new Dimension(150, 50));

        deleteButton.addActionListener(this);
    }

    private void setUpdateButton() {
        updateButton.setFont(new Font(null, Font.BOLD, 18));
        updateButton.setBackground(Color.YELLOW.darker());
        updateButton.setForeground(Color.WHITE);
        updateButton.setBorder(BorderFactory.createLineBorder(Color.YELLOW.darker()));
        updateButton.setFocusable(false);
        updateButton.setPreferredSize(new Dimension(150, 50));

        updateButton.addActionListener(this);
    }

    private void setupCancelButton() {
        cancelButton.setFont(new Font(null, Font.BOLD, 18));
        cancelButton.setBackground(Color.GRAY);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        cancelButton.setFocusable(false);
        cancelButton.setPreferredSize(new Dimension(150, 50));

        cancelButton.addActionListener(this);
    }

    private void setupSubmitButton() {
        submitButton.setFont(new Font(null, Font.BOLD, 18));
        submitButton.setBackground(Color.GREEN.darker());
        submitButton.setForeground(Color.WHITE);
        submitButton.setBorder(BorderFactory.createLineBorder(Color.GREEN.darker()));
        submitButton.setFocusable(false);
        submitButton.setPreferredSize(new Dimension(150, 50));

        submitButton.addActionListener(this);
    }



    private void setupFindButton() {
        //Setup Image Icon
        Image image = new ImageIcon("public/images/share.png").getImage();
        ImageIcon icon = new ImageIcon(image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH));
        findButton.setIcon(icon);

        findButton.setFont(new Font(null, Font.BOLD, 18));
        findButton.setBackground(Color.lightGray);
        findButton.setForeground(Color.BLACK);
        findButton.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        findButton.setFocusable(false);
        findButton.setPreferredSize(new Dimension(50, 50));

        findButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancelButton) {
            content.addItems();

            content.menu.resetAll_btn.setBackground(Color.RED);
        }

        if(e.getSource() == submitButton) {
            form.insertNewElement(content.getSelector());
            //Refresh data in content component.
            content.refreshObjects();
            content.addItems();

            content.menu.resetAll_btn.setBackground(Color.RED);
        }

        if(e.getSource() == updateButton) {
            form.updateElement(content.getSelector());
            //Refresh data in content component.
            content.refreshObjects();
            content.addItems();

            content.menu.resetAll_btn.setBackground(Color.RED);
        }

        if(e.getSource() == deleteButton) {
            form.deleteElement(content.getSelector());
            //Refresh data in content component.
            content.refreshObjects();
            content.addItems();

            content.menu.resetAll_btn.setBackground(Color.RED);
        }

        if(e.getSource() == findButton) {
            form.findElement(content.getSelector());
        }
    }
}
