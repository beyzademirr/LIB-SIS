package com.company.GUI;

import com.company.Model.DatabaseOperation;
import com.company.Model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LPanel {
    private JButton profile;
    private JButton books;
    private JButton home;
    private JPanel homePanel;
    private Student student;
    private DatabaseOperation operation;
    private JFrame frame;
    private HomePage homePage;
    private UserPage userPage;

    public LPanel(Student student, DatabaseOperation operation, JFrame frame, HomePage homePage, UserPage userPage) {
        this.student = student;
        this.operation = operation;
        this.frame = frame;
        this.homePage=homePage;
        this.userPage=userPage;
        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add(userPage.getPanel(), BorderLayout.CENTER);
                frame.getContentPane().add(homePanel, BorderLayout.WEST);
                frame.pack();
                frame.repaint();
                frame.revalidate();
                frame.setSize(1600, 900);

            }
        });
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add(homePage.getPanel1(), BorderLayout.CENTER);
                frame.getContentPane().add(homePanel, BorderLayout.WEST);
                frame.pack();
                frame.repaint();
                frame.revalidate();
                frame.setSize(1600, 900);
            }
        });
        books.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemList table = new ItemList(operation.getAllItems());
            }
        });
    }

    public JPanel getHomePanel() {
        return homePanel;
    }
}
