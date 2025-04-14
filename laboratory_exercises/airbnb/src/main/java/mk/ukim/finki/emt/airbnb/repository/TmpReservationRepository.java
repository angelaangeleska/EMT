package mk.ukim.finki.emt.airbnb.repository;

import mk.ukim.finki.emt.airbnb.models.domain.TmpReservation;
import mk.ukim.finki.emt.airbnb.models.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TmpReservationRepository extends JpaRepository<TmpReservation, Long> {
    Optional<TmpReservation> findByUser(User user);
}
