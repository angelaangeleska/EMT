package mk.ukim.finki.emt.airbnb.service.application.impl;

import mk.ukim.finki.emt.airbnb.dto.CreateHostDto;
import mk.ukim.finki.emt.airbnb.dto.DisplayHostDto;
import mk.ukim.finki.emt.airbnb.models.domain.Country;
import mk.ukim.finki.emt.airbnb.models.domain.Host;
import mk.ukim.finki.emt.airbnb.service.application.HostApplicationService;
import mk.ukim.finki.emt.airbnb.service.domain.CountryService;
import mk.ukim.finki.emt.airbnb.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {
    private final HostService hostService;
    private final CountryService countryService;

    public HostApplicationServiceImpl(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id).map(DisplayHostDto::from);
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return hostService.findAll().stream().map(DisplayHostDto::from).collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayHostDto> addHost(CreateHostDto host) {
        Optional<Country> country = countryService.findById(host.countryId());
        if (country.isPresent()) {
            return hostService.addHost(host.toHost(country.get())).map(DisplayHostDto::from);
        }
        return Optional.empty();
    }
}
