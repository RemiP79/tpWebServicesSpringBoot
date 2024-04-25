package org.example.gestionchampionnatapi;

import org.example.gestionchampionnatapi.models.*;
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
                                   UserRepository userRepository
                                  ) {

        log.info("Chargement des données");

        return args -> {

            if (championshipRepository.count() == 0
                    && dayRepository.count()==0
                    && gameRepository.count()==0
                    && teamRepository.count()==0
                    && userRepository.count()==0) {


                User user1 = new User("Boby","Lapointe","user1@championshipuser.com","passwordUser1",LocalDate.parse("1971-06-05") );
                user1 = userRepository.save(user1);
                log.info("Chargement de " + user1);

                User user2 = new User("Bibou","Lehibou","user2@championshipuser.com","passwordUser2",LocalDate.parse("1972-06-05") );
                user2 = userRepository.save(user2);
                log.info("Chargement de " + user2);

                User user3 = new User("Patrice","Leuviss","user3@championshipuser.com","passwordUser3",LocalDate.parse("1973-06-05") );
                user3 = userRepository.save(user3);
                log.info("Chargement de " + user3);

                User user4 = new User("Sylvestre","Stolaine","user4@championshipuser.com","passwordUser4",LocalDate.parse("1974-06-05") );
                user4 = userRepository.save(user4);
                log.info("Chargement de " + user4);



                LocalDate dateDebutChampionnat1 = LocalDate.parse("2023-06-05");
                LocalDate dateFinChampionnat1 = LocalDate.parse("2023-06-20");
                ChampionShip championShip1 = new ChampionShip(null, "Championnat1",
                        dateDebutChampionnat1, dateFinChampionnat1, 2L, 0L, 1L);
                // Sauvegarde le championnat dans la base de données
                championShip1 = championshipRepository.save(championShip1);
                log.info("Chargement de " + championShip1);

                Day day1 = new Day(null, "1", championShip1);
                day1 = dayRepository.save(day1);
                log.info("Chargement de " + day1);

                Day day2 = new Day(null, "2", championShip1);
                day2 = dayRepository.save(day2);
                log.info("Chargement de " + day2);

                Day day3 = new Day(null, "3", championShip1);
                day3 = dayRepository.save(day3);
                log.info("Chargement de " + day3);

                Day day4 = new Day(null, "4", championShip1);
                day4 = dayRepository.save(day4);
                log.info("Chargement de " + day4);

                Day day5 = new Day(null, "5", championShip1);
                day5 = dayRepository.save(day5);
                log.info("Chargement de " + day5);

                Team team1 = new Team("Team1", LocalDate.parse("2012-08-05"));
                team1 = teamRepository.save(team1);
                log.info("Chargement de " + team1);

                Team team2 = new Team("Team2", LocalDate.parse("2006-11-12"));
                team2 = teamRepository.save(team2);
                log.info("Chargement de " + team2);

                Team team3 = new Team("Team3", LocalDate.parse("2012-04-02"));
                team3 = teamRepository.save(team3);
                log.info("Chargement de " + team3);

                Team team4 = new Team("Team4", LocalDate.parse("2004-08-05"));
                team4 = teamRepository.save(team4);
                log.info("Chargement de " + team4);

                Team team5 = new Team("Team5", LocalDate.parse("2007-06-05"));
                team5 = teamRepository.save(team5);
                log.info("Chargement de " + team5);

                Team team6 = new Team("Team6", LocalDate.parse("2019-08-05"));
                team6 = teamRepository.save(team6);
                log.info("Chargement de " + team6);


                championShip1.addTeam(team1);
                championShip1.addTeam(team2);
                championShip1.addTeam(team3);
                championShip1.addTeam(team4);
                championShip1.addTeam(team5);
                championShip1.addTeam(team6);
                championshipRepository.save(championShip1);

                Game game1 = new Game(null,4L, 3L,team2,team1,day1);
                game1 = gameRepository.save(game1);
                log.info("Chargement de " + game1);

                Game game2 = new Game(null,8L, 4L,team4,team3,day1);
                game2 = gameRepository.save(game2);
                log.info("Chargement de " + game2);

                Game game3 = new Game(null,7L, 2L,team6,team5,day1);
                game3 = gameRepository.save(game3);
                log.info("Chargement de " + game3);


                Game game4 = new Game(null,5L, 5L,team3,team1,day2);
                game4 = gameRepository.save(game4);
                log.info("Chargement de " + game4);

                Game game5 = new Game(null,7L, 9L,team6,team2,day2);
                game5 = gameRepository.save(game5);
                log.info("Chargement de " + game5);

                Game game6 = new Game(null,10L, 11L,team5,team4,day2);
                game6 = gameRepository.save(game6);
                log.info("Chargement de " + game6);


                Game game7 = new Game(null,8L, 4L,team4,team1,day3);
                game7 = gameRepository.save(game7);
                log.info("Chargement de " + game7);

                Game game8 = new Game(null,7L, 2L,team5,team2,day3);
                game8 = gameRepository.save(game8);
                log.info("Chargement de " + game8);

                Game game9 = new Game(null,4L, 3L,team6,team3,day3);
                game9 = gameRepository.save(game9);
                log.info("Chargement de " + game9);


                Game game10 = new Game(null,7L, 2L,team5,team1,day4);
                game10 = gameRepository.save(game10);
                log.info("Chargement de " + game10);

                Game game11 = new Game(null,7L, 9L,team3,team2,day4);
                game11 = gameRepository.save(game11);
                log.info("Chargement de " + game11);

                Game game12 = new Game(null,7L, 2L,team6,team4,day4);
                game12 = gameRepository.save(game12);
                log.info("Chargement de " + game12);


                Game game13 = new Game(null,8L, 4L,team6,team1,day5);
                game13 = gameRepository.save(game13);
                log.info("Chargement de " + game13);

                Game game14 = new Game(null,4L, 3L,team4,team2,day5);
                game14 = gameRepository.save(game14);
                log.info("Chargement de " + game14);

                Game game15 = new Game(null,7L, 2L,team5,team3,day5);
                game15 = gameRepository.save(game15);
                log.info("Chargement de " + game15);

/*
                TeamChampionShip team1ChampionShip1 = new TeamChampionShip(team1, championShip1);
                team1ChampionShip1 = teamChampionShipRepository.save(team1ChampionShip1);
                log.info("Chargement de " + team1ChampionShip1);

                TeamChampionShip team2ChampionShip1 = new TeamChampionShip(team2, championShip1);
                team2ChampionShip1 = teamChampionShipRepository.save(team2ChampionShip1);
                log.info("Chargement de " + team2ChampionShip1);

                TeamChampionShip team3ChampionShip1 = new TeamChampionShip(team3, championShip1);
                team3ChampionShip1 = teamChampionShipRepository.save(team3ChampionShip1);
                log.info("Chargement de " + team3ChampionShip1);

                TeamChampionShip team4ChampionShip1 = new TeamChampionShip(team4, championShip1);
                team4ChampionShip1 = teamChampionShipRepository.save(team4ChampionShip1);
                log.info("Chargement de " + team4ChampionShip1);

                TeamChampionShip team5ChampionShip1 = new TeamChampionShip(team5, championShip1);
                team5ChampionShip1 = teamChampionShipRepository.save(team5ChampionShip1);
                log.info("Chargement de " + team5ChampionShip1);

                TeamChampionShip team6ChampionShip1 = new TeamChampionShip(team6, championShip1);
                team6ChampionShip1 = teamChampionShipRepository.save(team6ChampionShip1);
                log.info("Chargement de " + team6ChampionShip1);
*/
            } else {
                log.info("Données déjà chargées");
            }
        };
    }




}
