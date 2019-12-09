package com.example.library.service;

import com.example.library.models.Book;
import com.example.library.models.Reader;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.library.utils.StreamUtils.toSingleton;

public class Books {

    List<Book> books = new ArrayList<>();
    private int counter = 0;
    private Map<Integer, Book> booksById;
    private Map<String, Set<String>> booksByAuthor;
    private Map<Integer, Set<Integer>> booksByYear;
    private Map<String, Set<String>> booksByTitle;

    public Book addBook(Book b) {
        if (bookIsAvailable(b)) {
            counter = counter++;
            booksById.put(b.getId(), b);
            booksByAuthor.putIfAbsent(b.getAuthor(), new HashSet<>());
            booksByAuthor.get(b.getAuthor()).add(b.getAuthor());
            booksByYear.putIfAbsent(b.getYear(), new HashSet<>());
            booksByYear.get(b.getYear()).add(b.getYear());
            booksByTitle.putIfAbsent(b.getTitle(), new HashSet<>());
            booksByTitle.get(b.getTitle()).add(b.getTitle());
        }
        return b;
    }

    public Boolean removeBook(Book b) {
        if (bookIsAvailable(b)) {
            booksById.remove(b.getId());
            booksByAuthor.get(b.getAuthor()).remove(b.getId());
            booksByYear.get(b.getYear()).remove(b.getYear());
            booksByTitle.get(b.getTitle()).remove(b.getTitle());
            return true;
        } else {
            System.out.println("Book is not available.");
            return false;
        }
    }

    private List<Book> getBoooksById(List<Integer> ids) {
        List<Book> foundBooks = new ArrayList<>();
        try {
            for (Integer id : ids) {
                foundBooks.add(books.get(id));
            }
            return foundBooks;
        } catch (Exception e) {
            System.out.println("Book not found with id" + e.getMessage());
            return null;
        }
    }

    public List<Book> findBookById(Set<Integer> id) {
        Set<Book> byId = booksById.getOrDefault(id, Book);
        Set<Book> foundBooks = new HashSet(byId);
        foundBooks.retainAll(byId);
        return getBoooksById(foundBooks.toArray());
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
//        return books.stream()
//                .filter(c -> c.getAuthor().contains(authorName) && c.getTitle().contains(title) && c.getYear() == year)
//                .collect(Collectors.toList());
        Set<String> byAuthor = booksByAuthor.getOrDefault(authorName, new HashSet<String>()); // to sprawi, że nie dostaniemy nulla i nie wywalimy się z wyjątkiem
        Set<String> byTitle = booksByTitle.getOrDefault(title, new HashSet<String>());
        Set<Integer> byYear = booksByYear.getOrDefault(year, new HashSet<Integer>());
        Set<String> foundBooks = new HashSet(byAuthor); // tu ważne - potrzebujemy kopii zbioru id książek tego autora inaczej zbiór w booksByAuthor by się zepsuł
        foundBooks.retainAll(byTitle); // to wylicza część wspólną zbiorów
        foundBooks.retainAll(byYear);
        return getBooksById(foundBooks);
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

    public List<Book> getBooks() {
        return books;
    }

}
