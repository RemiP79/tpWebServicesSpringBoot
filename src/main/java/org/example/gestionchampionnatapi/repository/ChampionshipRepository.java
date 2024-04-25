package org.example.gestionchampionnatapi.repository;

import org.example.gestionchampionnatapi.models.ChampionShip;
import org.example.gestionchampionnatapi.models.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChampionshipRepository extends CrudRepository<ChampionShip, Integer> {
    @Override
    List<ChampionShip> findAll();
    Optional<ChampionShip> findById(Long id);

    @Query("SELECT c FROM ChampionShip c WHERE c.name = :name")
    List<ChampionShip> getChampionshipFromName(String name);

}

