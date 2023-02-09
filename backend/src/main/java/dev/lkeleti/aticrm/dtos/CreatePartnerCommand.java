package dev.lkeleti.aticrm.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreatePartnerCommand {

    @NotBlank
    public String name;

    @NotBlank
    public String phone;

    public String city;
}
