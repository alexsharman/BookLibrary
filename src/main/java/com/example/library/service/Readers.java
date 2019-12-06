package com.example.library.service;

import com.example.library.models.Book;
import com.example.library.models.Reader;
import com.example.library.utils.DisplayFunctions;

import java.util.ArrayList;
import java.util.List;

public class Readers {

    private List<Reader> readers = new ArrayList<Reader>();
    private DisplayFunctions displayFunctions = new DisplayFunctions();

    public Reader addReader(Reader reader) {
        readers.add(reader);
        return reader;
    }

    public Boolean removeReader(Reader reader) {
        return readers.remove(reader);
    }

    public List<Reader> getReaders() {
        return readers;
    }

    public void displayReaders() {
        displayFunctions.printReaders(this);
    }

    public Reader findReaderById(String id) {
        for (Reader reader : readers) {
            if (reader.getId() == id) {
                return reader;
            }
        }
        System.out.println("Reader not found!");
        return null;
    }

    public Reader findReaderByNameAndSurname(String name, String surname) {
        for (Reader reader : readers) {
            if (reader.getName() == name && reader.getSurname() == surname) {
                return reader;
            }
        }
        System.out.println("Reader not found!");
        return null;
    }

    public Reader findReaderWithBook(Book book) {
        for (Reader reader : readers) {
            if(reader.getBorrowedBooks().contains(book)){
                return reader;
            }
        }
        System.out.println("Book not found in any Readers borrowed books!");
    return null;
    }

}
