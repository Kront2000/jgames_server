package jgames_server.jgames_sever.repository;

import jgames_server.jgames_sever.models.Stat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StatRepository extends JpaRepository<Stat, Long> {
    List<Stat> findTop10ByGameNameOrderByStatDesc(String name);
    Optional<Stat> findByPlayerNameAndGameName(String playerName, String gameName);
    List<Stat> findByGameNameOrderByStatDesc(String gameName);
}