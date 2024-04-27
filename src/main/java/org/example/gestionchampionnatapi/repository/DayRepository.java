package org.example.gestionchampionnatapi.repository;

import org.example.gestionchampionnatapi.models.ChampionShip;
import org.example.gestionchampionnatapi.models.Day;
import org.example.gestionchampionnatapi.models.Team;
import org.example.gestionchampionnatapi.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DayRepository extends CrudRepository<Day, Long> {
    @Override
    List<Day> findAll();

    Optional<Day> findById(Long id);
    
    List<Day> findDayByChampionshipId(Long id);

}

