package mk.ukim.finki.emt.airbnb.models.exceptions;

public class AccommodationNotAvailable extends RuntimeException {
    public AccommodationNotAvailable(Long accommodationId) {
        super(String.format("Accommodation with id: %s is not available", accommodationId));
    }
}
