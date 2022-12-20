package com.company.Model;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

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

    public boolean checkForAdminLogin(String adminEmail, String adminPassword) {
        try {
            // Creating sql query
            String query = "SELECT * FROM Admin WHERE AdminEmail = \"" + adminEmail + "\" AND AdminPassword = \"" + adminPassword + "\"";

            // Creating statement for database
            statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            // If the admin exists return true
            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // If the admin doesn't exists return false
        return false;

     }
     public boolean checkForStudentLogin(String studentEmail, String studentPassword) {

        try {

            // Creating sql query
            String query = "SELECT studentName FROM Student WHERE studentEmail = \"" + studentEmail + "\" AND studentPassword = \"" + studentPassword + "\"";

            // Creating statement for database
            statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            // If the student exists return true
            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // If the student doesn't exists return false
        return false;

    }
     public boolean addStudent(String studentName, String studentSurname, String studentEmail, String studentPassword) {
        try {

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


    public Student pull_student(String studentEmail) {
        try {

            // Writing SQL query for push
            String query = "SELECT * FROM Student WHERE studentEmail = '" + studentEmail + "'";
            statement = con.createStatement();

            // Creating ResultSet type to pull data from database
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                return new Student(resultSet.getString("studentName"), resultSet.getString("studentSurname"),  studentEmail, resultSet.getString("studentPassword"), resultSet.getBoolean("studentPassword"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return null;
    }


    public void addItem(String location, String author, int year, int pages, boolean isAvailable, String name) {

        try {

            String query = "INSERT INTO Item (Author, Availability, Year, Pages, Name, Location) " +
                    "VALUES (" +"'" + author + "','" + isAvailable + "','" + year + "','" + pages + "','"+ name +"','" + location +"');";

            statement = con.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();

    }
    }

    public Item findItemById(int ID) {
        try {

            String query = "SELECT * FROM Item WHERE ItemID = " + ID + ";";
            statement = con.createStatement();
            ResultSet set = statement.executeQuery(query);
            set.next();
            return new Item(set.getInt(1), set.getString(2), set.getBoolean(3),
                    set.getInt(4), set.getInt(5), set.getString(6), set.getString(7));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public ArrayList<Item> findItemByName(String name) {
        try {

            ArrayList<Item> items = new ArrayList<>();
            String query = "SELECT * FROM Item WHERE Name = " + name + ";";
            statement = con.createStatement();
            ResultSet set = statement.executeQuery(query);


            if (set.next()) { items.add(new Item(set.getInt(1), set.getString(2), set.getBoolean(3),
                    set.getInt(4), set.getInt(5), set.getString(6), set.getString(7)));}

            return items;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
    public ArrayList<Item> findItemByAuthor(String name) {
        try {

            ArrayList<Item> items = new ArrayList<>();
            String query = "SELECT * FROM Item WHERE Author = " + name + ";";
            statement = con.createStatement();
            ResultSet set = statement.executeQuery(query);


            if (set.next()) { items.add(new Item(set.getInt(1), set.getString(2), set.getBoolean(3),
                    set.getInt(4), set.getInt(5), set.getString(6), set.getString(7)));}

            return items;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public ArrayList<Item> getAllItems(String name) {
        try {

            ArrayList<Item> items = new ArrayList<>();
            String query = "SELECT * FROM Item;";
            statement = con.createStatement();
            ResultSet set = statement.executeQuery(query);


            if (set.next()) { items.add(new Item(set.getInt(1), set.getString(2), set.getBoolean(3),
                    set.getInt(4), set.getInt(5), set.getString(6), set.getString(7)));}

            return items;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public void deleteItem(int ID) {

        try {
           String query = "DELETE FROM Item WHERE ItemID = " + ID + ";";
            statement = con.createStatement();
             statement.executeQuery(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
