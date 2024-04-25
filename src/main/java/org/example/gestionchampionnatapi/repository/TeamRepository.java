package org.example.gestionchampionnatapi.repository;

import org.example.gestionchampionnatapi.models.Championship;
import org.example.gestionchampionnatapi.models.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
    @Override
    List<Team> findAll();

    /*@Query("SELECT c FROM Championship c WHERE c.name = :name")
    List<Championship> getChampionshipFromName(String name);*/

}

