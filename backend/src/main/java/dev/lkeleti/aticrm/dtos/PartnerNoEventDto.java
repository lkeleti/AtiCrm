package dev.lkeleti.aticrm.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PartnerNoEventDto {

    @Schema(description="Partner Id", example = "1")
    public Long id;

    @Schema(description="Partner name", example = "John Doe")
    public String name;

    @Schema(description="Partner phone number", example = "+36/30/123-4567")
    public String phone;

    @Schema(description="Address of the partner", example = "Budapest")
    public String city;

}
