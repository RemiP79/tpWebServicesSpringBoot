package org.example.gestionchampionnatapi.controllers;

import jakarta.validation.Valid;
import org.example.gestionchampionnatapi.models.ChampionShip;
import org.example.gestionchampionnatapi.models.Day;
import org.example.gestionchampionnatapi.models.Game;
import org.example.gestionchampionnatapi.models.Team;
import org.example.gestionchampionnatapi.repository.ChampionshipRepository;
import org.example.gestionchampionnatapi.repository.DayRepository;
import org.example.gestionchampionnatapi.repository.GameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/days")
public class DayController {

    private ChampionshipRepository championshipRepository;
    private GameRepository gameRepository;
    private DayRepository dayRepository;

    public DayController(DayRepository dayRepository, ChampionshipRepository championshipRepository, GameRepository gameRepository){
        this.championshipRepository = championshipRepository;
        this.gameRepository = gameRepository;
        this.dayRepository = dayRepository;
    }

    // Get All
    @GetMapping("/")
    public List<Day> getDays(){
        return dayRepository.findAll();
    }

    // Get One
    @GetMapping("/{idDay}")
    public Day getDay(@PathVariable Long idDay){
        Optional<Day> day = dayRepository.findById(idDay);
        return day.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));
    }

    // Get
    @GetMapping("/championship/{idChampionship}")
    public Object getDaysByChampionship(@PathVariable Long idChampionship){
        List<Day> days = dayRepository.findDayByChampionshipId(idChampionship);
        return days.isEmpty() ? new ResponseStatusException(HttpStatus.NOT_FOUND,"Le championnant renseigné n'existe pas") : days;
    }

    // Bind day to championship
    @GetMapping("/bind-day-to-championship/{idDay}/{idChampionship}")
    public ResponseEntity<Day> bindDayToChampionship(@PathVariable Long idDay, @PathVariable Long idChampionship){
        Day day = dayRepository.findById(idDay).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"L'identifiant du jour est incorrect"));
        ChampionShip championShip = championshipRepository.findById(idChampionship).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "L'identifiant du championnat est incorrect"));
        day.bindDayToChampionship(championShip);
        dayRepository.save(day);
        return new ResponseEntity<>(day, HttpStatus.OK);
    }

    // Unbind day to championship
    @GetMapping("/bind-day-to-championship/{idDay}")
    public ResponseEntity<Day> bindDayToChampionship(@PathVariable Long idDay){
        Day day = dayRepository.findById(idDay).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"L'identifiant du jour est incorrect"));
        day.unbindDayToChampionship();
        dayRepository.save(day);
        return new ResponseEntity<>(day, HttpStatus.OK);
    }

    // Created
    @PostMapping("/")
    public ResponseEntity<Day> saveDay(@Valid @RequestBody Day day, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        } else {
            dayRepository.save(day);
            return new ResponseEntity<>(day, HttpStatus.CREATED);
        }
    }

    // Update
    @PutMapping("/{day}")
    public ResponseEntity<Day> updateDay(@PathVariable(name="day", required = false) Day day, @Valid @RequestBody Day dayUpdate, BindingResult bindingResult){
        if(day == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jour introuvable");
        } else {
            if(bindingResult.hasErrors()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
            } else {
                dayUpdate.setId(day.getId());
                dayRepository.save(dayUpdate);
                return new ResponseEntity<>(dayUpdate, HttpStatus.OK);
            }
        }
    }

    // Delete
    @DeleteMapping("/{day}")
    public void deleteDay(@PathVariable(name="day", required = false) Day day){
        if(day == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jour introuvable");
        } else {
            List<Game> games = gameRepository.findGamesByDayId(day.getId());
            games.forEach(gameRepository::delete);
            dayRepository.delete(day);
        }
    }
}
