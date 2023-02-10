package dev.lkeleti.aticrm.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ModifyPartnerCommand {

        @NotBlank(message = "Partner name cannot be empty")
        @Schema(description="Partner name", example = "John Doe")
        public String name;

        @NotBlank(message = "Partner phone number cannot be empty")
        @Schema(description="Partner phone number", example = "+36/30/123-4567")
        public String phone;

        @Schema(description="Address of the partner", example = "Budapest")
        public String city;
}
