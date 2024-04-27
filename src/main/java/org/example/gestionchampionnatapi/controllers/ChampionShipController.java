package org.example.gestionchampionnatapi.controllers;

import jakarta.validation.Valid;
import org.example.gestionchampionnatapi.models.ChampionShip;
import org.example.gestionchampionnatapi.repository.ChampionshipRepository;
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
    private ChampionshipRepository championShipRepository;

    public ChampionShipController(ChampionshipRepository championShipRepository){
        this.championShipRepository = championShipRepository;
    }

    // Get all championship
    @GetMapping("/")
    public List<ChampionShip> getChampionShip(){
        return championShipRepository.findAll();
    }

    // Get one championship
    @GetMapping("/{championShip}")
    public ChampionShip getChampionShip(@PathVariable Long id){
        Optional<ChampionShip> championShip = championShipRepository.findById(id);
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
    public void deletechampionShip(@PathVariable(name="championShip", required = false) ChampionShip championShip){
        if(championShip == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Championnat introuvable");
        } else {
            championShipRepository.delete(championShip);
        }
    }
}