package mk.ukim.finki.emt.airbnb.service.application.impl;

import mk.ukim.finki.emt.airbnb.dto.CreateAccommodationDto;
import mk.ukim.finki.emt.airbnb.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt.airbnb.models.domain.Host;
import mk.ukim.finki.emt.airbnb.service.application.AccommodationApplicationService;
import mk.ukim.finki.emt.airbnb.service.domain.AccommodationService;
import mk.ukim.finki.emt.airbnb.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return DisplayAccommodationDto.from(accommodationService.findAll());
    }

    @Override
    public Optional<DisplayAccommodationDto> addAccommodation(CreateAccommodationDto accommodation) {
        Optional<Host> host = hostService.findById(accommodation.hostId());
        if (host.isPresent()) {
            return accommodationService.addAccommodation(accommodation.toAccommodation(host.get())).map(DisplayAccommodationDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public void deleteById(Long id) {
        accommodationService.deleteById(id);
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodation) {
        Optional<Host> host = hostService.findById(accommodation.hostId());
        return accommodationService.update(id, accommodation.toAccommodation(host.orElse(null))).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> changeStatus(Long id) {
        return accommodationService.changeStatus(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public List<DisplayAccommodationDto> getRecommendations(Long id) {
        return accommodationService.getRecommendations(id).stream().map(DisplayAccommodationDto::from).collect(Collectors.toList());
    }
}
