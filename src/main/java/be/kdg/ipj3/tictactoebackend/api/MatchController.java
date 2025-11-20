package be.kdg.ipj3.tictactoebackend.api;


import be.kdg.ipj3.tictactoebackend.api.dtos.GameStateDtoChar;
import be.kdg.ipj3.tictactoebackend.application.GameStateApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/matches")
@Slf4j
public class MatchController {

    private final GameStateApplication gameStateApplication;

    public MatchController(GameStateApplication gameStateApplication) {
        this.gameStateApplication = gameStateApplication;
    }

    @PostMapping("/")
    public ResponseEntity<GameStateDtoChar> startGame(){
        log.info("Starting a new game");
        final var state = gameStateApplication.createBoard();
        return ResponseEntity.ok(GameStateDtoChar.from(state));
    }

}
