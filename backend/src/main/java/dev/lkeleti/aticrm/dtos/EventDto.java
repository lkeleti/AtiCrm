package dev.lkeleti.aticrm.dtos;

import dev.lkeleti.aticrm.model.Partner;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventDto {

    public Long id;
    public LocalDateTime eventDate;
    public String comment;
    public Partner partner;
}
