package com.example.library.utils;

import com.example.library.models.Book;
import com.example.library.models.Reader;
import com.example.library.service.Books;
import dnl.utils.text.table.TextTable;

import java.util.ArrayList;
import java.util.List;

public class DisplayFunctions {


//    public void dispMenu() {
//        System.out.println("--------------------------------------------------------");
//        System.out.println("Enter 0 to Exit Application.");
//        System.out.println("Enter 1 to Add new Book.");
//        System.out.println("Enter 2 to Search a Book.");
//        System.out.println("Enter 3 to Show All Books.");
//        System.out.println();
//        System.out.println("Enter 4 to Register Reader.");
//        System.out.println("Enter 5 to Show All Readers.");
//        System.out.println();
//        System.out.println("Enter 6 to Check Out Book. ");
//        System.out.println("Enter 7 to Check In Book");
//        System.out.println("--------------------------------------------------------");
//        System.out.println("Please choose an option:");
//    }

//    public Book createBookInput( Books books) {
//        System.out.println("Please input the books title:");
//        String name = scanner.next();
//        System.out.println("Please input the books author:");
//        String author = scanner.next();
//        System.out.println("Please input the books year:");
//        int year = scannerGetInt();
//        Book book = new Book(name, author, year);
//        books.bookIsAvailable(book);
//        return book;
//    }
//
//    public Reader createReaderInput( Readers readers) {
//        System.out.println("Please input you Name:");
//        String name = scanner.next();
//        System.out.println("Please input your Surname:");
//        String surname = scanner.next();
//        Reader newReader = new Reader(name, surname);
//        if (readers.findReaderByNameAndSurname(name, surname) != null) {
//            return newReader;
//        } else {
//            System.out.println("Reader already exists!");
//            return null;
//        }
//    }
//
//    public String provideBookId() {
//        System.out.println("Please provide book id to return:");
//        return scanner.next();
//    }
//
//    public Book getBookByTitleInput(LibraryMethods libraryMethods) {
//        System.out.println("Choosing a book.");
//        System.out.println("Input book title:");
//        String title = scanner.next();
//        List<Book> foundBooks = libraryMethods.findBookByTitle(title);
//        if (foundBooks.size() > 1) {
//            System.out.println("Select book by sequence: ");
//            int bookSeq = scannerGetInt() - 1;
//            return foundBooks.get(bookSeq);
//        } else if (foundBooks.size() == 0) {
//            System.out.println("No Books found!");
//            return null;
//        } else {
//            return foundBooks.get(0);
//        }
//    }
//
//    public Reader getReaderByNameSurname(LibraryMethods libraryMethods) {
//        System.out.println("Input readers name:");
//        String name = scanner.next();
//        System.out.println("Input readers surname:");
//        String surname = scanner.next();
//        List<Reader> foundReaders = libraryMethods.findReaderByNameAndSurname(name, surname);
//        if (foundReaders.size() > 1) {
//            System.out.println("Select Reader by sequence: ");
//            int bookSeq = scannerGetInt() - 1;
//            return foundReaders.get(bookSeq);
//        } else if (foundReaders.size() == 0) {
//            System.out.printf("No Reader Found!");
//            return null;
//        } else {
//            return foundReaders.get(0);
//        }
//    }

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
        List<Integer> res = new ArrayList<>();
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

    public void printReaders(List<Reader> readers) {
        TextTable tt = new TextTable(getReaderHeaders(), convertReadersToDataTable(readers));
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

//    public int getSearchChoiceOf2() {
//        int numTries = 0;
//        while (true) {
//            try {
//                return scannerGetInt();
//            } catch (Exception e) {
//                if (--numTries == 0) throw e;
//            }
//        }
//    }

//    private int scannerGetInt() {
//        boolean inputOk = false;
//        int res = 0;
//        while (!inputOk) {
//            try {
//                res = scanner.nextInt();
//                inputOk = true;
//            } catch (InputMismatchException e) {
//                System.err.println("That's not a number!");
//                scanner.nextLine();   // This discards input
//                scanner.reset();
//            }
//        }
//        return res;
//    }

}
