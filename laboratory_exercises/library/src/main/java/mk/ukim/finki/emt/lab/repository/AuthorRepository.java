package mk.ukim.finki.emt.lab.repository;

import mk.ukim.finki.emt.lab.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByNameOrSurname(String name, String surname);
}
