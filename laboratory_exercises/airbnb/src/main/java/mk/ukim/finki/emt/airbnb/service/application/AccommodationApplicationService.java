package mk.ukim.finki.emt.airbnb.service.application;

import mk.ukim.finki.emt.airbnb.dto.CreateAccommodationDto;
import mk.ukim.finki.emt.airbnb.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt.airbnb.models.domain.Accommodation;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    List<DisplayAccommodationDto> findAll();
    Optional<DisplayAccommodationDto> addAccommodation(CreateAccommodationDto accommodation);
    Optional<DisplayAccommodationDto> findById(Long id);
    void deleteById(Long id);
    Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodation);
    Optional<DisplayAccommodationDto> changeStatus(Long id);
    List<DisplayAccommodationDto> getRecommendations(Long id);
}
