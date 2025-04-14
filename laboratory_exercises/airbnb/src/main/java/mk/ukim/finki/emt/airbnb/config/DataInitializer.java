package mk.ukim.finki.emt.airbnb.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt.airbnb.models.domain.User;
import mk.ukim.finki.emt.airbnb.models.enumerations.Role;
import mk.ukim.finki.emt.airbnb.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        userRepository.save(new User(
                "admin",
                passwordEncoder.encode("admin"),
                "Angela",
                "Angeleska",
                Role.ROLE_HOST
        ));

        userRepository.save(new User(
                "tamara",
                passwordEncoder.encode("tamara"),
                "Tamara",
                "Stamatovska",
                Role.ROLE_USER
        ));
    }
}
