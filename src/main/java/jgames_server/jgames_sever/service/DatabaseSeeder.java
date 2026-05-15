package jgames_server.jgames_sever.service;

import jgames_server.jgames_sever.models.Game;
import jgames_server.jgames_sever.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final GameRepository gameRepository;

    @Override
    public void run(String... args) {
        List<String> gameNames = List.of("Breakout", "FlappyBird", "Snake", "Pacman", "Minesweeper");

        gameNames.forEach(name -> {
            if (gameRepository.findByName(name).isEmpty()) {
                gameRepository.save(Game.builder().name(name).build());
            }
        });

        System.out.println(">>> Проверка списка игр завершена.");
    }
}
