package com.company.GUI;

import com.company.Model.DatabaseOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage  {
    private JPanel panel;
    private JButton blockUnBlockStudentButton;
    private JButton bookOperationsButton;
    private JButton removeReservationButton;
    private JButton seeReservationHistoryButton;
    private JFrame frame;
    private DatabaseOperation databaseOperation;


    public AdminPage(JFrame frame, DatabaseOperation databaseOperation) {
        this.frame = frame;
        this.databaseOperation = databaseOperation;


        bookOperationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookOperations bookOperations = new BookOperations(databaseOperation);

            }
        });
        blockUnBlockStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlockPage blockPage = new BlockPage(databaseOperation);
            }
        });
        seeReservationHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               HistoryTable historyTable = new HistoryTable(databaseOperation.getAllHistory());

            }
        });
    }

    public static void main(String[] args) {
        DatabaseOperation op = new DatabaseOperation();
        JFrame frame = new JFrame();

        frame.setVisible(true);

        AdminPage sp = new AdminPage(frame,op);

        frame.setContentPane(sp.getPanel());
    }

    private Container getPanel() {
        return panel;
    }
}
