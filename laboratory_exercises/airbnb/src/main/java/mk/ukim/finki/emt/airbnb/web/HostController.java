package mk.ukim.finki.emt.airbnb.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mk.ukim.finki.emt.airbnb.dto.CreateHostDto;
import mk.ukim.finki.emt.airbnb.dto.DisplayHostDto;
import mk.ukim.finki.emt.airbnb.service.application.HostApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/host")
public class HostController {
    private final HostApplicationService hostService;

    public HostController(HostApplicationService hostService) {
        this.hostService = hostService;
    }

    @Operation(summary = "List all hosts", description = "Retrieve a list of all registered hosts.")
    @GetMapping
    public List<DisplayHostDto> listAll() {
        return hostService.findAll();
    }

    @Operation(summary = "Add a new host", description = "Create and add a new host to the system.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Host added successfully"),
            @ApiResponse(responseCode = "404", description = "Host could not be added")
    })
    @PostMapping("/add")
    public ResponseEntity<DisplayHostDto> addHost(@RequestBody CreateHostDto host) {
        return hostService.addHost(host)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
