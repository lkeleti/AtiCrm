package dev.lkeleti.aticrm.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventNoPartnerDto {

    @Schema(description="Event Id", example = "1")
    public Long id;
    @Schema(description="Event date", example = "2023.02.01")
    public LocalDateTime eventDate;
    @Schema(description="Event comment", example = "We discussed a personal meeting")
    public String comment;
}
