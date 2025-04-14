package mk.ukim.finki.emt.airbnb.dto;

import mk.ukim.finki.emt.airbnb.models.domain.Accommodation;
import mk.ukim.finki.emt.airbnb.models.domain.TmpReservation;

import java.util.List;

public record TmpReservationDto(
        Long id,
        DisplayUserDto user,
        List<DisplayAccommodationDto> accommodations
) {
    public static TmpReservationDto from(TmpReservation reservation) {
        return new TmpReservationDto(
                reservation.getId(),
                DisplayUserDto.from(reservation.getUser()),
                DisplayAccommodationDto.from(reservation.getAccommodations())
        );
    }
}
