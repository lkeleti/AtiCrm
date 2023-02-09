package dev.lkeleti.aticrm.dtos;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ModifyEventCommand {

    @NonNull
    public LocalDateTime eventDate;
    public String comment;
}
