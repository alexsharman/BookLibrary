package com.example.library.services;

import com.example.library.models.Book;
import com.example.library.models.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.library.utils.StreamUtils.toSingleton;

public class Books {

    private List<Book> books = new ArrayList<>();

    public Boolean addBook(Book b) {
        return this.books.add(b);

    }

    public Book findBookById(String id) {
        return books.stream()
                .filter(c -> c.getId().toLowerCase().contains(id))
                .collect(toSingleton());
    }

    public List<Book> searchByAuthorName(String authorName) {
        return books.stream()
                .filter(c -> c.getAuthor().contains(authorName))
                .collect(Collectors.toList());
    }

    public Book findBookByAuthorName(String authorName) {
        return books.stream()
                .filter(c -> c.getAuthor().contains(authorName))
                .collect(toSingleton());
    }

    public List<Book> searchByAuthorAndYear(String authorName, int year) {
        return books.stream()
                .filter(c -> c.getAuthor().contains(authorName) && c.getYear() == year)
                .collect(Collectors.toList());
    }

    public List<Book> searchByTitleAndAuthorAndYear(String title, String authorName, int year) {
        return books.stream()
                .filter(c -> c.getAuthor().contains(authorName) && c.getTitle().contains(title) && c.getYear() == year)
                .collect(Collectors.toList());
    }

    public Book findBookByTitleAndAuthorAndYear(String title, String authorName, int year) {
        return books.stream()
                .filter(c -> c.getAuthor().contains(authorName) && c.getTitle().contains(title) && c.getYear() == year)
                .collect(toSingleton());
    }

    public List<Book> searchByTitleName(String title) {
        return books.stream()
                .filter(c -> c.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> showAllBooks() {
        return books;
    }

    public List<Book> showAllBooksByAvailability(Boolean available) {
        return books.stream()
                .filter(c -> c.getAvailability() == available)
                .collect(Collectors.toList());
    }

    public void borrowBook(Book book, Reader reader) {
        //Checks if book is available to borrow
        if (book.getAvailability()) {
            book.setAvailability(false);
            book.setReader(reader.getId());
            reader.addBook(book.getId());
        } else {
            System.out.println("Book is not available!");
        }
    }

    public Boolean bookIsAvailable(Book book) {
        return (books.contains(book) && book.getAvailability());
    }

    public Boolean removeBook(Book book) {
        //checking to make sure book is available
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
