package dev.lkeleti.aticrm.controllertests;

import com.fasterxml.jackson.databind.JsonNode;
import dev.lkeleti.aticrm.dtos.*;
import dev.lkeleti.aticrm.model.Event;
import dev.lkeleti.aticrm.model.Partner;
import dev.lkeleti.aticrm.repository.EventRepository;
import dev.lkeleti.aticrm.repository.PartnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Test EventController")
@Sql(statements = {"DELETE FROM events;", "DELETE FROM partners;"})
class EventControllerIT {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ModelMapper modelMapper;

    PartnerDto partnerOneDto;
    PartnerDto partnerTwoDto;

    EventDto eventOneDto;
    EventDto eventTwoDto;
    EventDto eventThreeDto;

    @BeforeEach
    void init() {
        Partner partnerOne = new Partner("John Doe","+36/30/123-4567","Budapest");
        Partner partnerTwo = new Partner("Jack Doe","+36/20/123-4567","Szolnok");

        partnerOneDto = modelMapper.map(partnerRepository.save(partnerOne), PartnerDto.class);
        partnerTwoDto = modelMapper.map(partnerRepository.save(partnerTwo), PartnerDto.class);

        Event eventOne = new Event(LocalDateTime.now(),"Event 1");
        eventOne.addPartner(partnerOne);
        Event eventTwo = new Event(LocalDateTime.now(),"Event 2");
        eventTwo.addPartner(partnerOne);
        Event eventThree = new Event(LocalDateTime.now(),"Event 3");
        eventThree.addPartner(partnerTwo);

        eventOneDto = modelMapper.map(eventRepository.save(eventOne), EventDto.class);
        eventTwoDto = modelMapper.map(eventRepository.save(eventTwo), EventDto.class);
        eventThreeDto = modelMapper.map(eventRepository.save(eventThree), EventDto.class);
    }

    @Test
    @DisplayName("List event by ID")
    void testEventById() {
        webTestClient.get()
                .uri("/api/event/{id}", eventOneDto.getId())
                .exchange()
                .expectStatus().isAccepted()
                .expectBody(EventDto.class)
                .value(t -> assertThat(t).extracting(EventDto::getComment).isEqualTo("Event 1"))
                .value(t -> assertThat(t).extracting(EventDto::getPartner).extracting(PartnerNoEventDto::getName) .isEqualTo("John Doe"));
    }

    @Test
    @DisplayName("Test create new event")
    void testCreateNewPartner() {
        CreateEventCommand command = new CreateEventCommand(partnerTwoDto.getId(), LocalDateTime.now(), "New Event");

        webTestClient.post()
                .uri("/api/event")
                .bodyValue(command)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(EventDto.class)
                .value(t -> assertThat(t.getComment()).isEqualTo("New Event"))
                .value(t -> assertThat(t.getPartner()).extracting(PartnerNoEventDto::getName) .isEqualTo("Jack Doe"));
    }

    @Test
    @DisplayName("Test modify event comment")
    void testModifyComment() {
        ModifyEventCommand modifyEventCommand = new ModifyEventCommand(LocalDateTime.now(),"Modified comment");

        EventDto event =
                webTestClient.put()
                        .uri("/api/event/{id}", eventOneDto.getId())
                        .bodyValue(modifyEventCommand)
                        .exchange()
                        .expectStatus().isAccepted()
                        .expectBody(EventDto.class)
                        .returnResult().getResponseBody();
        assertThat(event.getComment()).isEqualTo("Modified comment");
    }

    @Test
    @DisplayName("Test modify event with blank comment")
    void testModifyPartnerInvalidName() {
        ModifyEventCommand modifyEventCommand = new ModifyEventCommand(LocalDateTime.now(),"");
        JsonNode cvp =
                webTestClient.put()
                        .uri("/api/event/{id}", eventOneDto.getId())
                        .bodyValue(modifyEventCommand)
                        .exchange()
                        .expectStatus().isBadRequest()
                        .expectBody(JsonNode.class)
                        .returnResult().getResponseBody();
        assertThat(cvp.get("error").asText()).isEqualTo("Bad Request");
    }

    @Test
    @DisplayName("Test delete event")
    void testDeletePartner() {
        webTestClient.delete()
                .uri("/api/event/{id}", eventOneDto.getId())
                .exchange()
                .expectStatus().isNoContent();
    }
}
