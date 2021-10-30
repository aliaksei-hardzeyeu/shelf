package by.hardzeyeu.libraryV2.services;

import by.hardzeyeu.libraryV2.models.Book;

import java.util.List;

public interface BookService {

    Book getBook(int bookId);

    List<Book> getListOfBooks();

    void addBook(String title, String publisher, int page_count, String isbn, String des, String publDate,
                 String authors, String genres, int amount);

    void updateBook(String title, String publisher, int page_count, String isbn, String des, String publDate,
                    String authors, String genres, int amount, int book_id);

    void removeBook(int book_id);



}
