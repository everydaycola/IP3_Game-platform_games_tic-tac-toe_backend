package be.kdg.ipj3.tictactoebackend.api;


import be.kdg.ipj3.tictactoebackend.api.dtos.GameStateDtoChar;
import be.kdg.ipj3.tictactoebackend.api.dtos.MoveDto;
import be.kdg.ipj3.tictactoebackend.api.dtos.NewAiMatchDto;
import be.kdg.ipj3.tictactoebackend.api.dtos.NewMatchDto;
import be.kdg.ipj3.tictactoebackend.application.GameStateService;
import be.kdg.ipj3.tictactoebackend.domain.GameStateId;
import be.kdg.ipj3.tictactoebackend.domain.CharacterId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/matches")
@Slf4j
public class MatchController {

    private final GameStateService gameStateService;

    public MatchController(GameStateService gameStateService) {
        this.gameStateService = gameStateService;
    }

    @PostMapping("/")
    public ResponseEntity<GameStateDtoChar> startGame(@RequestBody NewMatchDto newMatch){
        log.info("Starting a new game");
        final var player1Id = new CharacterId(newMatch.player1());
        final var player2Id = new CharacterId(newMatch.player2());
        final var state = gameStateService.createBoard(player1Id, player2Id);
        return ResponseEntity.ok(GameStateDtoChar.from(state));
    }

    @PostMapping("/ai")
    public ResponseEntity<GameStateDtoChar> startGame(@RequestBody NewAiMatchDto newMatch){
        log.info("Starting a new game");
        final var playerId = new CharacterId(newMatch.player());
        final var state = gameStateService.createBoardAi(playerId);
        return ResponseEntity.ok(GameStateDtoChar.from(state));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameStateDtoChar> getGame(@PathVariable UUID id){
        log.info("Getting game with id {}", id);
        final var stateId = new GameStateId(id);
        final var state = gameStateService.getState(stateId);
        return ResponseEntity.ok(GameStateDtoChar.from(state));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GameStateDtoChar> place(@RequestBody MoveDto move, @PathVariable UUID id){
        final var stateId = new GameStateId(id);
        final var playerId = new CharacterId(move.player());
        final var state = gameStateService.makeMove(move.x(), move.y(), playerId, stateId);
        return ResponseEntity.ok(GameStateDtoChar.from(state));
    }

}
