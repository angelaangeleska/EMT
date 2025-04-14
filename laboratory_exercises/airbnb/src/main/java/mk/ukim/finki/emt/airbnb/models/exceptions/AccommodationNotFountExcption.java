package mk.ukim.finki.emt.airbnb.models.exceptions;

public class AccommodationNotFountExcption extends RuntimeException {
    public AccommodationNotFountExcption(Long reservationId) {
        super(String.format("Accommodation with id %d was not found", reservationId));
    }
}
