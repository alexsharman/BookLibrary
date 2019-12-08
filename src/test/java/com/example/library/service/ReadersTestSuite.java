package com.example.library.service;

import com.example.library.models.Reader;
import com.example.library.utils.DisplayFunctions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ReadersTestSuite {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Readers readers = new Readers(new DisplayFunctions(new Scanner(System.in)));

    @Before
    public void runBeforeTests() {
        Reader reader1 = new Reader("Alex", "Sharman");
        readers.addReader(reader1);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void addReader() {
        Reader testReader = new Reader("Test", "Tester");
        readers.addReader(testReader);
        Assert.assertNotNull(readers.getReaders().contains(testReader));
    }

    @Test
    public void testRemoveReader() {
        Reader reader2 = new Reader("Test2", "Tester2");
        readers.removeReader(reader2);
        Assert.assertTrue(!readers.getReaders().contains(reader2));
    }

    @Test
    public void testDisplayReaders() {
        readers.displayReaders();
        Assert.assertNotNull(outContent.toString());
    }

    @Test
    public void testFindReaderById(){
        Reader reader3 = new Reader("Test3", "Tester3");
        readers.addReader(reader3);
        Reader resReader = readers.findReaderById(reader3.getId());
        Assert.assertEquals(reader3, resReader);
    }


}
