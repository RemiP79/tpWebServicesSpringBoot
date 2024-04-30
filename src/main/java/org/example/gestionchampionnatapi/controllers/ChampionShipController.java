package org.example.gestionchampionnatapi.controllers;

import jakarta.validation.Valid;
import org.example.gestionchampionnatapi.models.ChampionShip;
import org.example.gestionchampionnatapi.models.Day;
import org.example.gestionchampionnatapi.repository.ChampionshipRepository;
import org.example.gestionchampionnatapi.repository.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/championships")
public class ChampionShipController {
    @Autowired
    private DayController dayController;
    private ChampionshipRepository championShipRepository;
    private DayRepository dayRepository;

    public ChampionShipController(ChampionshipRepository championShipRepository, DayRepository dayRepository){
        this.championShipRepository = championShipRepository;
        this.dayRepository = dayRepository;
    }

    // Get all championship
    @GetMapping("/")
    public List<ChampionShip> getChampionShip(){
        return championShipRepository.findAll();
    }

    // Get one championship
    @GetMapping("/{idChampionShip}")
    public ChampionShip getChampionShip(@PathVariable Long idChampionShip){
        Optional<ChampionShip> championShip = championShipRepository.findById(idChampionShip);
        return championShip.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Championnat non trouvé"));
    }

    // Create championship
    @PostMapping("/")
    public ResponseEntity<ChampionShip> saveChampionShip(@Valid @RequestBody ChampionShip championShip, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        } else {
            championShipRepository.save(championShip);
            return new ResponseEntity<>(championShip, HttpStatus.CREATED);
        }
    }

    // Update championship
    @PutMapping("/{championShip}")
    public ResponseEntity<ChampionShip> updatechampionShip(@PathVariable(name="championShip", required = false) ChampionShip championShip, @Valid @RequestBody ChampionShip championShipUpdate, BindingResult bindingResult){
        if(championShip == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Championnat introuvable");
        } else {
            if(bindingResult.hasErrors()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
            } else {
                championShipUpdate.setId(championShip.getId());
                championShipRepository.save(championShipUpdate);
                return new ResponseEntity<>(championShipUpdate, HttpStatus.OK);
            }
        }
    }

    // Delete championship
    @DeleteMapping("/{championShip}")
    public ResponseEntity<ChampionShip> deletechampionship(@PathVariable(name="championShip", required = false) ChampionShip championShip){
        if(championShip == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Championnat introuvable");
        } else {
            championShip.getTeams().forEach(team -> team.getChampionShips().remove(championShip));
            List<Day> days = dayRepository.findDayByChampionshipId(championShip.getId());
            days.forEach(day -> dayController.deleteDay(day));
            championShipRepository.delete(championShip);
            return new ResponseEntity<>(championShip, HttpStatus.OK);
        }
    }
}