package org.example.gestionchampionnatapi.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le champ team1Point ne peut pas être null")
    private Long team1Point;

    @NotNull(message = "le champs team2Point ne peut pas être null")
    private Long team2Point;

    @ManyToOne
    @JoinColumn(name = "idTeam1", referencedColumnName = "id", insertable = false, updatable = false)
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "idTeam2", referencedColumnName = "id", insertable = false, updatable = false)
    private Team team2;

    @ManyToOne
    @JoinColumn(name = "idDay", referencedColumnName = "id", insertable = false, updatable = false)
    private Day day;

    public Game() {
    }

    public Game(Long id, Long team1Point, Long team2Point, Team team1, Team team2, Day day) {
        this.id = id;
        this.team1Point = team1Point;
        this.team2Point = team2Point;
        this.team1 = team1;
        this.team2 = team2;
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

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}