package com.example.library;

import com.example.library.models.Book;
import com.example.library.models.Reader;
import com.example.library.service.Books;
import com.example.library.service.Readers;
import com.example.library.utils.DisplayFunctions;

import java.util.List;

public class Library  {

    private DisplayFunctions displayFunctions = new DisplayFunctions();
    private Books books = new Books();
    private Readers readers = new Readers();

    public static void main(String[] args) {
        System.out.println("********************    Welcome to the Library!   ********************");

    }

    public void addBook(Book book) {
        books.addBook(book);
    }

    public void removeBookById(String id) {
        books.removeBook(books.findBookById(id));
    }

    public void displayAllBooks() {
        displayFunctions.printStats(books);
        books.showAllBooks();
    }

    public void booksToLend() {
        books.showAllBooksByAvailability(true);
    }

    public List<Book> borrowedBooks() {
        return books.showAllBooksByAvailability(false);
    }

    public void lendBookToReader(Book book, Reader reader) {
        books.borrowBook(book, reader);
    }

    public void returnBook(String bookId) {
        Book bookToReturn = books.findBookById(bookId);
        Reader readerWithBook = readers.findReaderWithBook(bookToReturn);
        bookToReturn.setAvailability(true);
        readerWithBook.removeBook(bookToReturn);
    }

    public void addReader(Reader reader){
        readers.addReader(reader);
    }

    public List<Book> searchByTitleName(String title){
        return books.searchByTitleName(title);
    }

    public Reader findReaderByNameAndSurname(String name, String surname){
        return readers.findReaderByNameAndSurname(name, surname);
    }

    public Book findBook(String title, String author, int year){
        List<Book> foundBooks = books.searchByTitleName(title);
        if(foundBooks.size() == 1 ){
            return foundBooks.get(0);
        } else {
            System.out.println("Duplicate books found in library, please search by Id");
            return null;
        }
    }

}
