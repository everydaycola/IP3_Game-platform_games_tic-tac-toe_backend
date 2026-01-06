package be.kdg.ipj3.tictactoebackend.config.rabbitMq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.rabbitmq.fourteengames")
public class RabbitMQProperties {
    private final String exchangeName;
    private final String registerGameQueue;
    private final String registerGameBinding;
    private final String unlockAchievementBinding;
}
