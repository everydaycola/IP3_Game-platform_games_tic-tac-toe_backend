package be.kdg.ipj3.tictactoebackend.application;

import be.kdg.ipj3.tictactoebackend.domain.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
//TOOD: @Transactional
@Slf4j
public class TTTApplication {

    public Board createBoard() {
        final var board = new Board();
        // todo save to database
        return board;
    }


}
