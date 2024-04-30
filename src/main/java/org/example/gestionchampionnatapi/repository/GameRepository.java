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
    @Query("SELECT g.id FROM Game g WHERE g.day.championship.id = :championshipId")
    List<Long> findGameIdsByChampionshipId(@Param("championshipId") Long championshipId);
    @Query("SELECT g FROM Game g WHERE g.day.championship.id = :championshipId")
    List<Game> findGamesByChampionShipId(@Param("championshipId") Long championshipId);
    List<Game> findGamesByDayId(Long id);
    @Query("SELECT g FROM Game g WHERE g.team1.id = :teamId OR g.team2.id = :teamId")
    List<Game> getGameByTeamId(@Param("teamId") Long teamId);
}

