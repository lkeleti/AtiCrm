package dev.lkeleti.aticrm.dtos;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateEventCommand {

    @NonNull
    private Long partnerId;

    @NonNull
    private LocalDateTime eventDate;

    private String comment;
}
