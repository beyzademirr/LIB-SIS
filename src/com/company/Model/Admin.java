package com.company.Model;

public class Admin {

    private int adminId;
    private String adminMail;
    private String adminPassword;

    public Admin(int adminId, String adminMail, String adminPassword) {
        this.adminId = adminId;
        this.adminMail = adminMail;
        this.adminPassword = adminPassword;
    }

    public Admin(String adminMail, String adminPassword) {

        this.adminMail = adminMail;
        this.adminPassword = adminPassword;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminMail() {
        return adminMail;
    }

    public void setAdminMail(String adminMail) {
        this.adminMail = adminMail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}
