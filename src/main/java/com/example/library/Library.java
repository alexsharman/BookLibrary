package com.example.library;

import com.example.library.models.Book;

import java.util.List;

/**
 * This interface complies to the needs of the task.
 *
 * @author Alex Sharman
 */

public interface Library {

    public void addBook(String title, String author, int year);

    public void removeBookById(String BookId);

    public List<Book> displayAllBooks();

    public List<Book> searchByTitleName(String title);

    public List<Book> searchByAuthorName(String author);

    public List<Book> searchByTitleAuthorYear(String title, String author, int year);

    public void lendBookToReader(String bookId, String readerSurname);

    public void showBooksWithDetails(String bookId);
}
