package mk.ukim.finki.emt.airbnb.service.domain;

import mk.ukim.finki.emt.airbnb.models.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Optional<Country> findById(Long id);
    List<Country> listAll();
    Optional<Country> addCountry(Country country);
}
