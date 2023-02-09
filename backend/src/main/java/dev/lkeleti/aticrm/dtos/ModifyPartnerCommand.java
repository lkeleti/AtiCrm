package dev.lkeleti.aticrm.dtos;

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

        @NotBlank
        public String name;

        @NotBlank
        public String phone;

        public String city;
}
