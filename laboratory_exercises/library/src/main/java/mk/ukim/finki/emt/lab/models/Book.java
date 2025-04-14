package mk.ukim.finki.emt.lab.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.lab.models.enumerations.BookStatus;
import mk.ukim.finki.emt.lab.models.enumerations.Category;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    private Author author;
    private Integer availableCopies;
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    public Book(String title, String description, Category category, Author author, Integer availableCopies) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
        this.status = BookStatus.AVAILABLE;
    }
}
