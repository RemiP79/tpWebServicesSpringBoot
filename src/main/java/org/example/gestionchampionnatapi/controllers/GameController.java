package org.example.gestionchampionnatapi.controllers;
import jakarta.validation.Valid;
import org.example.gestionchampionnatapi.models.Day;
import org.example.gestionchampionnatapi.models.Game;
import org.example.gestionchampionnatapi.models.Team;
import org.example.gestionchampionnatapi.repository.GameRepository;
import org.example.gestionchampionnatapi.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class GameController {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private TeamRepository teamRepository;

    // Get games
    @GetMapping("/")
    public List<Game> getGames(){
        return gameRepository.findAll();
    }

    // Get game
    @GetMapping("/{idGame}")
    public Game getGame(@PathVariable Long idGame){
        Optional<Game> game = gameRepository.findById(idGame);
        return game.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));
    }

    // Created game
    @PostMapping("/")
    public ResponseEntity<Game> saveGame(@Valid @RequestBody Game game, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        } else {
            gameRepository.save(game);
            return new ResponseEntity<>(game, HttpStatus.CREATED);
        }
    }

    // Update game
    @PutMapping("/{game}")
    public ResponseEntity<Game> updateGame(@PathVariable(name="game", required = false) Game game, @Valid @RequestBody Game gameUpdate, BindingResult bindingResult){
        if(game == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Résultat introuvable");
        } else {
            if(bindingResult.hasErrors()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
            } else {
                gameUpdate.setId(game.getId());
                gameRepository.save(gameUpdate);
                return new ResponseEntity<>(gameUpdate, HttpStatus.OK);
            }
        }
    }

    // Delete game
    @DeleteMapping("/{game}")
    public void deleteGame(@PathVariable(name="game", required = false) Game game){
        if(game == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Résultat introuvable");
        } else {
            gameRepository.delete(game);
        }
    }

    // Get games by championship
    @GetMapping("/by-championship/{idChampionship}")
    public List<Game> getGamesByChampionShip(@PathVariable Long idChampionship){
        return gameRepository.findGamesByChampionShipId(idChampionship);
    }

    // Get games by day
    @GetMapping("/by-day/{idDay}")
    public List<Game> getGamesByDay(@PathVariable Long idDay){
        return gameRepository.findGamesByDayId(idDay);
    }

    // Endpoint for determining winners and registering games
    @PostMapping("/determine-winners-and-save")
    public ResponseEntity<String> determineWinnersAndSave(@RequestBody GameIdRequest request) {
        List<Long> gameIds = request.getGameIds();
        Iterable<Game> games = gameRepository.findAllById(gameIds);
        for (Game game : games) {
            game.determineWinner();
        }
        gameRepository.saveAll(games);
        return ResponseEntity.ok("Winners determined and games saved successfully.");
    }

    @PostMapping("/determine-winners-and-save/{idChampionship}")
    public ResponseEntity<String> determineWinnersAndSave(@PathVariable Long idChampionship) {
        // Récupérez les identifiants des jeux associés à ce championnat
        List<Long> gameIds = gameRepository.findGameIdsByChampionshipId(idChampionship);
        Iterable<Game> games = gameRepository.findAllById(gameIds);
        for (Game game : games) {
            game.determineWinner();
        }
        gameRepository.saveAll(games);
        return ResponseEntity.ok("Winners determined and games saved successfully.");
    }

    // Endpoint to retrieve team rankings
    @GetMapping("/team-ranking")
    public ResponseEntity<List<Team>> getTeamRanking() {
        List<Team> teams = teamRepository.findAll();// récupérez toutes les équipes depuis la base de données
                // Tri des équipes en fonction de leurs points (par exemple)
                teams.sort(Comparator.comparingLong(Team::getPoints).reversed());
        return ResponseEntity.ok(teams);
    }
}

