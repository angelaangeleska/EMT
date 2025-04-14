package mk.ukim.finki.emt.airbnb.service.domain.impl;

import mk.ukim.finki.emt.airbnb.models.domain.Country;
import mk.ukim.finki.emt.airbnb.repository.CountryRepository;
import mk.ukim.finki.emt.airbnb.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public List<Country> listAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> addCountry(Country country) {
        if (country.getName() != null && !country.getName().isEmpty() &&
                country.getContinent() != null && !country.getContinent().isEmpty()) {
            return Optional.of(countryRepository.save(new Country(country.getName(), country.getContinent())));
        }
        return Optional.empty();
    }
}
