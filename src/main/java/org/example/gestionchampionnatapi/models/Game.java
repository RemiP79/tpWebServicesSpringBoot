package org.example.gestionchampionnatapi.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
public class Game {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull (message = "Le champ team1Point ne peut pas être null")
    private Long team1Point;
    @NotNull (message = "le champs team2Point ne peut pas être null")
    private Long team2Point;

    //@ManyToOne // Relation many-to-one avec Championship
    //@JoinColumn(name = "idChampionship", referencedColumnName = "id", insertable = false, updatable = false)

    @ManyToOne
    @JoinColumn (name = "idTeam1", referencedColumnName = "id", insertable = false, updatable = false)
    private Team team;

    @ManyToOne
    @JoinColumn (name = "idTeam2", referencedColumnName = "id", insertable = false, updatable = false)
    private Team team;

    @ManyToOne
    @JoinColumn (name = "idDay", referencedColumnName = "id", insertable = false,updatable = false)
    private Day day;


    public Game(){
    }

    public Game(Long id, Long team1Point, Long team2Point, Team team, Team team1, Day day) {
        this.id = id;
        this.team1Point = team1Point;
        this.team2Point = team2Point;
        this.team = team;
        this.team = team1;
        this.day = day;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeam1Point() {
        return team1Point;
    }

    public void setTeam1Point(Long team1Point) {
        this.team1Point = team1Point;
    }

    public Long getTeam2Point() {
        return team2Point;
    }

    public void setTeam2Point(Long team2Point) {
        this.team2Point = team2Point;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
