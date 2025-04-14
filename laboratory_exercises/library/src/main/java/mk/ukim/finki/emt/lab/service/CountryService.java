package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.models.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> listAll();
    Optional<Country> addCountry(Country country);
    Optional<Country> findCountryById(Long id);
}
