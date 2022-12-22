package com.company.GUI;

import com.company.Model.DatabaseOperation;
import com.company.Model.Item;
import com.company.Model.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPage {
    private JTextField itemId;
    private JButton resButton;
    private JButton historyButton;
    private JPanel Panel;
    private DatabaseOperation operation;
    private Student student;

    public UserPage(DatabaseOperation operation, Student student) {
        this.operation = operation;
        this.student = student;
        resButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!itemId.getText().equals("")){
                    int id = Integer.parseInt(itemId.getText());
                    boolean isReserved = operation.reserveItemWithoutId(student.getStudentId(), id);
                    if(!student.isBlocked()){JOptionPane.showMessageDialog(null, "You are blocked, contact an admin");}
                    else if(isReserved){JOptionPane.showMessageDialog(null, "Item is reserved successfully");}
                    else{JOptionPane.showMessageDialog(null, "Item is not available");}
                }else{
                    JOptionPane.showMessageDialog(null, "Item ID is missing!");
                }
            }
        });
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistoryTable table = new HistoryTable(operation.getStudentHistory(student.getStudentId()));
                
            }
        });
    }

    public JPanel getPanel() {
        return Panel;
    }

    public static void main(String[] args) {
        DatabaseOperation op = new DatabaseOperation();
        JFrame frame = new JFrame();
        Student student= new Student(22,"uu@ozu.edu.tr", "djkfndsg", null,false);
        frame.setVisible(true);

        UserPage sp = new UserPage(op, student);

        frame.setContentPane(sp.getPanel());
    }

}
