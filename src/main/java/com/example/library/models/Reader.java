package com.example.library.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Reader {

    private String id;
    private String Name;
    private String Surname;
    private List<String> borrowedBooks = new ArrayList();

    public Reader(String name, String surname) {
        this.id = UUID.randomUUID().toString();
        Name = name;
        Surname = surname;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public List<String> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void addBook(String bookId) {
        this.borrowedBooks.add(bookId);
    }

    public void removeBook(Book book) {
        this.borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id='" + id + '\'' +
                ", Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }

}
