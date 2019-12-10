package com.example.library;

import com.example.library.models.Book;
import com.example.library.utils.DisplayFunctions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

// This testSuite does print the output of the requested library methods.
public class LibraryTestSuite {

    private Library library = new LibraryMethods();
    private DisplayFunctions displayFunctions = new DisplayFunctions();

    @Before
    public void runBeforeTests() {
        //given
        library.addBook("Freakanomics", "Levitt&Dubner", 2005);
        library.addBook("Freakanomics2", "Levitt&Dubner", 2012);
        library.addBook("Mission to Tashkent", "F.M. Bailey", 1946);
        library.addReader("Alex", "Sharman");
    }

    @Test
    public void testDisplayAllBooks(){
        library.displayAllBooks();
    }

    @Test
    public void testSearchByTitleName(){
        Assert.assertNotNull(library.searchByTitleName("Freakanomics2"));
    }

    @Test
    public void testRemoveBookById(){
        String bookId = library.searchByTitleName("Mission to Tashkent").get(0).getId();
        library.removeBookById(bookId);
        Assert.assertNotEquals(bookId, library.searchByTitleName("Mission to Tashkent").get(0).getId());
    }


    @Test
    public void testLendBookToReader() {
        //given
        String title = "Freakanomics2";
        String surname = "Sharman";
        Book foundBook = library.searchByTitleName(title).get(0);
        //when
        library.lendBookToReader(foundBook.getId(), surname);
        //then
        Assert.assertFalse(foundBook.getAvailability());
    }


    @Test
    public void testPrintAllBooks() {
        //given
        //when
        library.displayAllBooks();
        //then
        Assert.assertNotNull("");
    }

    @Test
    public void testBookToLend() {
        //given
        //when
//        displayFunctions.printBookTable("");
        //then
//        Assert.assertNotNull(outContent.toString());
    }
}
