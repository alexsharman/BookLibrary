package com.example.library;

import com.example.library.models.Book;
import com.example.library.models.Reader;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LibraryTestSuite {


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    Library library = new Library();

    @Before
    public void runBeforeTests() {
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
        Book foundBook = library.searchByTitleName("Freakanomics2").get(0);
        Reader thisReader = library.findReaderByNameAndSurname("Alex", "Sharman");
        library.lendBookToReader(foundBook, thisReader);
        Assert.assertTrue(library.borrowedBooks().contains(foundBook));
        Assert.assertTrue(thisReader.getBorrowedBooks().contains(foundBook));
    }

    @Test
    public void testLendBookToReaderNotAvailable() {
        Book foundBook = library.searchByTitleName("Freakanomics2").get(0);
        Reader thisReader = library.findReaderByNameAndSurname("Alex", "Sharman");
        library.lendBookToReader(foundBook, thisReader);
        Reader reader2 = new Reader("Test", "Teaser");
        library.addReader(reader2);
        library.lendBookToReader(foundBook, reader2);
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
        Reader thisReader = library.findReaderByNameAndSurname("Alex", "Sharman");
        library.lendBookToReader(foundBook, thisReader);
        String bookId = library.findBook(title, author, year).getId();
        library.returnBook(bookId);
        //then
        Assert.assertTrue(library.findBook("Mission to Tashkent", "F.M. Bailey", 1946).getAvailability());
    }

    @Test
    public void testPrintAllBooks(){
        library.displayAllBooks();
        Assert.assertNotNull(outContent.toString());
    }


}
