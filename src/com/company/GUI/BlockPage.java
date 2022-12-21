package com.company.GUI;

import com.company.Model.DatabaseOperation;

import javax.swing.*;

public class BlockPage extends JFrame {
    private JPanel panel;
    private JTextField textField1;
    private JButton blockButton;
    private JButton unBlockButton;
    private DatabaseOperation operation;

    public BlockPage (DatabaseOperation operation){
        this.operation=operation;
        setContentPane(panel);
        setSize(800, 450);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }



}
