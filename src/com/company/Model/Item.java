package com.company.Model;

public class Item {
    private int itemId;
    private String location;
    private String author;
    private int year;
    private int pages;
    private boolean isAvailable;

    public Item() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


    public Item(String location, String author, int year, int pages, boolean isAvailable) {
        this.location = location;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.isAvailable = isAvailable;
    }

    public Item(int ItemID, String ItemName, String Author, boolean isAvailable, int year, int pages, String location) {
        this.itemId = itemId;
        this.location = location;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.isAvailable = isAvailable;
        this.name = name;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
