package com.company.GUI;

import com.company.Model.DatabaseOperation;

import javax.swing.*;
import java.awt.event.*;


public class SignUpPage extends JFrame {
    private JTextField email;
    private JButton signUpButton;
    private JPanel panel;
    private JTextField fName;
    private JTextField lName;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private DatabaseOperation operation;
    private final JFrame frame;

    public SignUpPage(JFrame frame, DatabaseOperation operation) {
        this.operation = operation;
        this.frame = frame;

        frame.setVisible(true);
        frame.setContentPane(panel);

        signUpButton.addActionListener(new SignUpListener());
    }
    
    public JPanel getPanel() {
        return panel;
    }

    public boolean signUp(String email, String name, String surname, String password1, String password2) {




       if (email.equals("")) {
            JOptionPane.showMessageDialog(null, "Email cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }   else if (password1.equals("")) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (name.equals("")) {
            JOptionPane.showMessageDialog(null, "First Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (surname.equals("")) {
            JOptionPane.showMessageDialog(null, "Last Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (password1.equals(password2)) {

            boolean success = operation.addStudent(name, surname, email, password1);
            if (success) {
                LoginPage reLogin = new LoginPage(frame, operation);
                frame.getContentPane().removeAll();
                frame.repaint();

                frame.getContentPane().add(reLogin.getPanel());
                frame.revalidate();
                return true;
            }
            else {
                JOptionPane.showMessageDialog(null, "There is another account using same email", "Error", JOptionPane.ERROR_MESSAGE);
                LoginPage reLogin = new LoginPage(frame , operation);
                frame.getContentPane().removeAll();
                frame.repaint();

                frame.getContentPane().add(reLogin.getPanel());
                frame.revalidate();
                return false;
            }


        } else {
            passwordField1.setText("");
            passwordField2.setText("");
            JOptionPane.showMessageDialog(null, "Passwords don't match", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }



    public class SignUpListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            signUp(email.getText(),fName.getText(), lName.getText(), passwordField1.getText(),passwordField2.getText() );
        }


    }
}
