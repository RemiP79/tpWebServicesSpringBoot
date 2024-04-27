package org.example.gestionchampionnatapi.controllers;

import jakarta.validation.Valid;
import org.example.gestionchampionnatapi.models.Day;
import org.example.gestionchampionnatapi.repository.DayRepository;
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

    private DayRepository dayRepository;

    public DayController(DayRepository dayRepository){
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

    //lier day à championnat

    // Created
    @PostMapping("/")
    public ResponseEntity<Day> saveDay(@Valid @RequestBody Day Day, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        } else {
            dayRepository.save(Day);
            return new ResponseEntity<>(Day, HttpStatus.CREATED);
        }
    }

    // Update
    @PutMapping("/{day}")
    public ResponseEntity<Day> updateDay(@PathVariable(name="day", required = false) Day Day, @Valid @RequestBody Day DayUpdate, BindingResult bindingResult){
        if(Day == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jour introuvable");
        } else {
            if(bindingResult.hasErrors()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
            } else {
                DayUpdate.setId(Day.getId());
                dayRepository.save(DayUpdate);
                return new ResponseEntity<>(DayUpdate, HttpStatus.OK);
            }
        }
    }

    // Delete
    @DeleteMapping("/{day}")
    public void deleteDay(@PathVariable(name="day", required = false) Day Day){
        if(Day == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jour introuvable");
        } else {
            dayRepository.delete(Day);
        }
    }
}
