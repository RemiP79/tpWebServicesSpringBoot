package org.example.gestionchampionnatapi.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
        import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le champ name ne peut pas être null")
    @NotBlank(message = "Le champ name ne peut pas être vide")
    private String name;

    @NotNull(message = "Le champ creationDate ne peut pas être null")
    @Temporal(value = TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    // List championnats
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "teams")
    private Set<ChampionShip> championShips = new HashSet<>();


    ////////////////////////////:
    // test calcul resultats :

    private int points;


    public void addPoints(Long pointsToAdd) {
        points += pointsToAdd;
    }
    public int getPoints() {
        return this.points;
    }




    // fin test calcul resultat
    ////////////////////////////////////////



    public Team() {
    }

    public Team(String name, LocalDate creationDate) {
        this.name = name;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Set<ChampionShip> getChampionShips() {
        return championShips;
    }

    public void setChampionShips(Set<ChampionShip> championShips) {
        this.championShips = championShips;
    }
}
