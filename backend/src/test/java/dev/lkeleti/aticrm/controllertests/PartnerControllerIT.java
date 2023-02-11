package dev.lkeleti.aticrm.controllertests;

import com.fasterxml.jackson.databind.JsonNode;
import dev.lkeleti.aticrm.dtos.CreatePartnerCommand;
import dev.lkeleti.aticrm.dtos.ModifyPartnerCommand;
import dev.lkeleti.aticrm.dtos.PartnerDto;
import dev.lkeleti.aticrm.model.Partner;
import dev.lkeleti.aticrm.repository.PartnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Test PartnerController")
@Sql(statements = {"DELETE FROM events;", "DELETE FROM partners;"})
class PartnerControllerIT {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    ModelMapper modelMapper;

    PartnerDto partnerOneDto;
    PartnerDto partnerTwoDto;

    @BeforeEach
    void init() {
        Partner partnerOne = new Partner("John Doe","+36/30/123-4567","Budapest");
        Partner partnerTwo = new Partner("Jack Doe","+36/20/123-4567","Szolnok");

        partnerOneDto = modelMapper.map(partnerRepository.save(partnerOne), PartnerDto.class);
        partnerTwoDto = modelMapper.map(partnerRepository.save(partnerTwo), PartnerDto.class);
    }

    @Test
    @DisplayName("List all partners")
    void testListAllPartners() {
        webTestClient.get()
                .uri("/api/partner")
                .exchange()
                .expectStatus().isAccepted()
                .expectBodyList(PartnerDto.class)
                .hasSize(2)
                .value(t -> assertThat(t).extracting(PartnerDto::getName).containsExactly("John Doe", "Jack Doe"));
    }

    @Test
    @DisplayName("List partner by ID")
    void testPartnersById() {
        webTestClient.get()
                .uri("/api/partner/{id}", partnerOneDto.getId())
                .exchange()
                .expectStatus().isAccepted()
                .expectBody(PartnerDto.class)
                .value(t -> assertThat(t).extracting(PartnerDto::getName).isEqualTo("John Doe"));
    }

    @Test
    @DisplayName("Testing get partner by ID with invalid value")
    void testGetTrainByInvalidId() {
        PartnerDto result = webTestClient.get()
                .uri("/api/partner/{id}", -1)
                .exchange()
                .expectStatus().isAccepted()
                .expectBody(PartnerDto.class)
                .returnResult().getResponseBody();

        assert result == null;
    }

    @Test
    @DisplayName("Test create new partner")
    void testCreateNewPartner() {
        CreatePartnerCommand command = new CreatePartnerCommand("Jane Doe","+36/70/123-4567","Debrecen");

        webTestClient.post()
                .uri("/api/partner")
                .bodyValue(command)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(PartnerDto.class)
                .value(t->assertThat(t.getName()).isEqualTo(command.getName()));

        webTestClient.get()
                .uri("/api/partner")
                .exchange()
                .expectStatus().isAccepted()
                .expectBodyList(PartnerDto.class)
                .hasSize(3);
    }

    @Test
    @DisplayName("Test create new partner with blank name")
    void testCreateNewPartnerInvalidName() {
        CreatePartnerCommand command = new CreatePartnerCommand("","+36/70/123-4567","Vác");

        JsonNode cvp =
                webTestClient.post()
                        .uri("/api/partner")
                        .bodyValue(command)
                        .exchange()
                        .expectStatus().isBadRequest()
                        .expectBody(JsonNode.class)
                        .returnResult().getResponseBody();
        assertThat(cvp.get("error").asText()).isEqualTo("Bad Request");
    }

    @Test
    @DisplayName("Test modify partner name")
    void testModifyName() {
        ModifyPartnerCommand modifyPartnerCommand = new ModifyPartnerCommand("Jane Doe","+36/70/123-4567","Vác");

        PartnerDto partner =
                webTestClient.put()
                        .uri("/api/partner/{id}", partnerOneDto.getId())
                        .bodyValue(modifyPartnerCommand)
                        .exchange()
                        .expectStatus().isAccepted()
                        .expectBody(PartnerDto.class)
                        .returnResult().getResponseBody();
        assertThat(partner.getName()).isEqualTo("Jane Doe");
    }

    @Test
    @DisplayName("Test modify partner with blank name")
    void testModifyPartnerInvalidName() {
        ModifyPartnerCommand modifyPartnerCommand = new ModifyPartnerCommand("","+36/70/123-4567","Vác");

        JsonNode cvp =
                webTestClient.put()
                        .uri("/api/partner/{id}", partnerOneDto.getId())
                        .bodyValue(modifyPartnerCommand)
                        .exchange()
                        .expectStatus().isBadRequest()
                        .expectBody(JsonNode.class)
                        .returnResult().getResponseBody();
        assertThat(cvp.get("error").asText()).isEqualTo("Bad Request");
    }

    @Test
    @DisplayName("Test delete partner")
    void testDeletePartner() {
        webTestClient.delete()
                .uri("/api/partner/{id}", partnerOneDto.getId())
                .exchange()
                .expectStatus().isNoContent();

        webTestClient.get()
                .uri("/api/partner")
                .exchange()
                .expectStatus().isAccepted()
                .expectBodyList(PartnerDto.class)
                .hasSize(1);
    }

}
