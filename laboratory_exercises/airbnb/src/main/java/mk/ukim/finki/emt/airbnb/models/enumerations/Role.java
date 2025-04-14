package mk.ukim.finki.emt.airbnb.models.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_HOST,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
