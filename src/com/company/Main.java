package com.company;

import com.company.GUI.HomePage;
import com.company.GUI.LPanel;
import com.company.GUI.LoginPage;
import com.company.Model.DatabaseOperation;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        DatabaseOperation operation = new DatabaseOperation();
        JFrame frame = new JFrame();
        LoginPage loginPage = new LoginPage(frame,operation);



    }


}
