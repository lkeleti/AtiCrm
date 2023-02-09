package dev.lkeleti.aticrm.dtos;

import dev.lkeleti.aticrm.model.Event;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PartnerDto {

    public Long id;

    public String name;

    public String phone;

    public String city;

    private List<Event> events = new ArrayList<>();
}
