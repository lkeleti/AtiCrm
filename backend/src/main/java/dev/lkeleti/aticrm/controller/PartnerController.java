package dev.lkeleti.aticrm.controller;

import dev.lkeleti.aticrm.dtos.CreatePartnerCommand;
import dev.lkeleti.aticrm.dtos.ModifyPartnerCommand;
import dev.lkeleti.aticrm.dtos.PartnerDto;
import dev.lkeleti.aticrm.service.PartnerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partner")
@AllArgsConstructor
public class PartnerController {

    private PartnerService partnerService;

    @GetMapping
    public List<PartnerDto> listAllPartner() {
        return partnerService.listAllPartner();
    }

    @GetMapping("/{id}")
    public PartnerDto listPartnerById(@PathVariable long id) {
        return partnerService.listPartnerById(id);
    }

    @PostMapping
    public PartnerDto createPartner(@Valid @RequestBody CreatePartnerCommand createPartnerCommand) {
        return partnerService.createPartner(createPartnerCommand);
    }

    @PutMapping("/{id}")
    public PartnerDto modifyPartner(@PathVariable long id, @Valid @RequestBody ModifyPartnerCommand modifyPartnerCommand) {
        return partnerService.modifyPartner(id, modifyPartnerCommand);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        partnerService.deleteById(id);
    }
}
