package dev.lkeleti.aticrm.controller;

import dev.lkeleti.aticrm.dtos.CreatePartnerCommand;
import dev.lkeleti.aticrm.dtos.ModifyPartnerCommand;
import dev.lkeleti.aticrm.dtos.PartnerDto;
import dev.lkeleti.aticrm.service.PartnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partner")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@AllArgsConstructor
@Tag(name = "Operations on partners")
public class PartnerController {

    private PartnerService partnerService;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "List all partners",
            description = "List all partners.")
    public List<PartnerDto> listAllPartner() {
        return partnerService.listAllPartner();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "List partner by Id",
            description = "List partner by Id.")
    public PartnerDto listPartnerById(@PathVariable long id) {
        return partnerService.listPartnerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new partner",
            description = "Create new partner")
    public PartnerDto createPartner(@Valid @RequestBody CreatePartnerCommand createPartnerCommand) {
        return partnerService.createPartner(createPartnerCommand);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Create new partner",
            description = "Create new partner.")
    public PartnerDto modifyPartner(@PathVariable long id, @Valid @RequestBody ModifyPartnerCommand modifyPartnerCommand) {
        return partnerService.modifyPartner(id, modifyPartnerCommand);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete partner by Id",
            description = "Delete partner by Id.")
    public void deleteById(@PathVariable long id) {
        partnerService.deleteById(id);
    }
}
