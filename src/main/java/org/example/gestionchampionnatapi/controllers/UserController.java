package org.example.gestionchampionnatapi.controllers;

import jakarta.validation.Valid;
import org.example.gestionchampionnatapi.models.User;
import org.example.gestionchampionnatapi.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // Get users
    @GetMapping("/")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    // Get user
    @GetMapping("/{idUser}")
    public User getUser(@PathVariable Long idUser){
        Optional<User> user = userRepository.findById(idUser);
        return user.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));
    }

    // Get user by email and password
    @GetMapping("/connect/{email}/{password}")
    public User getUserByEmailPassword(@PathVariable String email, @PathVariable String password){
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        return user.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Identifiant ou mot de passe incorrect"));
    }


    // Create user
    @PostMapping("/")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        } else {
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
    }

    // Update user
    @PutMapping("/{user}")
    public ResponseEntity<User> updateUser(@PathVariable(name="user", required = false) User user, @Valid @RequestBody User userUpdate, BindingResult bindingResult){
        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable");
        } else {
            if(bindingResult.hasErrors()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
            } else {
                userUpdate.setId(user.getId());
                userRepository.save(userUpdate);
                return new ResponseEntity<>(userUpdate, HttpStatus.OK);
            }
        }
    }

    // Delete user
    @DeleteMapping("/{user}")
    public void deleteOne(@PathVariable(name="user", required = false) User user){
        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable");
        } else {
            userRepository.delete(user);
        }
    }
}
