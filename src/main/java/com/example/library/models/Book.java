package com.example.library.models;

import java.util.UUID;

public class Book {
    private final String id;
    private final String title;
    private final String author;
    private final int year;
    private Boolean availability;
    private String readerId;

    public Book(String title, String author, int year) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.year = year;
        this.availability = true;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        if(availability){
            this.setReader(null);
        }
        this.availability = availability;
    }

    public int getYear() {
        return year;
    }

    public String getReader() {
        return readerId;
    }

    public void setReader(String readerId) {
        this.readerId = readerId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", availability=" + availability +
                ", reader=" + readerId +
                '}';
    }
}
