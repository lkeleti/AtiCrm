package dev.lkeleti.aticrm.service;

import dev.lkeleti.aticrm.dtos.CreateEventCommand;
import dev.lkeleti.aticrm.dtos.EventDto;
import dev.lkeleti.aticrm.dtos.ModifyEventCommand;
import dev.lkeleti.aticrm.model.Event;
import dev.lkeleti.aticrm.model.Partner;
import dev.lkeleti.aticrm.repository.EventRepository;
import dev.lkeleti.aticrm.repository.PartnerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventService {
    private ModelMapper modelMapper;
    private EventRepository eventRepository;
    private PartnerRepository partnerRepository;

    public EventDto findEventById(long id) {
        return modelMapper.map(eventRepository.findById(id), EventDto.class);
    }

    public void deleteEventById(long id) {
        eventRepository.deleteById(id);
    }

    @Transactional
    public EventDto modifyEvent(long id, ModifyEventCommand modifyEventCommand) {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Can't find event")
        );
        event.setEventDate(modifyEventCommand.getEventDate());
        event.setComment(modifyEventCommand.getComment());

        return modelMapper.map(event, EventDto.class);
    }

    @Transactional
    public EventDto createEvent(CreateEventCommand createEventCommand) {
        Event event = new Event(
                createEventCommand.getEventDate(),
                createEventCommand.getComment()
        );

        event = eventRepository.save(event);

        Partner partner = partnerRepository.findById(createEventCommand.getPartnerId()).orElseThrow(
                ()-> new IllegalArgumentException("Can't find partner")
        );
        event.addPartner(partner);
        return modelMapper.map(event, EventDto.class);
    }
}
