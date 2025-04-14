package mk.ukim.finki.emt.airbnb.service.application;

import mk.ukim.finki.emt.airbnb.dto.CreateHostDto;
import mk.ukim.finki.emt.airbnb.dto.DisplayHostDto;
import mk.ukim.finki.emt.airbnb.models.domain.Host;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    Optional<DisplayHostDto> findById(Long id);
    List<DisplayHostDto> findAll();
    Optional<DisplayHostDto> addHost(CreateHostDto host);
}
