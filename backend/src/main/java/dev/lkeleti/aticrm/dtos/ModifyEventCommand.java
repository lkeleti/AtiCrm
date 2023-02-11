package dev.lkeleti.aticrm.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ModifyEventCommand {

    @Schema(description="Date of the event", example = "2023.02.01")
    public LocalDateTime eventDate;

    @NotBlank(message = "The comment cannot be empty")
    @Schema(description="Event comment", example = "We discussed a personal meeting")
    public String comment;
}
