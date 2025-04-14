package mk.ukim.finki.emt.lab.repository;

import mk.ukim.finki.emt.lab.models.Author;
import mk.ukim.finki.emt.lab.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleOrDescriptionOrAuthorIn(String title, String description, List<Author> authors);
}
