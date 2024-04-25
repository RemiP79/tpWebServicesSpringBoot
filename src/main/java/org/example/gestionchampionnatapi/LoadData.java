package org.example.gestionchampionnatapi;

import org.example.gestionchampionnatapi.models.Championship;
import org.example.gestionchampionnatapi.models.Day;
import org.example.gestionchampionnatapi.models.Game;
import org.example.gestionchampionnatapi.models.Team;
import org.example.gestionchampionnatapi.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class LoadData {
    private final Logger log = LoggerFactory.getLogger(LoadData.class);

    @Bean
    CommandLineRunner initDatabase(ChampionshipRepository championshipRepository,
                                   DayRepository dayRepository,
                                   GameRepository gameRepository,
                                   TeamRepository teamRepository,
                                   UserRepository userRepository,
                                   TeamChampionShipRepository teamChampionShipRepository) {

        log.info("Chargement des données");

        return args -> {

            if (championshipRepository.count() == 0
                    && dayRepository.count()==0
                    && gameRepository.count()==0) {
                LocalDate dateDebutChampionnat1 = LocalDate.parse("2023-06-05");
                LocalDate dateFinChampionnat1 = LocalDate.parse("2023-06-20");

                Championship championship = new Championship(null, "Championnat1",
                        dateDebutChampionnat1, dateFinChampionnat1, 2L, 0L, 1L);

                // Sauvegarde le championnat dans la base de données
                championship = championshipRepository.save(championship);
                log.info("Chargement de " + championship);

                Day day1 = new Day(null, "1",championship);
                day1 = dayRepository.save(day1);
                log.info("Chargement de " + day1);

                Team team1 = new Team("Team1", LocalDate.parse("2012-08-05"));
                team1 = teamRepository.save(team1);
                log.info("Chargement de " + team1);

                Team team2 = new Team("Team2", LocalDate.parse("2006-11-12"));
                team2 = teamRepository.save(team2);
                log.info("Chargement de " + team2);

                Game game1 = new Game(null,12L, 10L,team1,team2,day1);
                game1 = gameRepository.save(game1);
                log.info("Chargement de " + game1);

            } else {
                log.info("Données déjà chargées");
            }
        };
    }




}
