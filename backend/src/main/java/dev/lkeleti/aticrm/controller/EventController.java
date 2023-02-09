package dev.lkeleti.aticrm.controller;

import dev.lkeleti.aticrm.dtos.CreateEventCommand;
import dev.lkeleti.aticrm.dtos.EventDto;
import dev.lkeleti.aticrm.dtos.ModifyEventCommand;
import dev.lkeleti.aticrm.service.EventService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
@AllArgsConstructor
public class EventController {

    private EventService eventService;

    @GetMapping("/{id}")
    public EventDto findEventById(@PathVariable long id) {
        return eventService.findEventById(id);
    }

    @PostMapping
    public EventDto createEvent(@Valid @RequestBody CreateEventCommand createEventCommand) {
        return eventService.createEvent(createEventCommand);
    }


    @PutMapping("/{id}")
    public EventDto modifyEvent(@PathVariable long id, @Valid @RequestBody ModifyEventCommand modifyEventCommand) {
        return eventService.modifyEvent(id, modifyEventCommand);
    }

    @DeleteMapping("/{id}")
    public void deleteEventById(@PathVariable long id) {
        eventService.deleteEventById(id);
    }
}
