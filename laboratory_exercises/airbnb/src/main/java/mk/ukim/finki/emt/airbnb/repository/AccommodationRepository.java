package mk.ukim.finki.emt.airbnb.repository;

import mk.ukim.finki.emt.airbnb.models.domain.Accommodation;
import mk.ukim.finki.emt.airbnb.models.enumerations.AccommodationStatus;
import mk.ukim.finki.emt.airbnb.models.enumerations.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    List<Accommodation> findTop3ByIdNotAndStatusAndCategory(Long id, AccommodationStatus status, Category category);
}