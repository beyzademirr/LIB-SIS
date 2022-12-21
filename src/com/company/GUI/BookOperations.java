package com.company.GUI;

import com.company.Model.DatabaseOperation;

import javax.swing.*;

public class BookOperations extends JFrame {
    private JButton add;
    private JButton delete;
    private JButton modify;
    private JButton deletex;
    private JTextField itemId;
    private JTextField author;
    private JTextField userId;
    private JTextField availability;
    private JTextField year;
    private JTextField pages;
    private JTextField loation;
    private JTextField reservationDate;
    private JPanel panel;
    private DatabaseOperation operation;

    public BookOperations(DatabaseOperation operation) {
        this.operation = operation;

        setContentPane(panel);
        setSize(800, 450);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);}
}
