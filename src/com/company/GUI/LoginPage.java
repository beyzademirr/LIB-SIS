package com.company.GUI;

import com.company.Model.DatabaseOperation;
import com.company.Model.History;
import com.company.Model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

public class LoginPage {
    private final JFrame frame;
    private DatabaseOperation operation;
    private JPanel panel;
    private JButton loginButton;
    private JButton signUpButton;
    private JPasswordField passwordField1;
    private JLabel password;
    private JTextField email;

    public LoginPage(JFrame frame, DatabaseOperation operation) {
        this.operation = operation;
        this.frame = frame;
        frame.setContentPane(panel);
        frame.setVisible(true);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               login(email.getText(), passwordField1.getText());

            }
        });


        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();

                SignUpPage signup = new SignUpPage(frame, operation);
                frame.setContentPane(signup.getPanel());
                frame.revalidate();
            }
        });
    }

    public JPanel getPanel() {

        return panel;
    }

    public boolean login(String email, String password){


        if (email.equals("admin") && password.equals("admin")) {

            //create admin login

            return true;

        } else {
            boolean loginCheck = operation.checkForStudentLogin(email, password);


            if (loginCheck) {
                JOptionPane.showMessageDialog(null, "Logged in.");
                frame.getContentPane().removeAll();
                frame.setLayout(new BorderLayout());

                Student student = operation.pull_student(email);
               // Library library = new Library(frame, operation, student);
                //LPanel lPanel = new LPanel(frame, operation, garage, student);
                //frame.getContentPane().add(library.productPanel, BorderLayout.CENTER);
                //frame.getContentPane().add(lPanel.lPanel, BorderLayout.WEST);
                frame.pack();
                frame.repaint();
                frame.revalidate();

                return true;

            } else if (email.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(null, "Please make sure no credentials missing", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            } else if (!loginCheck) {
                JOptionPane.showMessageDialog(null, "Wrong data entered.");
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DatabaseOperation op = new DatabaseOperation();
        JFrame frame = new JFrame();

        LoginPage sp = new LoginPage(frame, op);
    }


}
