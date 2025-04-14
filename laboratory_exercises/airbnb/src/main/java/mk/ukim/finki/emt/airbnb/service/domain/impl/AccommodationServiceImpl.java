package mk.ukim.finki.emt.airbnb.service.domain.impl;

import mk.ukim.finki.emt.airbnb.models.domain.Accommodation;
import mk.ukim.finki.emt.airbnb.models.domain.Host;
import mk.ukim.finki.emt.airbnb.models.enumerations.AccommodationStatus;
import mk.ukim.finki.emt.airbnb.repository.AccommodationRepository;
import mk.ukim.finki.emt.airbnb.service.domain.AccommodationService;
import mk.ukim.finki.emt.airbnb.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> addAccommodation(Accommodation accommodation) {
        Optional<Host> host = hostService.findById(accommodation.getHost().getId());
        if (accommodation.getName() != null && !accommodation.getName().isEmpty() &&
                accommodation.getCategory() != null &&
                host.isPresent() &&
                accommodation.getNumRooms() != null) {
            return Optional.of(accommodationRepository.save(new Accommodation(accommodation.getName(), accommodation.getCategory(), host.get(), accommodation.getNumRooms())));

        }
        return Optional.empty();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        if (accommodationRepository.findById(id).isPresent()) {
            Accommodation existingAccommodation = accommodationRepository.findById(id).get();
            if (accommodation.getName() != null && !accommodation.getName().isEmpty()) {
                existingAccommodation.setName(accommodation.getName());
            }
            if (accommodation.getCategory() != null) {
                existingAccommodation.setCategory(accommodation.getCategory());
            }
            if (accommodation.getCategory() != null) {
                existingAccommodation.setCategory(accommodation.getCategory());
            }
            if (accommodation.getNumRooms() != null) {
                existingAccommodation.setNumRooms(accommodation.getNumRooms());
            }
            return Optional.of(accommodationRepository.save(existingAccommodation));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Accommodation> changeStatus(Long id) {
        if (accommodationRepository.findById(id).isPresent()) {
            Accommodation accommodation = accommodationRepository.findById(id).get();
            if (accommodation.getStatus() == AccommodationStatus.BOOKED) {
                accommodation.setStatus(AccommodationStatus.AVAILABLE);
            } else {
                accommodation.setStatus(AccommodationStatus.BOOKED);
            }
            return Optional.of(accommodationRepository.save(accommodation));
        }
        return Optional.empty();
    }

    @Override
    public List<Accommodation> getRecommendations(Long id) {
//        if (accommodationRepository.findById(id).isPresent()) {
//            Accommodation accommodation = accommodationRepository.findById(id).get();
//            List<Accommodation> recommendations = this.findAll().stream()
//                    .filter(x -> !x.getId().equals(accommodation.getId()))
//                    .filter(x -> x.getStatus() == AccommodationStatus.AVAILABLE)
//                    .filter(x -> x.getCategory().equals(accommodation.getCategory()))
//                    .limit(3).toList();
//            if (recommendations.isEmpty()) {
//                return new ArrayList<>();
//            }
//            return recommendations;
//        }
//
//        return List.of();
        if (accommodationRepository.findById(id).isPresent()) {
            Accommodation accommodation = accommodationRepository.findById(id).get();
            return accommodationRepository.findTop3ByIdNotAndStatusAndCategory(id, AccommodationStatus.AVAILABLE, accommodation.getCategory());
        }
        return new ArrayList<>();
    }
}
