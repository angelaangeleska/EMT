package mk.ukim.finki.emt.lab.web;

import mk.ukim.finki.emt.lab.models.Country;
import mk.ukim.finki.emt.lab.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> listAll() {
        return countryService.listAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Country> addCountry(@RequestBody Country country) {
        return this.countryService.addCountry(country)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
