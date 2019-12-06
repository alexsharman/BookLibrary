package com.example.library.service;

import com.example.library.utils.DisplayFunctions;
import com.example.library.models.Book;
import com.example.library.models.Reader;

import java.util.ArrayList;
import java.util.List;


public class Books {

    private List<Book> books = new ArrayList<Book>();
    private DisplayFunctions displayFunctions = new DisplayFunctions();

    public Boolean addBook(Book b) {
        return this.books.add(b);
    }

    public Book findBookById(String id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public List<Book> searchByAuthorName(String authorName) {
        List<Book> booksByAuthor = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getAuthor().contains(authorName)) {
                booksByAuthor.add(book);
            }
        }
        displayFunctions.printBookTable(booksByAuthor);
        return booksByAuthor;
    }

    public List<Book> searchByAuthorAndYear(String authorName, int year) {
        List<Book> booksByAuthor = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getAuthor().contains(authorName) && book.getYear() == year) {
                booksByAuthor.add(book);
            }
        }
        displayFunctions.printBookTable(booksByAuthor);
        return booksByAuthor;
    }

    public List<Book> searchByTitleAndAuthorAndYear(String title, String authorName, int year) {
        List<Book> booksByAuthor = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getAuthor().contains(authorName) && book.getYear() == year && book.getTitle() == title) {
                booksByAuthor.add(book);
            }
        }
        displayFunctions.printBookTable(booksByAuthor);
        return booksByAuthor;
    }

    public List<Book> searchByTitleName(String title) {
        List<Book> booksByTitle = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getTitle().contains(title)) {
                booksByTitle.add(book);
            }
        }
        displayFunctions.printBookTable(booksByTitle);
        return booksByTitle;
    }

    public void showAllBooks() {
        displayFunctions.printBookTable(books);
    }

    public List<Book> showAllBooksByAvailability(Boolean available) {
        List<Book> availableBooks = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getAvailability() == available) {
                availableBooks.add(book);
            }
        }
        displayFunctions.printBookTable(availableBooks);
        return availableBooks;
    }

    public void borrowBook(Book book, Reader reader) {
        if (book.getAvailability()) {
            books.get(books.indexOf(book)).setAvailability(false);
            books.get(books.indexOf(book)).setReader(reader.getId());
            reader.addBook(book);
        } else {
            System.out.println("Book is not available!");
        }
    }

    public Boolean bookIsAvailable(Book book) {
        if (books.contains(book) && book.getAvailability()) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean removeBook(Book book) {
        if (bookIsAvailable(book)) {
            return books.remove(book);
        } else {
            System.out.println("Book is not available.");
            return false;
        }
    }

    public List<Book> getBooks() {
        return books;
    }


}
