package com.company.GUI;

import com.company.Model.DatabaseOperation;

import javax.swing.*;
import java.awt.*;

public class LoginPage {
    private final JFrame frame;
    private DatabaseOperation operation;
    private JPanel panel;

    public LoginPage(JFrame frame, DatabaseOperation operation) {
        this.operation = operation;
        this.frame = frame;
    }

    public JPanel getPanel() {

        return panel;
    }
}
