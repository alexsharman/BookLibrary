package com.example.library;

import com.example.library.models.Book;
import com.example.library.models.Reader;
import com.example.library.utils.DisplayFunctions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class LibraryMethodsTestSuite {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private LibraryMethods library = new LibraryMethods();
    private DisplayFunctions displayFunctions = new DisplayFunctions();

    @Before
    public void runBeforeTests() {
        //given
        System.setOut(new PrintStream(outContent));
        Book newBook = new Book("Freakanomics", "Levitt&Dubner", 2005);
        Book newBook2 = new Book("Freakanomics2", "Levitt&Dubner", 2012);
        Book newBook3 = new Book("Mission to Tashkent", "F.M. Bailey", 1946);
        library.addBook(newBook);
        library.addBook(newBook2);
        library.addBook(newBook3);
        Reader reader1 = new Reader("Alex", "Sharman");
        library.addReader(reader1);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testLendBookToReader() {
        //given
        Book foundBook = library.searchByTitleName("Freakanomics2").get(0);
        Reader thisReader = library.findReaderByNameAndSurname("Alex", "Sharman").get(0);
        //when
        library.lendBookToReader(foundBook, thisReader);
        //then
        Assert.assertTrue(library.borrowedBooks().contains(foundBook));
        Assert.assertTrue(thisReader.getBorrowedBooks().contains(foundBook));
    }

    @Test
    public void testLendBookToReaderNotAvailable() {
        //given
        String title = "Freakanomics2";
        Book foundBook = library.searchByTitleName(title).get(0);
        Reader thisReader = library.findReaderByNameAndSurname("Alex", "Sharman").get(0);
        library.lendBookToReader(foundBook, thisReader);
        //when
        Reader reader2 = new Reader("Test", "Teaser");
        library.addReader(reader2);
        library.lendBookToReader(foundBook, reader2);
        //then
        Assert.assertTrue(foundBook.getReader() == thisReader.getId());
    }

    @Test
    public void testReturnBook() {
        //given
        String title = "Mission to Tashkent";
        String author = "F.M. Bailey";
        int year = 1946;
        //when
        Book foundBook = library.searchByTitleName(title).get(0);
        Reader thisReader = library.findReaderByNameAndSurname("Alex", "Sharman").get(0);
        library.lendBookToReader(foundBook, thisReader);
        String bookId = library.findBook(title, author, year).get(0).getId();
        library.returnBook(bookId);
        //then
        Assert.assertTrue(library.findBook("Mission to Tashkent", "F.M. Bailey", 1946).get(0).getAvailability());
    }

    @Test
    public void testPrintAllBooks() {
        //given
        //when
        library.displayAllBooks();
        //then
        Assert.assertNotNull(outContent.toString());
    }

    @Test
    public void testBookToLend() {
        //given
        //when
        displayFunctions.printBookTable(library.booksToLend());
        //then
        Assert.assertNotNull(outContent.toString());
    }

}
