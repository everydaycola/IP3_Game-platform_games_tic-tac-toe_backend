package be.kdg.ipj3.tictactoebackend.infrastructure.rabbitMq.messages;
import be.kdg.ipj3.tictactoebackend.api.dtos.registration.FullGameDto;

public record RegisterGameMessage(FullGameDto gameDto) {
}
