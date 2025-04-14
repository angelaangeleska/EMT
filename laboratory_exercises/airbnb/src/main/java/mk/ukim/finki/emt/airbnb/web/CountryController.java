package mk.ukim.finki.emt.airbnb.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mk.ukim.finki.emt.airbnb.dto.CreateCountryDto;
import mk.ukim.finki.emt.airbnb.dto.DisplayCountryDto;
import mk.ukim.finki.emt.airbnb.service.application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/country")
public class CountryController {
    private final CountryApplicationService countryService;

    public CountryController(CountryApplicationService countryService) {
        this.countryService = countryService;
    }

    @Operation(summary = "List all countries", description = "Retrieve a list of all available countries.")
    @GetMapping
    public List<DisplayCountryDto> listAll() {
        return countryService.listAll();
    }

    @Operation(summary = "Add a new country", description = "Create and add a new country to the system.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Country added successfully"),
            @ApiResponse(responseCode = "404", description = "Country could not be added")
    })
    @PostMapping("/add")
    public ResponseEntity<DisplayCountryDto> addCountry(@RequestBody CreateCountryDto country) {
        return countryService.addCountry(country)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
