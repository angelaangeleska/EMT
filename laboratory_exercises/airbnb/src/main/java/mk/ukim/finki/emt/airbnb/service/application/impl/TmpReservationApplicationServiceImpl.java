package mk.ukim.finki.emt.airbnb.service.application.impl;

import mk.ukim.finki.emt.airbnb.dto.TmpReservationDto;
import mk.ukim.finki.emt.airbnb.models.domain.TmpReservation;
import mk.ukim.finki.emt.airbnb.service.application.TmpReservationApplicationService;
import mk.ukim.finki.emt.airbnb.service.domain.TmpReservationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TmpReservationApplicationServiceImpl implements TmpReservationApplicationService {
    private final TmpReservationService tmpReservationService;

    public TmpReservationApplicationServiceImpl(TmpReservationService tmpReservationService) {
        this.tmpReservationService = tmpReservationService;
    }

    @Override
    public Optional<TmpReservationDto> getTmpReservationsList(String username) {
        return tmpReservationService.getTmpReservationsList(username).map(TmpReservationDto::from);
    }

    @Override
    public Optional<TmpReservationDto> addAccommodationToTmpReservation(String username, Long accommodationId) {
        return tmpReservationService.addAccommodationToTmpReservation(username, accommodationId).map(TmpReservationDto::from);
    }

    @Override
    public Optional<TmpReservationDto> reserveAllAccommodations(String username) {
        return tmpReservationService.reserveAllAccommodations(username).map(TmpReservationDto::from);
    }

    @Override
    public Optional<TmpReservationDto> reserveAccommodation(String username, Long accommodationId) {
        return tmpReservationService.reserveAccommodation(username, accommodationId).map(TmpReservationDto::from);
    }
}
