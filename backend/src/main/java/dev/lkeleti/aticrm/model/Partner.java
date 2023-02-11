package dev.lkeleti.aticrm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "partners")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotBlank
    public String name;
    @NotBlank
    public String phone;
    public String city;

    @OneToMany(mappedBy = "partner")
    private List<Event> events = new ArrayList<>();

    public Partner(String name, String phone, String city) {
        this.name = name;
        this.phone = phone;
        this.city = city;
    }
}
