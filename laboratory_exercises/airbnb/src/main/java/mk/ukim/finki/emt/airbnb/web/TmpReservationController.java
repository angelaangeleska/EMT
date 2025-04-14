package mk.ukim.finki.emt.airbnb.web;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.emt.airbnb.dto.TmpReservationDto;
import mk.ukim.finki.emt.airbnb.models.domain.TmpReservation;
import mk.ukim.finki.emt.airbnb.models.domain.User;
import mk.ukim.finki.emt.airbnb.service.application.TmpReservationApplicationService;
import mk.ukim.finki.emt.airbnb.service.domain.TmpReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tmp-reservations")
public class TmpReservationController {
    private final TmpReservationApplicationService tmpReservationService;

    public TmpReservationController(TmpReservationApplicationService tmpReservationService) {
        this.tmpReservationService = tmpReservationService;
    }

    @GetMapping
    public ResponseEntity<TmpReservationDto> getActiveShoppingCart(HttpServletRequest req) {
        String username = req.getRemoteUser();
        return tmpReservationService.getTmpReservationsList(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/add-tmp-reservation/{id}")
    public ResponseEntity<TmpReservationDto> addProductToShoppingCart(
            @PathVariable Long id,
            Authentication authentication
    ) {
        try {
            User user = (User) authentication.getPrincipal();
            return tmpReservationService.addAccommodationToTmpReservation(user.getUsername(), id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/reserve-all")
    public ResponseEntity<TmpReservationDto> reserveAllAccommodations(Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            return tmpReservationService.reserveAllAccommodations(user.getUsername())
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/reserve/{id}")
    public ResponseEntity<TmpReservationDto> reserveAccommodation(
            @PathVariable Long id,
            Authentication authentication
    ) {
        try {
            User user = (User) authentication.getPrincipal();
            return tmpReservationService.reserveAccommodation(user.getUsername(), id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
