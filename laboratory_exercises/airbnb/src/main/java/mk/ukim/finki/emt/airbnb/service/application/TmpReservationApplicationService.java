package mk.ukim.finki.emt.airbnb.service.application;

import mk.ukim.finki.emt.airbnb.dto.TmpReservationDto;
import mk.ukim.finki.emt.airbnb.models.domain.TmpReservation;

import java.util.Optional;

public interface TmpReservationApplicationService {
    Optional<TmpReservationDto> getTmpReservationsList(String username);
    Optional<TmpReservationDto> addAccommodationToTmpReservation(String username, Long accommodationId);
    Optional<TmpReservationDto> reserveAllAccommodations(String username);
    Optional<TmpReservationDto> reserveAccommodation(String username, Long accommodationId);
}

