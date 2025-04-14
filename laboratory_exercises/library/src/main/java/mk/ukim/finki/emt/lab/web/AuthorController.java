package mk.ukim.finki.emt.lab.web;

import mk.ukim.finki.emt.lab.models.Author;
import mk.ukim.finki.emt.lab.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> listAll() {
        return this.authorService.listAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        return this.authorService.addAuthor(author)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
