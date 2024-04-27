package org.example.gestionchampionnatapi.repository;

import org.example.gestionchampionnatapi.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    @Override
    List<Game> findAll();

    /////test resultats
    @Query("SELECT g.id FROM Game g WHERE g.day.championship.id = :championshipId")
    List<Long> findGameIdsByChampionshipId(@Param("championshipId") Long championshipId);

    // List<Long> findGameIdsByChampionshipId(Long championshipId);

    // List<Game> saveAll(List<Game> games);
    /////////// fin tests
}

