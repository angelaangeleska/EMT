package mk.ukim.finki.emt.airbnb.service.domain.impl;

import mk.ukim.finki.emt.airbnb.models.domain.Host;
import mk.ukim.finki.emt.airbnb.repository.HostRepository;
import mk.ukim.finki.emt.airbnb.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;

    public HostServiceImpl(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> addHost(Host host) {
        if (host.getName() != null && !host.getName().isEmpty() &&
                host.getSurname() != null && !host.getSurname().isEmpty() &&
                host.getCountry() != null) {
            return Optional.of(hostRepository.save(new Host(host.getName(), host.getSurname(), host.getCountry())));
        }
        return Optional.empty();
    }
}
