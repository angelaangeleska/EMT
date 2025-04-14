package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.models.Author;
import mk.ukim.finki.emt.lab.models.Book;
import mk.ukim.finki.emt.lab.models.dto.BookDto;
import mk.ukim.finki.emt.lab.models.enumerations.BookStatus;
import mk.ukim.finki.emt.lab.repository.BookRepository;
import mk.ukim.finki.emt.lab.service.AuthorService;
import mk.ukim.finki.emt.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> addBook(BookDto book) {
        if (book.getTitle() != null &&
                !book.getTitle().isEmpty() &&
                book.getCategory() != null &&
                this.authorService.findById(book.getAuthorId()).isPresent() &&
                book.getAvailableCopies() != null) {
            Author author = this.authorService.findById(book.getAuthorId()).get();
            return Optional.of(this.bookRepository.save(new Book(book.getTitle(), book.getDescription(), book.getCategory(), author, book.getAvailableCopies())));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> update(Long id, BookDto book) {
        Optional<Book> b = this.findById(id);
        if (b.isPresent()) {
            if (book.getTitle() != null && !book.getTitle().isEmpty()) {
                b.get().setTitle(book.getTitle());
            }
            if (book.getDescription() != null && !book.getDescription().isEmpty()) {
                b.get().setDescription(book.getDescription());
            }
            if (book.getCategory() != null) {
                b.get().setCategory(book.getCategory());
            }
            if (book.getAvailableCopies() != null) {
                b.get().setAvailableCopies(book.getAvailableCopies());
            }
            if (book.getAuthorId() != null && authorService.findById(book.getAuthorId()).isPresent()) {
                b.get().setAuthor(authorService.findById(book.getAuthorId()).get());
            }
            if (book.getStatus() != null) {
                b.get().setStatus(book.getStatus());
            }
            return Optional.of(bookRepository.save(b.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> changeStatus(Long id) {
        Optional<Book> b = this.findById(id);
        if (b.isPresent()) {
            if (b.get().getStatus() != null) {
                if (b.get().getStatus() == BookStatus.AVAILABLE) {
                    b.get().setStatus(BookStatus.CHECKED_OUT);
                } else {
                    b.get().setStatus(BookStatus.AVAILABLE);
                }
            }
            return Optional.of(bookRepository.save(b.get()));
        }
        return Optional.empty();
    }

    @Override
    public List<Book> filterBooks(String title, String description, String author) {
        List<Author> authors = new ArrayList<>();
        if (author != null && !author.isEmpty()) {
            if (author.split("//s+").length > 1) {
                String name = author.split("//s+")[0];
                String surname = author.split("//s+")[1];
                authors = this.authorService.findByFirstNameOrLastName(name, surname);
                authors.addAll(authorService.findByFirstNameOrLastName(surname, name));
            } else {
                authors = this.authorService.findByFirstNameOrLastName(author, "");
                authors.addAll(authorService.findByFirstNameOrLastName("", author));
            }
        }
        return this.bookRepository.findByTitleOrDescriptionOrAuthorIn(title, description, authors);
    }
}
