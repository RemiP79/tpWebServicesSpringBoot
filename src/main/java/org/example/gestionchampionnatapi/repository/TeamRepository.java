package org.example.gestionchampionnatapi.repository;

import org.example.gestionchampionnatapi.models.ChampionShip;
import org.example.gestionchampionnatapi.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
    public interface TeamRepository extends CrudRepository<Team, Long> {
        @Override
        List<Team> findAll();
        @Override


        Optional<Team> findById(Long id);

        List<Team> findTeamByChampionShipsId(Long id);


        //findteambychampionship
        //faire insert



    }

    /*@Query("SELECT c FROM Championship c WHERE c.name = :name")
    List<Championship> getChampionshipFromName(String name);*/



