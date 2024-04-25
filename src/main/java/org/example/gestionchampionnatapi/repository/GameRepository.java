package org.example.gestionchampionnatapi.repository;

import org.example.gestionchampionnatapi.models.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    @Override
    List<Game> findAll();

}

