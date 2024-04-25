package org.example.gestionchampionnatapi.repository;

import org.example.gestionchampionnatapi.models.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Results {
    List<Game> findAllResults();

    // Méthode pour récupérer la liste des résultats suivant l'ID d'un championnat
    @Query("SELECT g FROM Game g WHERE g.day.championship.id = :championshipId")
    List<Game> findAllByChampionshipId(Long championshipId);

    @Query("SELECT g FROM Game g WHERE g.day.id = :dayId")
    List<Game> findAllByDayId(Long dayId);

    Optional<Game> findById(Long id);



}
