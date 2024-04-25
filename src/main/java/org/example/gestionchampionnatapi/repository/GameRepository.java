package org.example.gestionchampionnatapi.repository;

import org.example.gestionchampionnatapi.models.Championship;
import org.example.gestionchampionnatapi.models.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    @Override
    List<Game> findAll();

    @Query("SELECT c FROM Game c WHERE c.team1Point = :team1Point")
    List<Game> getChampionshipFromTeam1Point(Long team1Point);

}

