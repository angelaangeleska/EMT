package mk.ukim.finki.emt.airbnb.service.application;

import mk.ukim.finki.emt.airbnb.dto.CreateCountryDto;
import mk.ukim.finki.emt.airbnb.dto.DisplayCountryDto;
import mk.ukim.finki.emt.airbnb.models.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    Optional<DisplayCountryDto> findById(Long id);
    List<DisplayCountryDto> listAll();
    Optional<DisplayCountryDto> addCountry(CreateCountryDto country);
}
