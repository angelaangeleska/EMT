package mk.ukim.finki.emt.lab.web;

import mk.ukim.finki.emt.lab.models.Book;
import mk.ukim.finki.emt.lab.models.dto.BookDto;
import mk.ukim.finki.emt.lab.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> listAll(){
        return this.bookService.listAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody BookDto book) {
        return this.bookService.addBook(book)
                .map(b -> ResponseEntity.ok().body(b))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.findById(id).isPresent()) {
            this.bookService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody BookDto book) {
        return this.bookService.update(id, book)
                .map(b -> ResponseEntity.ok().body(b))
                .orElseGet(() -> ResponseEntity.badRequest().build())   ;
    }

    @PostMapping("/change-status/{id}")
    public ResponseEntity<Book> changeStatus(@PathVariable Long id) {
        return this.bookService.changeStatus(id)
                .map(b -> ResponseEntity.ok().body(b))
                .orElseGet(() -> ResponseEntity.badRequest().build())   ;
    }

    @GetMapping("/filter")
    public List<Book> filterBooks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String description,
                                  @RequestParam(required = false) String author) {
        System.out.println(title);
        return this.bookService.filterBooks(title, description, author);
    }
}
