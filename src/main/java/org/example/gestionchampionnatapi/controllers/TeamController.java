package org.example.gestionchampionnatapi.controllers;

import jakarta.validation.Valid;
import org.example.gestionchampionnatapi.models.ChampionShip;
import org.example.gestionchampionnatapi.models.Team;
import org.example.gestionchampionnatapi.repository.ChampionshipRepository;
import org.example.gestionchampionnatapi.repository.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/teams")
public class TeamController {
    private ChampionshipRepository championShipRepository;
    private TeamRepository teamRepository;

    public TeamController(ChampionshipRepository championShipRepository, TeamRepository teamRepository){
        this.championShipRepository = championShipRepository;
        this.teamRepository = teamRepository;
    }

    // Get all teams
    @GetMapping("/")
    public List<Team> getAll(){
        return teamRepository.findAll();
    }

    // Get one team
    @GetMapping("/{idTeam}")
    public Team getOne(@PathVariable Long idTeam){
        Optional<Team> team = teamRepository.findById(idTeam);
        return team.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipe non trouvé"));
    }

    // Get teams by championship
    @GetMapping("/championship/{idChampionShip}")
    public Object getTeamsByChampionId(@PathVariable Long idChampionShip){
        List<Team> teams = teamRepository.findTeamByChampionShipsId(idChampionShip);
        return teams.isEmpty() ? new ResponseStatusException(HttpStatus.NOT_FOUND, "Le championnant renseigné n'existe pas ou ne contient aucune équipe") : teams;
    }

    // Add team to championship
    @GetMapping("/add-team-to-chompionship/{idTeam}/{idChampionShip}")
    public ResponseEntity<Team> addTeamToChampionShip(@PathVariable Long idTeam, @PathVariable Long idChampionShip){
        Team team = teamRepository.findById(idTeam).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"L'identifiant de l'équipe est incorrect"));
        ChampionShip championShip = championShipRepository.findById(idChampionShip).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "L'identifiant du championnat est incorrect"));
        championShip.addTeam(team);
        championShipRepository.save(championShip);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    // remove team to championship
    @GetMapping("/remove-team-to-chompionship/{idTeam}/{idChampionShip}")
    public ResponseEntity<Object> removeTeamToChampionShip(@PathVariable Long idTeam, @PathVariable Long idChampionShip){
        teamRepository.findById(idTeam).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"L'identifiant de l'équipe est incorrect"));
        ChampionShip championShip = championShipRepository.findById(idChampionShip).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "L'identifiant du championnat est incorrect"));
        championShip.removeTeam(idTeam);
        championShipRepository.save(championShip);
        return new ResponseEntity<>(championShip, HttpStatus.OK);
    }

    // Create user
    @PostMapping("/")
    public ResponseEntity<Team> saveTeam(@Valid @RequestBody Team team, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        } else {
            teamRepository.save(team);
            return new ResponseEntity<>(team, HttpStatus.CREATED);
        }
    }

    // Update user
    @PutMapping("/{team}")
    public ResponseEntity<Team> updateTeam(@PathVariable(name="team", required = false) Team team, @Valid @RequestBody Team teamUpdate, BindingResult bindingResult){
        if(team == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipe introuvable");
        } else {
            if(bindingResult.hasErrors()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
            } else {
                teamUpdate.setId(team.getId());
                teamRepository.save(team);
                return new ResponseEntity<>(teamUpdate, HttpStatus.OK);
            }
        }
    }

    // Delete user
    @DeleteMapping("/{team}")
    public void deleteTeam(@PathVariable(name="team", required = false) Team team){
        if(team == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipe introuvable");
        } else {
            teamRepository.delete(team);
        }
    }
}
