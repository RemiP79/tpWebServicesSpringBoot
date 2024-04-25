package org.example.gestionchampionnatapi.repository;

import org.example.gestionchampionnatapi.models.Championship;
import org.example.gestionchampionnatapi.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();

   /* @Query("SELECT c FROM Championship c WHERE c.name = :name")
    List<Championship> getChampionshipFromName(String name);*/

}

