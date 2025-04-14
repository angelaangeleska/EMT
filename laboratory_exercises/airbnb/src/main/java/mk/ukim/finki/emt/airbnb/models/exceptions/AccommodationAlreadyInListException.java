package mk.ukim.finki.emt.airbnb.models.exceptions;

public class AccommodationAlreadyInListException extends RuntimeException {
    public AccommodationAlreadyInListException(Long accommodationId, String username) {
        super(String.format("Accommodation with id: %d already exists in the list for user with username %s", accommodationId, username));
    }
}
