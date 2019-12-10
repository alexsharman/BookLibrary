package com.example.library.service;

import com.example.library.models.Book;
import com.example.library.models.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.library.utils.StreamUtils.toSingleton;

public class Readers {

    private List<Reader> readers;

    public Readers() {
        this.readers = new ArrayList<>();
    }

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

    public Reader findReaderById(String id) {
        return readers.stream()
                .filter(c -> c.getId() == id)
                .collect(toSingleton());
    }

    public Reader findReaderBySurname(String surname) {
        return readers.stream()
                .filter(c -> c.getSurname() == surname)
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

    public Reader findReaderWithBook(Book book) {
        return readers.stream()
                .filter(c -> c.getBorrowedBooks().stream().anyMatch(n -> n.equals(book.getId())))
                .collect(toSingleton());
    }

}
