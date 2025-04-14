package mk.ukim.finki.emt.airbnb.dto;

import mk.ukim.finki.emt.airbnb.models.domain.Accommodation;
import mk.ukim.finki.emt.airbnb.models.domain.Host;
import mk.ukim.finki.emt.airbnb.models.enumerations.AccommodationStatus;
import mk.ukim.finki.emt.airbnb.models.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record CreateAccommodationDto(
        String name,
        Category category,
        Long hostId,
        Integer numRooms,
        AccommodationStatus status
) {
    public Accommodation toAccommodation(Host host) {
        return new Accommodation(name, category, host, numRooms);
    }

    public static CreateAccommodationDto from(Accommodation accommodation) {
        return new CreateAccommodationDto(
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getId(),
                accommodation.getNumRooms(),
                accommodation.getStatus()
        );
    }

    public static List<CreateAccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(CreateAccommodationDto::from).collect(Collectors.toList());
    }
}
