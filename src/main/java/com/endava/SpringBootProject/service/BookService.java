package com.endava.SpringBootProject.service;

import com.endava.SpringBootProject.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(Long id);

    Book getBookByName(String name);

    Book saveBook(Book book);

    void updateBook(Long id, Book book);

    void deleteBook(Long id);
}
