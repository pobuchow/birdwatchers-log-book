package info.pobu.blb.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Entity
public class Observation {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
//    @ElementCollection(targetClass = Species.class)
    @Enumerated(EnumType.STRING)
    @Getter
    private Species species;

    @NotNull
    @Getter
    private String location;

    @NotNull
    @Getter
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter
    private User user;

    public Observation(User user, Species species, String location, LocalDate date) {
        this.user = user;
        this.species = species;
        this.location = location;
        this.date = date;
    }

    public Observation() {

    }
}