package dev.lkeleti.aticrm.controller;

import dev.lkeleti.aticrm.dtos.CreateEventCommand;
import dev.lkeleti.aticrm.dtos.EventDto;
import dev.lkeleti.aticrm.dtos.ModifyEventCommand;
import dev.lkeleti.aticrm.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@AllArgsConstructor
@Tag(name = "Operations on events")
public class EventController {

    private EventService eventService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "List event by Id",
            description = "List event by Id.")
    public EventDto findEventById(@PathVariable long id) {
        return eventService.findEventById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new event and add to partner",
            description = "Create new event and add to partner.")
    public EventDto createEvent(@Valid @RequestBody CreateEventCommand createEventCommand) {
        return eventService.createEvent(createEventCommand);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Modify event by Id",
            description = "Modify event by Id.")
    public EventDto modifyEvent(@PathVariable long id, @Valid @RequestBody ModifyEventCommand modifyEventCommand) {
        return eventService.modifyEvent(id, modifyEventCommand);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete event by Id",
            description = "Delete event by Id.")
    public void deleteEventById(@PathVariable long id) {
        eventService.deleteEventById(id);
    }
}
