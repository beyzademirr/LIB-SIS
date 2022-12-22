package com.company.GUI;
import com.company.Model.DatabaseOperation;
import com.company.Model.Item;
import com.company.Model.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
                ItemList itemList = new ItemList(findItemByName(textField1.getText()));
            }
        });
        searchByAuthorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemList itemList = new ItemList(findItemByAuthor(textField1.getText()));
            }
        });
    }

    public HomePage() {
        operation = new DatabaseOperation();
    }


    public JPanel getPanel1() {
        return panel1;
    }

    public ArrayList<Item> findItemByName(String s){
       return operation.findItemByName(s);}

    public ArrayList<Item> findItemByAuthor(String s){
        return operation.findItemByAuthor(s);}
}
