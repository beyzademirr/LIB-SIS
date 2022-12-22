package com.company.GUI;
import com.company.Model.DatabaseOperation;
import com.company.Model.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage {


    private JPanel panel1;
    private JTextField textField1;
    private JButton searchByNameButton;
    private JButton searchByAuthorButton;
    private DatabaseOperation operation;
    private JFrame frame;
    private Student student;

    public HomePage(DatabaseOperation operation, JFrame frame, Student student) {
        this.operation = operation;
        this.frame=frame;
        this.student=student;

        searchByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemList itemList = new ItemList(operation.findItemByName(textField1.getText()));
            }
        });
        searchByAuthorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemList itemList = new ItemList(operation.findItemByAuthor(textField1.getText()));
            }
        });
    }


    public JPanel getPanel1() {
        return panel1;
    }

    public static void main(String[] args) {
        DatabaseOperation op = new DatabaseOperation();
        JFrame frame = new JFrame();

        frame.setVisible(true);

        //HomePage sp = new HomePage(op);

       // frame.setContentPane(sp.getPanel1());
    }
}
