package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> listAll();
    Optional<Author> addAuthor(Author author);
    Optional<Author> findById(Long id);
    List<Author> findByFirstNameOrLastName(String firstName, String lastName);
}
