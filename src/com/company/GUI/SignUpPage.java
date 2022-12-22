package com.company.GUI;

import com.company.Model.DatabaseOperation;

import javax.swing.*;
import java.awt.event.*;


public class SignUpPage extends JFrame {
    private JTextField email;
    private JButton signUpButton;

    public JPanel getPanel() {
        return panel;
    }

    private JPanel panel;
    private JTextField fName;
    private JTextField lName;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private DatabaseOperation operation;


    public SignUpPage(DatabaseOperation operation) {
        this.operation = operation;

        setContentPane(panel);
        setSize(800, 450);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        signUpButton.addActionListener(new SignUpListener());
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
                JOptionPane.showMessageDialog(null, "SignUp is successful, please login", "Information", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                return true;
            }
            else {
                JOptionPane.showMessageDialog(null, "There is another account using same email", "Error", JOptionPane.ERROR_MESSAGE);

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

    public static void main(String[] args) {
        DatabaseOperation op = new DatabaseOperation();
        JFrame frame = new JFrame();

        SignUpPage sp = new SignUpPage(op);
    }
}
