package jgames_server.jgames_sever.controller;

import jgames_server.jgames_sever.models.Stat;
import jgames_server.jgames_sever.service.JwtService;
import jgames_server.jgames_sever.service.StatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/stat")
public class StatController {

    private final JwtService jwtService;
    private final StatService statService;

    public static class GameStat {
        public String gameName;
        public String stat;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveStat(@RequestBody GameStat stat, @RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            String username = jwtService.extractUsername(token);

            statService.save(username, Long.parseLong(stat.stat), stat.gameName);
            return ResponseEntity.ok("Saved!");
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid Token!");
        }
    }

    @GetMapping("/top/{gameName}")
    public ResponseEntity<List<Stat>> getTop10(@PathVariable String gameName) {
        List<Stat> topPlayers = statService.getStatByGameName(gameName);
        return ResponseEntity.ok(topPlayers);
    }

}
