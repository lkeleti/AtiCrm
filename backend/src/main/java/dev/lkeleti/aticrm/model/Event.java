package dev.lkeleti.aticrm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Long id;

    @Column(name = "event_date")
    public LocalDateTime eventDate;

    public String comment;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    public Partner partner;

    public void addPartner(Partner partner) {
        this.partner = partner;
        partner.getEvents().add(this);
    }

    public Event(LocalDateTime eventDate, String comment) {
        this.eventDate = eventDate;
        this.comment = comment;
    }
}
