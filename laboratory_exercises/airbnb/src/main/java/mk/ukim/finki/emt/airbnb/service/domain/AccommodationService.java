package mk.ukim.finki.emt.airbnb.service.domain;

import mk.ukim.finki.emt.airbnb.models.domain.Accommodation;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();
    Optional<Accommodation> addAccommodation(Accommodation accommodation);
    Optional<Accommodation> findById(Long id);
    void deleteById(Long id);
    Optional<Accommodation> update(Long id, Accommodation accommodation);
    Optional<Accommodation> changeStatus(Long id);
    List<Accommodation> getRecommendations(Long id);
}
