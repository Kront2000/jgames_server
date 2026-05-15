package jgames_server.jgames_sever.repository;

import jgames_server.jgames_sever.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByName(String username);
}
