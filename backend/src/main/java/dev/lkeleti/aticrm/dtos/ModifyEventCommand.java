package dev.lkeleti.aticrm.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ModifyEventCommand {

    @NotNull(message = "Event date cannot be null")
    @Schema(description="Date of the event", example = "2023.02.01")
    public LocalDateTime eventDate;

    @Schema(description="Event comment", example = "We discussed a personal meeting")
    public String comment;
}
