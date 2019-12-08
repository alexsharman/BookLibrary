package com.example.library;

import com.example.library.service.Books;
import com.example.library.service.Readers;
import com.example.library.utils.DisplayFunctions;

import java.util.Scanner;

public class LibraryApp {

    private static Scanner scanner = new Scanner(System.in);
    private static DisplayFunctions displayFunctions = new DisplayFunctions(scanner);
    private static Books books = new Books();
    private static Readers readers = new Readers(displayFunctions);
    private static LibraryMethods libraryMethods = new LibraryMethods(scanner);

    public static void main(String[] args) {
        System.out.println("********************    Welcome to the Library!   ********************");
        int choice;
        int searchChoice;
        do {
            displayFunctions.dispMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    libraryMethods.addBook(displayFunctions.createBookInput( books));
                    break;
                case 2:
                    System.out.println("Enter 1 to Search by Title");
                    System.out.println("Enter 2 to Search by Author Name(Full Name).");
                    searchChoice = displayFunctions.getSearchChoiceOf2();
                    switch (searchChoice) {
                        case 1:
                            System.out.println("Enter book Title:");
                            libraryMethods.findBookByTitle(scanner.next());
                            break;
                        case 2:
                            System.out.println("Enter book Title:");
                            libraryMethods.findBookByAuthor(scanner.next());
                    }
                    break;
                case 3:
                    libraryMethods.displayAllBooks();
                    break;
                case 4:
                    libraryMethods.addReader(displayFunctions.createReaderInput( readers));
                    break;
                case 5:
                    libraryMethods.displayAllReaders();
                    break;
                case 6:
                    libraryMethods.lendBookToReader(
                            displayFunctions.getBookByTitleInput(libraryMethods),
                            displayFunctions.getReaderByNameSurname(libraryMethods)
                    );
                    break;
                case 7:
                    libraryMethods.returnBook(displayFunctions.provideBookId());
                    break;
                default:
                    System.out.println("CHOICE SHOULD BE BETWEEN 0 TO 7!");
            }
        }
        while (choice != 0);
    }
}
