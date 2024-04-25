package org.example.gestionchampionnatapi.repository;
import org.example.gestionchampionnatapi.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    Optional<User> findById(Long id);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    Optional<User> findByEmailAndPassword(String email, String password);

    }




