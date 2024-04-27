package org.example.gestionchampionnatapi.repository;

import org.example.gestionchampionnatapi.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    @Override
    List<Game> findAll();


    /////test resultats

   // List<Long> findGameIdsByChampionshipId(Long championshipId);

    // List<Game> saveAll(List<Game> games);
    /////////// fin tests
}

