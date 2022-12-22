package com.company.Model;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

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


    public Student pullStudent(String studentEmail) {
        try {

            // Writing SQL query for push
            String query = "SELECT * FROM Student WHERE studentEmail = '" + studentEmail + "'";
            statement = con.createStatement();

            // Creating ResultSet type to pull data from database
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                return new Student(resultSet.getInt("StudentID"), resultSet.getString("StudentName"),resultSet.getString("StudentSurname"), resultSet.getString("StudentEmail"), resultSet.getString("StudentPassword"), resultSet.getBoolean("StudentStatus"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return null;
    }
    public void deleteStudent(int ID) {

        try {
            String query = "DELETE FROM Student WHERE StudentID = " + ID + ";";
            statement = con.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void deleteStudentByEmail(String email) {

        try {
            String query = "DELETE FROM Student WHERE StudentEmail= '" + email + "'";
            statement = con.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addItem(String location, String author, int year, int pages, boolean isAvailable, String name) {

        try {

            String query = "INSERT INTO Item (Author, Availability, Year, Pages, ItemName, Location) " +
                    "VALUES (" +"'" + author + "'," + true + ",'" + year + "','" + pages + "','"+ name +"','" + location +"');";

            statement = con.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    public void addItemById(int id, String location, String author, int year, int pages, boolean isAvailable, String name) {
        try {
            String query = "INSERT INTO Item (ItemID, Author, Availability, Year, Pages, ItemName, Location) " +
                    "VALUES (" +"" + id + ",'"+ author + "'," + true + ",'" + year + "','" + pages + "','"+ name +"','" + location +"');";

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
            if(set.next()) {
                Item item = new Item();
                item.setItemId(set.getInt("ItemID"));
                item.setLocation(set.getString("Location"));
                item.setAuthor(set.getString("Author"));
                item.setAvailable(set.getBoolean("Availability"));
                item.setName(set.getString("ItemName"));
                item.setPages( set.getInt("Pages"));
                item.setYear(set.getInt("Year"));
                return item;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public Item findItemByLocation(String location) {
        try {
            String query = "SELECT * FROM Item WHERE Location = '" + location + "'";
            statement = con.createStatement();
            ResultSet set = statement.executeQuery(query);
            if(set.next()) {
                Item item = new Item();
                item.setItemId(set.getInt("ItemID"));
                item.setLocation(set.getString("Location"));
                item.setAuthor(set.getString("Author"));
                item.setAvailable(set.getBoolean("Availability"));
                item.setName(set.getString("ItemName"));
                item.setPages( set.getInt("Pages"));
                item.setYear(set.getInt("Year"));
                return item;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public ArrayList<Item> findItemByName(String name) {
        try {

            ArrayList<Item> items = new ArrayList<>();
            String query = "SELECT * FROM Item WHERE ItemName = '" + name + "';";
            statement = con.createStatement();
            ResultSet set = statement.executeQuery(query);

            while (set.next()) {
                Item item = new Item();
                item.setItemId(set.getInt("ItemID"));
                item.setLocation(set.getString("Location"));
                item.setAuthor(set.getString("Author"));
                item.setAvailable(set.getBoolean("Availability"));
                item.setName(set.getString("ItemName"));
                item.setPages( set.getInt("Pages"));
                item.setYear(set.getInt("Year"));
                items.add(item);
            }

            return items;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
    public ArrayList<Item> findItemByAuthor(String name) {
        try {

            ArrayList<Item> items = new ArrayList<>();
            String query = "SELECT * FROM Item WHERE Author = '" + name + "';";
            statement = con.createStatement();
            ResultSet set = statement.executeQuery(query);

            while (set.next()) {
                Item item = new Item();
                item.setItemId(set.getInt("ItemID"));
                item.setLocation(set.getString("Location"));
                item.setAuthor(set.getString("Author"));
                item.setAvailable(set.getBoolean("Availability"));
                item.setName(set.getString("ItemName"));
                item.setPages( set.getInt("Pages"));
                item.setYear(set.getInt("Year"));
                items.add(item);
            }

            return items;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public ArrayList<Item> getAllItems() {
        try {

            ArrayList<Item> items = new ArrayList<>();
            String query = "SELECT * FROM Item;";
            statement = con.createStatement();
            ResultSet set = statement.executeQuery(query);



            while (set.next()) {
                Item item = new Item();
                item.setItemId(set.getInt("ItemID"));
                item.setLocation(set.getString("Location"));
                item.setAuthor(set.getString("Author"));
                item.setAvailable(set.getBoolean("Availability"));
                item.setName(set.getString("ItemName"));
                item.setPages( set.getInt("Pages"));
                item.setYear(set.getInt("Year"));
                items.add(item);

            }

            return items;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public void modifyItem(int ID, boolean status) {

        try {
            String query = "UPDATE Item SET Availability = " + status + " WHERE ItemID= "+ ID +"";
            statement = con.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void deleteItem(int ID) {
        try {
           String query = "DELETE FROM Item WHERE ItemID = " + ID + "";
            statement = con.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void blockStudent( int ID){
        try {
            String query = "UPDATE Student SET StudentStatus = " + false + " WHERE StudentID= "+ ID +"";
            statement = con.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void unBlockStudent( int ID){
        try {
            String query = "UPDATE Student SET StudentStatus = " + true + " WHERE StudentID= "+ ID +"";
            statement = con.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void reserveItem(int resId, int studentId, int itemId){
        try {
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            String query = "INSERT INTO Reservations (ResID, StudentID,ItemID,ResTime) " +
                    "VALUES (" +"" + resId + ", "+ studentId + "," + itemId + ",'" + sqlDate +"');";

            statement = con.createStatement();
            statement.executeUpdate(query);
            modifyItem(itemId, false);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   public boolean reserveItemWithoutId(int studentId, int itemId){
        try {
            Item item = findItemById(itemId);
            if(item==null) return false;
           else if(item.isAvailable()){
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            String query = "INSERT INTO Reservations (StudentID,ItemID,ResTime) " +
                    "VALUES (" +""+ studentId + "," + itemId + ",'" + sqlDate +"');";

            statement = con.createStatement();
            statement.executeUpdate(query);
            modifyItem(itemId, false);
            }

            else return false;

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return false;
    }

    public void removeRes(int ID) {
        try {
            History history = getHistoryById(ID);
            modifyItem(history.getItemID(), true);

            String query = "DELETE FROM Reservations WHERE ResID = " + ID + "";
            statement = con.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<History> getAllHistory(){
        try {
            ArrayList<History> historyList = new ArrayList<>();
            String query = "SELECT * FROM Reservations;";
            statement = con.createStatement();
            ResultSet set = statement.executeQuery(query);

            while (set.next()) {
                History history = new History();
                history.setHistoryID(set.getInt("ResID"));
                history.setStudentID(set.getInt("StudentID"));
                history.setItemID(set.getInt("ItemID"));
                history.setResTime(set.getDate("ResTime"));
                historyList.add(history);
            }

            return historyList;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
    public ArrayList<History> getStudentHistory(int id){
        try {
            ArrayList<History> historyList = new ArrayList<>();
            String query = "SELECT * FROM Reservations WHERE StudentId = "+ id +";";
            statement = con.createStatement();
            ResultSet set = statement.executeQuery(query);

            while (set.next()) {
                History history = new History();
                history.setHistoryID(set.getInt("ResID"));
                history.setStudentID(set.getInt("StudentID"));
                history.setItemID(set.getInt("ItemID"));
                history.setResTime(set.getDate("ResTime"));
                historyList.add(history);
            }
            return historyList;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public History getHistoryById(int ID) {
        try {
            String query = "SELECT * FROM Reservations WHERE ResID = " + ID + ";";
            statement = con.createStatement();
            ResultSet set = statement.executeQuery(query);
            if(set.next()) {
                History history = new History();
                history.setHistoryID(set.getInt("ResID"));
                history.setItemID(set.getInt("ItemID"));
                history.setStudentID(set.getInt("StudentID"));
                history.setResTime(set.getDate("ResTime"));
                return history;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
