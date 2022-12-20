package com.company.Model;

import java.util.ArrayList;

public class Student {

    private int studentId;
    private String studentMail;
    private String studentPassword;
    private ArrayList<Item> items;
    private boolean isBlocked;

    public Student(int studentId, String studentMail, String studentPassword, ArrayList<Item> items, boolean isBlocked) {
        this.studentId = studentId;
        this.studentMail = studentMail;
        this.studentPassword = studentPassword;
        this.items = items;
        this.isBlocked = isBlocked;
    }
    public Student(String studentMail, String studentPassword, ArrayList<Item> items, boolean isBlocked) {
        this.studentMail = studentMail;
        this.studentPassword = studentPassword;
        this.items = items;
        this.isBlocked = isBlocked;
    }
    
        public Student(String studentName, String studentSurname, String studentEmail, String studentPassword, boolean isBlocked) {
        this.studentMail = studentEmail;
        this.studentPassword = studentPassword;
        this.isBlocked = isBlocked;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentMail() {
        return studentMail;
    }

    public void setStudentMail(String studentMail) {
        this.studentMail = studentMail;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
