package mk.ukim.finki.emt.airbnb.models.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.airbnb.models.enumerations.AccommodationStatus;
import mk.ukim.finki.emt.airbnb.models.enumerations.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    private Host host;
    private Integer numRooms;
    @Enumerated
    private AccommodationStatus status;

    public Accommodation(String name, Category category, Host host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.status = AccommodationStatus.AVAILABLE;
    }
}
