package mk.ukim.finki.emt.airbnb.service.application.impl;

import mk.ukim.finki.emt.airbnb.dto.CreateCountryDto;
import mk.ukim.finki.emt.airbnb.dto.DisplayCountryDto;
import mk.ukim.finki.emt.airbnb.models.domain.Country;
import mk.ukim.finki.emt.airbnb.service.application.CountryApplicationService;
import mk.ukim.finki.emt.airbnb.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {
    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDto::from);
    }

    @Override
    public List<DisplayCountryDto> listAll() {
        return countryService.listAll().stream().map(DisplayCountryDto::from).collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayCountryDto> addCountry(CreateCountryDto country) {
        return countryService.addCountry(country.toCountry()).map(DisplayCountryDto::from);
    }
}
