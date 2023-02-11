package dev.lkeleti.aticrm.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateEventCommand {

    @NotNull(message = "Partner id cannot be null")
    @Schema(description="Partner id", example = "1")
    private Long partnerId;

    @Schema(description="Date of the event", example = "2023.02.01")
    private LocalDateTime eventDate;

    @NotBlank(message = "The comment cannot be empty")
    @Schema(description="Event comment", example = "We discussed a personal meeting")
    private String comment;
}
