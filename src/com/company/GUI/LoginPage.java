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
        
        frame.setSize(1600, 900);
        //frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setLocationRelativeTo(null);
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
                SignUpPage signup = new SignUpPage(operation);
            }
        });
    }

    public JPanel getPanel() {

        return panel;
    }

    public boolean login(String email, String password){

        if (operation.checkForAdminLogin(email, password)) {
            AdminPage adminPage = new AdminPage(frame,operation);
            frame.getContentPane().removeAll();
            frame.setContentPane(adminPage.getPanel());
            frame.pack();
            frame.repaint();
            frame.revalidate();
            frame.setSize(1600, 900);

            return true;

        } else {
            boolean loginCheck = operation.checkForStudentLogin(email, password);


            if (loginCheck) {
                JOptionPane.showMessageDialog(null, "Logged in.");
                frame.getContentPane().removeAll();
                frame.setLayout(new BorderLayout());

                Student student = operation.pullStudent(email);

                UserPage userPage=new UserPage(operation, student);
                HomePage homePage = new HomePage(operation, frame, student);
                LPanel lPanel = new LPanel(student,operation,frame, homePage, userPage);

                frame.getContentPane().add(homePage.getPanel1(), BorderLayout.CENTER);
                frame.getContentPane().add(lPanel.getHomePanel(), BorderLayout.WEST);
                frame.pack();
                frame.repaint();
                frame.revalidate();
                frame.setSize(1600, 900);

                return true;

            } else if (email.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(null, "Please make sure no credentials missing", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            } else if (!loginCheck) {
                JOptionPane.showMessageDialog(null, "Wrong credentials entered.");
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
