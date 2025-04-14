package mk.ukim.finki.emt.airbnb.service.domain.impl;

import mk.ukim.finki.emt.airbnb.models.domain.Accommodation;
import mk.ukim.finki.emt.airbnb.models.domain.TmpReservation;
import mk.ukim.finki.emt.airbnb.models.domain.User;
import mk.ukim.finki.emt.airbnb.models.enumerations.AccommodationStatus;
import mk.ukim.finki.emt.airbnb.models.exceptions.AccommodationAlreadyInListException;
import mk.ukim.finki.emt.airbnb.models.exceptions.AccommodationNotAvailable;
import mk.ukim.finki.emt.airbnb.models.exceptions.AccommodationNotFountExcption;
import mk.ukim.finki.emt.airbnb.repository.TmpReservationRepository;
import mk.ukim.finki.emt.airbnb.service.domain.AccommodationService;
import mk.ukim.finki.emt.airbnb.service.domain.TmpReservationService;
import mk.ukim.finki.emt.airbnb.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TmpReservationServiceImpl implements TmpReservationService {
    private final TmpReservationRepository tmpReservationRepository;
    private final UserService userService;
    private final AccommodationService accommodationService;

    public TmpReservationServiceImpl(TmpReservationRepository tmpReservationRepository, UserService userService, AccommodationService accommodationService) {
        this.tmpReservationRepository = tmpReservationRepository;
        this.userService = userService;
        this.accommodationService = accommodationService;
    }

    @Override
    public Optional<TmpReservation> getTmpReservationsList(String username) {
        User user = userService.findByUsername(username);
        return Optional.of(tmpReservationRepository.findByUser(user).orElseGet(() -> tmpReservationRepository.save(new TmpReservation(user))));
    }

    @Override
    public Optional<TmpReservation> addAccommodationToTmpReservation(String username, Long accommodationId) {
        if (getTmpReservationsList(username).isPresent()) {
            TmpReservation reservation = getTmpReservationsList(username).get();
            Accommodation accommodation = accommodationService.findById(accommodationId).orElseThrow(() -> new AccommodationNotFountExcption(accommodationId));
            if (!reservation.getAccommodations().stream().filter(x -> x.getId().equals(accommodationId)).toList().isEmpty()) {
                throw new AccommodationAlreadyInListException(accommodationId, username);
            }
            if (accommodation.getStatus().equals(AccommodationStatus.BOOKED)) {
                throw new AccommodationNotAvailable(accommodationId);
            }
            reservation.getAccommodations().add(accommodation);
            return Optional.of(tmpReservationRepository.save(reservation));
        }
        return Optional.empty();
    }

    @Override
    public Optional<TmpReservation> reserveAllAccommodations(String username) {
        if (getTmpReservationsList(username).isPresent()) {
            TmpReservation reservation = getTmpReservationsList(username).get();
            reservation.getAccommodations().forEach(x -> x.setStatus(AccommodationStatus.BOOKED));
            return Optional.of(tmpReservationRepository.save(reservation));
        }
        return Optional.empty();
    }

    @Override
    public Optional<TmpReservation> reserveAccommodation(String username, Long accommodationId) {
        if (getTmpReservationsList(username).isPresent()) {
            TmpReservation reservation = getTmpReservationsList(username).get();
            reservation.getAccommodations().stream().filter(x -> x.getId().equals(accommodationId)).findFirst().ifPresent(x -> x.setStatus(AccommodationStatus.BOOKED));
            return Optional.of(tmpReservationRepository.save(reservation));
        }
        return Optional.empty();
    }
}
