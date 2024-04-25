package org.example.gestionchampionnatapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Day {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message= "Le champs number ne peut être null")
    @NotBlank(message = "le champs number ne peut pas être vide")
    private String number;

    @ManyToOne // Relation many-to-one avec Championship
    @JoinColumn(name = "idChampionship", referencedColumnName = "id")
    private ChampionShip championship;

    public Day(){
    }

    public Day(Long id, String number, ChampionShip championship) {
        this.id = id;
        this.number = number;
        this.championship = championship;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ChampionShip getChampionship() {
        return championship;
    }

    public void setChampionship(ChampionShip championship) {
        this.championship = championship;
    }
}
