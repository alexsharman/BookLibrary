package com.example.library;

import com.example.library.models.Book;
import com.example.library.models.Reader;
import com.example.library.services.Books;
import com.example.library.services.Readers;
import com.example.library.utils.DisplayFunctions;

import java.util.ArrayList;
import java.util.List;

public class LibraryMethods implements Library {

    private static Books books = new Books();
    private Readers readers;
    private DisplayFunctions displayFunctions;

    public LibraryMethods() {
        this.displayFunctions = new DisplayFunctions();
        this.readers = new Readers();
    }

    public void addBook(String title, String author, int year) {
        Book book = new Book(title, author, year);
        if (!books.bookIsAvailable(book)) {
            books.addBook(book);
        } else {
            System.out.println("Book already exists!");
        }
    }

    public void removeBookById(String id) {
        books.removeBook(books.findBookById(id));
    }

    public List<Book> displayAllBooks() {
        displayFunctions.printStats(books);
        List<Book> foundBooks = books.showAllBooks();
        displayFunctions.printBookTable(foundBooks);
        return foundBooks;
    }

    public void displayAllReaders() {
        readers.displayReaders();
    }

    public List<Book> findBookByTitle(String title) {
        List<Book> foundBook = books.searchByTitleName(title);
        displayFunctions.printBookTable(new ArrayList<>(foundBook));
        return foundBook;
    }

    public List<Book> booksToLend() {
        return books.showAllBooksByAvailability(true);
    }

    public List<Book> borrowedBooks() {
        return books.showAllBooksByAvailability(false);
    }

    public void lendBookToReader(String bookId, String readerSurname) {
        Book book = books.findBookById(bookId);
        Reader reader = readers.findReaderBySurname(readerSurname);
        books.borrowBook(book, reader);
    }

    public void showAllBooksWithDetails() {

    }

    public void returnBook(String bookId) {
        Book bookToReturn = books.findBookById(bookId);
        Reader readerWithBook = readers.findReaderWithBook(bookToReturn);
        bookToReturn.setAvailability(true);
        readerWithBook.removeBook(bookToReturn);
    }

    public void addReader(Reader reader) {
        readers.addReader(reader);
    }

    public List<Book> searchByTitleName(String title) {
        List<Book> foundBooks = books.searchByTitleName(title);
        displayFunctions.printBookTable(new ArrayList<>(foundBooks));
        return foundBooks;
    }

    public List<Book> searchByAuthorName(String author) {
        List<Book> foundBooks = books.searchByAuthorName(author);
        displayFunctions.printBookTable(new ArrayList<>(foundBooks));
        return foundBooks;
    }

    public List<Book> searchByTitleAuthorYear(String title, String author, int year) {
        List<Book> foundBooks = books.searchByTitleAndAuthorAndYear(title, author, year);
        displayFunctions.printBookTable(new ArrayList<>(foundBooks));
        return foundBooks;
    }

    public List<Reader> findReaderByNameAndSurname(String name, String surname) {
        List<Reader> foundReader = readers.findReaderByNameAndSurname(name, surname);
        displayFunctions.printReaders(foundReader);
        return foundReader;
    }

    public List<Book> findBook(String title, String author, int year) {
        List<Book> foundBook = books.searchByTitleAndAuthorAndYear(title, author, year);
        displayFunctions.printBookTable(new ArrayList<>(foundBook));
        return foundBook;
    }

    public List<Book> findBookByAuthor(String author) {
        List<Book> foundBook = books.searchByAuthorName(author);
        displayFunctions.printBookTable(new ArrayList<>(foundBook));
        return foundBook;
    }

}
