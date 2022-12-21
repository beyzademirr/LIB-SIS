package com.company.Model;

import java.util.Date;

public class History {
    private int historyID;
    private int StudentID;
    private int itemID;
    private Date resTime;

    public History(int historyID, int studentID, int itemID, Date resTime) {
        this.historyID = historyID;
        StudentID = studentID;
        this.itemID = itemID;
        this.resTime = resTime;
    }

    public History() {
    }

    public int getHistoryID() {
        return historyID;
    }

    public void setHistoryID(int historyID) {
        this.historyID = historyID;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int studentID) {
        StudentID = studentID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public Date getResTime() {
        return resTime;
    }

    public void setResTime(Date resTime) {
        this.resTime = resTime;
    }
}
