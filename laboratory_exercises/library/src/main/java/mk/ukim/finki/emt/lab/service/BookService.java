package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.models.Book;
import mk.ukim.finki.emt.lab.models.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAll();
    Optional<Book> addBook(BookDto book);
    void deleteById(Long id);
    Optional<Book> findById(Long id);
    Optional<Book> update(Long id, BookDto book);
    Optional<Book> changeStatus(Long id);
    List<Book> filterBooks(String title, String description, String author);
}
