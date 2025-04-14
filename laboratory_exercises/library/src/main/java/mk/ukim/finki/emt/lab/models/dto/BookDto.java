package mk.ukim.finki.emt.lab.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.lab.models.enumerations.BookStatus;
import mk.ukim.finki.emt.lab.models.enumerations.Category;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private String title;
    private String description;
    private Category category;
    private Long authorId;
    private Integer availableCopies;
    private BookStatus status;
}
