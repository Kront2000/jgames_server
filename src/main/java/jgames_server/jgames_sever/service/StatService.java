package jgames_server.jgames_sever.service;

import jgames_server.jgames_sever.models.Game;
import jgames_server.jgames_sever.models.Player;
import jgames_server.jgames_sever.models.Stat;
import jgames_server.jgames_sever.repository.GameRepository;
import jgames_server.jgames_sever.repository.PlayerRepository;
import jgames_server.jgames_sever.repository.StatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StatService {
    private final StatRepository statRepository;
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    public boolean save(String playerName, Long statValue, String gameName) {
        Player player = playerRepository.findByName(playerName).orElse(null);
        Game game = gameRepository.findByName(gameName).orElse(null);

        if (player == null || game == null) return false;

        Optional<Stat> existingRecord = statRepository.findByPlayerNameAndGameName(playerName, gameName);

        if (existingRecord.isEmpty()) {
            Stat newStat = Stat.builder()
                    .game(game)
                    .player(player)
                    .stat(statValue)
                    .build();
            statRepository.save(newStat);
        } else {
            Stat current = existingRecord.get();
            if (statValue > current.getStat()) {
                current.setStat(statValue);
                statRepository.save(current);
            }
        }
        return true;
    }

    public Long getStatByGameNameAndPlayerName(String gameName, String playerName){
        Stat stat = statRepository.findByPlayerNameAndGameName(playerName, gameName).orElse(null);
        return stat == null ? 0 : stat.getStat();
    }

    public List<Stat> getStatByGameName(String gameName){
        List<Stat> stats = statRepository.findTop10ByGameNameOrderByStatDesc(gameName);
        return stats;
    }
}
