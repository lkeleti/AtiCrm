package dev.lkeleti.aticrm.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventDto {

    @Schema(description="Event Id", example = "1")
    public Long id;
    @Schema(description="Event date", example = "2023.02.01")
    public LocalDateTime eventDate;
    @Schema(description="Event comment", example = "We discussed a personal meeting")
    public String comment;
    @Schema(description="Partner to whom the event is connected", example = "John Doe")
    public PartnerNoEventDto partner;
}
