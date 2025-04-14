package mk.ukim.finki.emt.airbnb.dto;

import mk.ukim.finki.emt.airbnb.models.domain.User;
import mk.ukim.finki.emt.airbnb.models.enumerations.Role;

public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role
) {

    public User toUser() {
        validatePasswords();
        return new User(username, password, name, surname, role);
    }

    private void validatePasswords() {
        if (!password.equals(repeatPassword)) {
            throw new IllegalArgumentException("Passwords do not match!");
        }
    }
}

