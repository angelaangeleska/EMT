package mk.ukim.finki.emt.airbnb.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mk.ukim.finki.emt.airbnb.dto.CreateAccommodationDto;
import mk.ukim.finki.emt.airbnb.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt.airbnb.service.application.AccommodationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/accommodations")
public class AccommodationController {
    private final AccommodationApplicationService accommodationService;

    public AccommodationController(AccommodationApplicationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @Operation(summary = "List all accommodations", description = "Retrieve a list of all available accommodations.")
    @GetMapping
    public List<DisplayAccommodationDto> listAll() {
        return accommodationService.findAll();
    }

    @Operation(summary = "Add a new accommodation", description = "Create and add a new accommodation to the system.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Accommodation added successfully"),
            @ApiResponse(responseCode = "404", description = "Accommodation could not be added")
    })
    @PostMapping("/add")
    public ResponseEntity<DisplayAccommodationDto> addAccommodation(@RequestBody CreateAccommodationDto accommodation) {
        return accommodationService.addAccommodation(accommodation)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an accommodation", description = "Delete an existing accommodation by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Accommodation deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Accommodation not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id) {
        if (accommodationService.findById(id).isPresent()) {
            accommodationService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Update an accommodation", description = "Update the details of an existing accommodation by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Accommodation updated successfully"),
            @ApiResponse(responseCode = "404", description = "Accommodation not found")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<DisplayAccommodationDto> updateAccommodation(@PathVariable Long id, @RequestBody CreateAccommodationDto accommodation) {
        return accommodationService.update(id, accommodation)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Change status of an accommodation", description = "Change the status of an existing accommodation by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status changed successfully"),
            @ApiResponse(responseCode = "404", description = "Accommodation not found")
    })
    @PostMapping("/change-status/{id}")
    public ResponseEntity<DisplayAccommodationDto> changeStatus(@PathVariable Long id) {
        return accommodationService.changeStatus(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get recommendations for an accommodation", description = "Retrieve a list of recommended accommodations based on a given ID.")
    @GetMapping("/recommendations/{id}")
    public List<DisplayAccommodationDto> getRecommendations(@PathVariable Long id) {
        return accommodationService.getRecommendations(id);
    }
}
