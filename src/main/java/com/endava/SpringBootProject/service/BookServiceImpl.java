package com.endava.SpringBootProject.service;

import com.endava.SpringBootProject.exception.BookAlreadyExistsException;
import com.endava.SpringBootProject.exception.BookNotFoundException;
import com.endava.SpringBootProject.model.Book;
import com.endava.SpringBootProject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            throw new BookNotFoundException("No such book found");
        }
        return book.get();
    }

    @Override
    public Book getBookByName(String name) {
        Optional<Book> book = bookRepository.findBookByName(name);
        if (!book.isPresent()) {
            throw new BookNotFoundException("No such book found");
        }
        return book.get();
    }

    @Override
    public Book saveBook(Book book) {
        Optional<Book> existingBook = bookRepository.findBookByName(book.getName());
        if (existingBook.isPresent()) {
            throw new BookAlreadyExistsException("Such book already exists");
        }
        return bookRepository.save(book);
    }

    @Override
    public void updateBook(Long id, Book book) {
        Optional<Book> savedBook = bookRepository.findById(id);
        if (!savedBook.isPresent()) {
            throw new BookNotFoundException("No such book found");
        }
        savedBook.get().setName(book.getName());
        savedBook.get().setGenre(book.getGenre());
        bookRepository.save(savedBook.get());
    }

    @Override
    public void deleteBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            throw new BookNotFoundException("No such book found");
        }
        bookRepository.deleteById(id);
    }
}
