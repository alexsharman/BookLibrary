package com.example.library.utils;

import com.example.library.models.Book;
import com.example.library.models.Reader;
import com.example.library.service.Books;
import com.example.library.service.Readers;
import dnl.utils.text.table.TextTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DisplayFunctions {

    public Book createBookInput(Scanner input, Books books) {
        System.out.println("Please input the books title:");
        String name = input.next();
        System.out.println("Please input the books author:");
        String author = input.next();
        System.out.println("Please input the books year:");
        int year = input.nextInt();
        Book book = new Book(name, author, year);
        books.bookIsAvailable(book);
        return book;
    }

    public Reader createReaderInput(Scanner input, Readers readers) {
        System.out.println("Please input you Name:");
        String name = input.next();
        System.out.println("Please input your Surname:");
        String surname = input.next();
        System.out.println("Please input the books year:");
        int year = input.nextInt();
        Reader newReader = new Reader(name, surname);
        if(readers.findReaderByNameAndSurname(name, surname) != null){
            return newReader;
        } else {
            System.out.println("Reader already exists!");
            return null;
        }
    }

    private String[] getBookHeaders() {
        String[] headers = new String[5];
        headers[0] = "id";
        headers[1] = "Title";
        headers[2] = "Author";
        headers[3] = "Year";
        headers[4] = "Availability";
        return headers;
    }

    private Object[][] convertBookToDataTable(List<Book> books) {
        Object[][] data = new Object[books.size()][5];
        for (int i = 0; i < books.size(); i++) {
            data[i][0] = books.get(i).getId();
            data[i][1] = books.get(i).getTitle();
            data[i][2] = books.get(i).getAuthor();
            data[i][3] = books.get(i).getYear();
            data[i][4] = books.get(i).getAvailability();
        }
        return data;
    }

    private void printTable(String[] columnNames, Object[][] data) {
        TextTable tt = new TextTable(columnNames, data);
        tt.printTable();
    }

    public void printBookTable(List<Book> books) {
        if (books.size() > 0) {
            printTable(getBookHeaders(), convertBookToDataTable(books));
        } else {
            System.out.println("No books available!");
        }
    }

    private String[] getStatHeaders() {
        String[] headers = new String[2];
        headers[0] = "available";
        headers[1] = "unavailable";
        return headers;
    }

    public void printStats(Books books) {
        TextTable tt = new TextTable(getStatHeaders(), convertStatsToDataTable(getStats(books)));
        tt.printTable();
    }

    private Object[][] convertStatsToDataTable(List<Integer> stats) {
        Object[][] data = new Object[1][2];
        data[0][0] = stats.get(0);
        data[0][1] = stats.get(1);
        return data;
    }

    public List<Integer> getStats(Books books) {
        Integer available = 0;
        Integer unAvailable = 0;
        for (Book book : books.getBooks()) {
            if (book.getAvailability() == true) {
                available++;
            } else {
                unAvailable++;
            }
        }
        List<Integer> res = new ArrayList<Integer>();
        res.add(available);
        res.add(unAvailable);
        return res;
    }

    private String[] getReaderHeaders() {
        String[] headers = new String[4];
        headers[0] = "id";
        headers[1] = "Name";
        headers[2] = "Surname";
        headers[3] = "Books held";
        return headers;
    }

    public void printReaders(Readers readers) {
        TextTable tt = new TextTable(getReaderHeaders(), convertReadersToDataTable(readers.getReaders()));
        tt.printTable();
    }

    private Object[][] convertReadersToDataTable(List<Reader> readers) {
        Object[][] data = new Object[readers.size()][4];
        for (int i = 0; i < readers.size(); i++) {
            data[i][0] = readers.get(i).getId();
            data[i][1] = readers.get(i).getName();
            data[i][2] = readers.get(i).getSurname();
            data[i][3] = readers.get(i).getBorrowedBooks().size();
        }
        return data;
    }

}
