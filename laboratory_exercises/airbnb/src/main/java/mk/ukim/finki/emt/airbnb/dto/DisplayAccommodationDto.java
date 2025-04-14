package mk.ukim.finki.emt.airbnb.dto;

import mk.ukim.finki.emt.airbnb.models.domain.Accommodation;
import mk.ukim.finki.emt.airbnb.models.domain.Host;
import mk.ukim.finki.emt.airbnb.models.enumerations.AccommodationStatus;
import mk.ukim.finki.emt.airbnb.models.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAccommodationDto(
        Long id,
        String name,
        Category category,
        Long hostId,
        Integer numRooms,
        AccommodationStatus status
) {
    public Accommodation toAccommodation(Host host) {
        return new Accommodation(name, category, host, numRooms);
    }

    public static DisplayAccommodationDto from(Accommodation accommodation) {
        return new DisplayAccommodationDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getId(),
                accommodation.getNumRooms(),
                accommodation.getStatus()
        );
    }

    public static List<DisplayAccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(DisplayAccommodationDto::from).collect(Collectors.toList());
    }
}
