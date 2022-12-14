package com.company.Model;

import javax.swing.*;
import java.sql.*;

public class DatabaseOperation {
    public Connection con;
    public Statement statement;
    public PreparedStatement preparedStatement;

    boolean isConnected = false;

    public DatabaseOperation() {

        JFrame frame = new JFrame();
        String url = "jdbc:mysql://:" + Database.DBport + "/" + "lib-sis";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            System.out.println("Couldn't Find JDBC Driver");
        }

        try {
            con = DriverManager.getConnection(url, Database.DBusername, Database.DBpassword);
            System.out.println("Database Connection Successful");
            isConnected = true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Database Connection Fails.Try Again.");
            System.exit(-1);
        }
    }

    public boolean checkForConnection(){
        return isConnected;
    }

    public String testPull (String studentEmail) {
        try {
            // Writing SQL query for push
            String query = "SELECT * FROM Student WHERE studentEmail = '" + studentEmail + "'";
            statement = con.createStatement();

            // Creating ResultSet type to pull data from database
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                String  a = (resultSet.getString("studentName"));
                return a;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "NULL";
        }
       return "NULL";
}

    public boolean testPush(String studentName, String studentSurname, String studentEmail, String studentPassword) {
        try {
            // Writing SQL query for push
            String query = "INSERT INTO Student (StudentName, StudentSurname, StudentEmail, StudentPassword, StudentStatus)  " +
                    "VALUES (" +"'" + studentName + "','" + studentSurname + "','" + studentEmail + "','" + studentPassword + "',"+ true +");";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate(query);

            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }
}
