package mk.ukim.finki.emt.airbnb.service.domain;

import mk.ukim.finki.emt.airbnb.models.domain.Host;

import java.util.List;
import java.util.Optional;

public interface HostService {
    Optional<Host> findById(Long id);
    List<Host> findAll();
    Optional<Host> addHost(Host host);
}
