package com.company.GUI;

import com.company.Model.DatabaseOperation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        blockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(textField1.getText());
                blockStudent(id);
                dispose();
            }
        });
        unBlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(textField1.getText());
                unBlockStudent(id);
                dispose();
            }
        });
    }

    public void blockStudent(int id){
    operation.blockStudent(id);
    }

    public void unBlockStudent(int id){
        operation.unBlockStudent(id);
    }
}
