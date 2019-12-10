package com.example.library.services;

import com.example.library.models.Book;
import com.example.library.models.Reader;
import com.example.library.utils.DisplayFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.library.utils.StreamUtils.toSingleton;

/**
 * This class manages the readers within the library
 * Allows to add, remove and search.
 *
 * @author Alex Sharman
 */

public class Readers {

    private List<Reader> readers;
    private DisplayFunctions displayFunctions = new DisplayFunctions();


    public Readers() {
        this.readers = new ArrayList<>();
    }

    public Boolean addReader(Reader reader) {
        //as we have no constraints on the list of readers i am making sure we can only have one with the same surname
        if (!checkIfReaderExistsWithSameSurname(reader.getSurname())) {
            return readers.add(reader);
        } else {
            System.out.println("Reader already exists!");
            return false;
        }
    }

    public Boolean removeReader(Reader reader) {
        return readers.remove(reader);
    }

    public List<Reader> getReaders() {
        return readers;
    }

    public void displayReaders() {
        displayFunctions.printReaders(readers);
    }

    public Reader findReaderById(String id) {
        return readers.stream()
                .filter(c -> c.getId() == id)
                .collect(toSingleton());
    }

    public List<Reader> findReaderByNameAndSurname(String name, String surname) {
        try {
            return readers.stream()
                    .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase())
                            && c.getSurname().toLowerCase().contains(surname.toLowerCase()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Something went wrong, please try again: " + e.getMessage());
            return null;
        }
    }

    public Reader findReaderBySurname(String surname) {
        for (Reader reader : readers) {
            if (reader.getSurname().contains(surname)) {
                return reader;
            }
        }
        throw new NullPointerException("Reader not found!");
    }

    public Reader findReaderWithBook(Book book) {
        return readers.stream()
                .filter(c -> c.getBorrowedBookIds().stream().anyMatch(n -> n == book.getId()))
                .collect(toSingleton());
    }

    private Boolean checkIfReaderExistsWithSameSurname(String surname) {
        for (Reader reader : readers) {
            if (reader.getSurname().contains(surname)) {
                return true;
            }
        }
        return false;
    }

}
