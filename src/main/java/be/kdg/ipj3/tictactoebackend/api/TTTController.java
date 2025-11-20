package be.kdg.ipj3.tictactoebackend.api;


import be.kdg.ipj3.tictactoebackend.api.dtos.BoardDto;
import be.kdg.ipj3.tictactoebackend.application.TTTApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/matches")
@Slf4j
public class TTTController {

    private final TTTApplication application;

    public TTTController(TTTApplication application) {
        this.application = application;
    }

    @PostMapping("/")
    public ResponseEntity<BoardDto> startGame(){
        log.info("Starting a new game");
        final var state = application.createBoard();
        return ResponseEntity.ok(BoardDto.from(state));
    }

}
