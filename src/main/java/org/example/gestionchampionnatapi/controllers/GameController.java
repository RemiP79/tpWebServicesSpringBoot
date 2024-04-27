package org.example.gestionchampionnatapi.controllers;
import org.example.gestionchampionnatapi.models.Game;
import org.example.gestionchampionnatapi.models.Team;
import org.example.gestionchampionnatapi.repository.GameRepository;
import org.example.gestionchampionnatapi.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private TeamRepository teamRepository;

    // Endpoint pour déterminer les gagnants et enregistrer les jeux
        @PostMapping("/determinewinnersandsave")
        public ResponseEntity<String> determineWinnersAndSave(@RequestBody GameIdRequest request) {
            List<Long> gameIds = request.getGameIds();
            Iterable<Game> games = gameRepository.findAllById(gameIds);
            for (Game game : games) {
                game.determineWinner();
            }
            gameRepository.saveAll(games);
            return ResponseEntity.ok("Winners determined and games saved successfully.");
        }

    /*@PostMapping("/determinewinnersandsave/{championshipId}")
    public ResponseEntity<String> determineWinnersAndSave(@PathVariable Long championshipId) {
        // Récupérez les identifiants des jeux associés à ce championnat
        List<Long> gameIds = gameRepository.findGameIdsByChampionShipId(championshipId);
        Iterable<Game> games = gameRepository.findAllById(gameIds);
        for (Game game : games) {
            game.determineWinner();
        }
        gameRepository.saveAll(games);
        return ResponseEntity.ok("Winners determined and games saved successfully.");
    }*/


    // Endpoint pour récupérer le classement des équipes
        @GetMapping("/teamranking")
        public ResponseEntity<List<Team>> getTeamRanking() {
            List<Team> teams = teamRepository.findAll();// récupérez toutes les équipes depuis la base de données
                    // Tri des équipes en fonction de leurs points (par exemple)
                    teams.sort(Comparator.comparingLong(Team::getPoints).reversed());
            return ResponseEntity.ok(teams);
        }






   /* @Autowired
    private GameRepository gameRepository;

   @PostMapping("/determinewinnersandsave")
    public ResponseEntity<String> determineWinnersAndSave(@RequestParam List<Long> gameIds) {
        Iterable<Game> games = gameRepository.findAllById(gameIds);
        for (Game game : games) {
            game.determineWinner();
        }
        gameRepository.saveAll(games);
        return ResponseEntity.ok("Winners determined and games saved successfully.");
    }*/


}

