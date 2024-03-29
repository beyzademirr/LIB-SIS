package com.company.GUI;

import com.company.Model.DatabaseOperation;
import com.company.Model.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookOperations extends JFrame {
    private JButton add;
    private JButton delete;
    private JButton modify;
    private JButton removeRes;
    private JTextField itemId;
    private JTextField author;
    private JTextField year;
    private JTextField pages;
    private JTextField location;
    private JTextField name;
    private JPanel panel;
    private JTextField resID;
    private DatabaseOperation operation;

    public BookOperations(DatabaseOperation operation) {
        this.operation = operation;

        setContentPane(panel);
        setSize(800, 450);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!location.getText().equals("") && !author.getText().equals("") && !year.getText().equals("") && !pages.getText().equals("")){
                    int yearInt = Integer.parseInt(year.getText());
                    int pagesInt = Integer.parseInt(pages.getText());
                    addItem(location.getText(), author.getText(), yearInt, pagesInt, true, name.getText());
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please make sure all related fields are full");
                }
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!itemId.getText().equals("")){
                    int id = Integer.parseInt(itemId.getText());
                    deleteItem(id);
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Item ID is missing!");
                }
            }
        });
        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!itemId.getText().equals("")){
                    int id = Integer.parseInt(itemId.getText());
                    Item item = operation.findItemById(id);
                    if (item.isAvailable()){
                        modifyItem(id, false);
                    }
                    else{
                        modifyItem(id, true);
                    }
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Item ID is missing!");
                }
            }
        });
        removeRes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!resID.getText().equals("")){
                    int id = Integer.parseInt(resID.getText());
                    removeRes(id);
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Reservation ID is missing!");
                }
            }
        });
    }

    public BookOperations() {
        operation = new DatabaseOperation();
    }

    public void addItem(String location, String author, int year, int pages, boolean isAvailable, String name) {
        operation.addItem(location, author, year, pages, true, name);
    }

    public void deleteItem(int id){
        operation.deleteItem(id);
    }

    public void modifyItem(int id,boolean b){
        operation.modifyItem(id, b);

    }

    public void removeRes(int id){
        operation.removeRes(id);

    }
}
