package jgames_server.jgames_sever.controller;

import jgames_server.jgames_sever.models.Player;
import jgames_server.jgames_sever.repository.PlayerRepository;
import jgames_server.jgames_sever.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final PlayerRepository playerRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public static class AuthRequest {
        public String username;
        public String password;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest req) {
        if (playerRepository.findByName(req.username).isPresent()) {
            return ResponseEntity.badRequest().body("Пользователь уже существует!");
        }
        Player player = new Player();
        player.setName(req.username);
        player.setPassword(passwordEncoder.encode(req.password));
        playerRepository.save(player);
        String token = jwtService.generateToken(player.getName());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest req) {
        return playerRepository.findByName(req.username)
                .filter(player -> passwordEncoder.matches(req.password, player.getPassword()))
                .map(player -> ResponseEntity.ok(jwtService.generateToken(player.getName())))
                .orElse(ResponseEntity.status(401).body("Bad credentials"));
    }
}
