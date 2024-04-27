package org.example.gestionchampionnatapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.example.gestionchampionnatapi.repository.GameRepository;

import java.util.List;

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
    @JoinColumn(name = "idTeam1", referencedColumnName = "id")
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "idTeam2", referencedColumnName = "id")
    private Team team2;

    @ManyToOne
    @JoinColumn(name = "idDay", referencedColumnName = "id")
    private Day day;

    // Determination liste des resultats :
    @ManyToOne
    private ChampionShip championShip;

    @ManyToOne
    @JoinColumn(name = "winningTeamId", referencedColumnName = "id")
    private Team winningTeam;

    public void determineWinner() {
        ChampionShip championshipDay = day.getChampionship(); // Supposons que day contient la journée associée au match

        Long winPoint = championshipDay.getWonPoint();
        Long lostPoint = championshipDay.getLostPoint();
        Long drawPoint = championshipDay.getDrawPoint();

        if (team1Point > team2Point) {
            winningTeam = team1;
            team1.addPoints(winPoint); // Ajoute les points de victoire à l'équipe gagnante
            team2.addPoints(lostPoint); // Ajoute les points de défaite à l'équipe perdante
        } else if (team1Point < team2Point) {
            winningTeam = team2;
            team1.addPoints(winPoint); // Ajoute les points de défaite à l'équipe perdante
            team2.addPoints(winPoint); // Ajoute les points de victoire à l'équipe gagnante
        } else {
            winningTeam = null; // Match nul, aucune équipe n'a de point bonus
            team1.addPoints(drawPoint); // Ajoute les points de match nul à chaque équipe
            team2.addPoints(drawPoint);
        }
    }
    // fin determination liste des résultats

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