package org.example.gestionchampionnatapi.models;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
public class TeamChampionShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idTeam", referencedColumnName = "id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "idChampionship", referencedColumnName = "id")
    private Championship championship;

    public TeamChampionShip() {
    }

    public TeamChampionShip(Team team, Championship championship) {
        this.team = team;
        this.championship = championship;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }
}
