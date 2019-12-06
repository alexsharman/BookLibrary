package com.example.library.service;

import com.example.library.models.Book;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class BooksTestSuite {

    private Books books;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void runBeforeTests() {
        System.setOut(new PrintStream(outContent));
        books = new Books();
        Book newBook = new Book("Freakanomics", "Levitt&Dubner", 2005);
        Book newBook2 = new Book("Freakanomics2", "Levitt&Dubner", 2012);
        Book newBook3 = new Book("Mission to Tashkent", "F.M. Bailey", 1946);
        books.addBook(newBook);
        books.addBook(newBook2);
        books.addBook(newBook3);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void addBook() {
        Book testBook = new Book("Test", "Test", 1999);
        books.addBook(testBook);
        Assert.assertNotNull(books.getBooks().contains(testBook));
    }

    @Test
    public void testRemoveBook() {
        Book testBook = new Book("Test", "Test", 1999);
        books.addBook(testBook);
        books.removeBook(testBook);
        Assert.assertTrue(!books.bookIsAvailable(testBook));
    }

    @Test
    public void testDisplayBooks() {
        books.showAllBooks();
        Assert.assertNotNull(outContent.toString());
    }

    @Test
    public void searchByTitle() {
        List<Book> foundBooks = books.searchByTitleName("Freakanomics");
        Assert.assertTrue(foundBooks.size() == 2);
    }

    @Test
    public void searchByAuthor() {
        List<Book> foundBooks = books.searchByAuthorName("Levitt");
        List<Book> foundBooks2 = books.searchByAuthorName("Bailey");

        Assert.assertTrue(foundBooks.size() == 2);
        Assert.assertTrue(foundBooks2.size() == 1);
    }

    @Test
    public void testSearchByYearAndAuthor() {
        List<Book> foundBooks = books.searchByAuthorAndYear("Levitt", 2005);
        Assert.assertTrue(foundBooks.size() == 1);
    }

}
