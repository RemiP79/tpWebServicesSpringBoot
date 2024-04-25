package org.example.gestionchampionnatapi.repository;

import org.example.gestionchampionnatapi.models.Championship;
import org.example.gestionchampionnatapi.models.Day;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayRepository extends CrudRepository<Day, Long> {
    @Override
    List<Day> findAll();

    @Query("SELECT d FROM Day d WHERE d.number = :name")
    List<Championship> getChampionshipFromName(String name);
    
}

