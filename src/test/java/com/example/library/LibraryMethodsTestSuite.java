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

public class LibraryMethodsTestSuite {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private LibraryMethods library = new LibraryMethods();
    private DisplayFunctions displayFunctions = new DisplayFunctions();

    @Before
    public void runBeforeTests() {
        //given
        System.setOut(new PrintStream(outContent));
        library.addBook("Freakanomics", "Levitt&Dubner", 2005);
        library.addBook("Freakanomics2", "Levitt&Dubner", 2012);
        library.addBook("Mission to Tashkent", "F.M. Bailey", 1946);
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
        String title = "Freakanomics2";
        String name = "Alex";
        String surname = "Sharman";
        Book foundBook = library.searchByTitleName(title).get(0);
        Reader thisReader = library.findReaderByNameAndSurname(name, surname).get(0);
        //when
        library.lendBookToReader(foundBook.getId(), surname);
        //then
        Assert.assertTrue(library.borrowedBooks().contains(foundBook));
        Assert.assertTrue(thisReader.getBorrowedBookIds().contains(foundBook.getId()));
    }

    @Test
    public void testLendBookToReaderNotAvailable() {
        //given
        String title = "Freakanomics2";
        String name = "Alex";
        String surname = "Sharman";
        Book foundBook = library.searchByTitleName(title).get(0);
        Reader thisReader = library.findReaderByNameAndSurname(name, surname).get(0);
        library.lendBookToReader(foundBook.getId(), surname);
        //when
        Reader reader2 = new Reader("Test", "Teaser");
        library.addReader(reader2);
        library.lendBookToReader(foundBook.getId(), reader2.getSurname());
        //then
        Assert.assertTrue(foundBook.getReader() != thisReader.toString());
    }

    @Test
    public void testReturnBook() {
        //given
        String title = "Mission to Tashkent";
        String author = "F.M. Bailey";
        int year = 1946;
        //when
        Reader thisReader = library.findReaderByNameAndSurname("Alex", "Sharman").get(0);
        String bookId = library.findBook(title, author, year).get(0).getId();
        library.lendBookToReader(bookId, thisReader.getSurname());
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
