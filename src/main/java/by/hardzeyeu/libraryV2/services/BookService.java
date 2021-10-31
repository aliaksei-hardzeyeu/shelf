package by.hardzeyeu.libraryV2.services;

import by.hardzeyeu.libraryV2.models.Book;

import java.util.List;

public interface BookService {

    Book getBook(int id);

    List<Book> getListOfBooks();

    void addBook(String title, String publisher, String author);

    void updateBook(String title, String publisher, String author, int id);

    void removeBook(int id);



}
