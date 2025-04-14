package mk.ukim.finki.emt.airbnb.service.domain;

import mk.ukim.finki.emt.airbnb.models.domain.TmpReservation;

import java.util.Optional;

public interface TmpReservationService {
    Optional<TmpReservation> getTmpReservationsList(String username);
    Optional<TmpReservation> addAccommodationToTmpReservation(String username, Long accommodationId);
    Optional<TmpReservation> reserveAllAccommodations(String username);
    Optional<TmpReservation> reserveAccommodation(String username, Long accommodationId);
}
