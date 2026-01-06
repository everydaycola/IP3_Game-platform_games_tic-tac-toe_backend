package be.kdg.ipj3.tictactoebackend.infrastructure.rabbitMq;

import be.kdg.ipj3.tictactoebackend.api.dtos.registration.FullGameDto;
import be.kdg.ipj3.tictactoebackend.config.RegistrationConfig;
import be.kdg.ipj3.tictactoebackend.config.rabbitMq.RabbitMQProperties;
import be.kdg.ipj3.tictactoebackend.infrastructure.rabbitMq.messages.RegisterGameMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Component
@Profile("!test")
@RequiredArgsConstructor
public class RabbitStartupPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQProperties properties;
    private final TaskScheduler taskScheduler;
    private final UrlChecker urlChecker;
    private final RegistrationConfig registrationConfig;

    @EventListener(ApplicationReadyEvent.class)
    public void publishStartupEvent() {
        final var updatedDto = new FullGameDto(
                registrationConfig.getId(),
                registrationConfig.getName(),
                registrationConfig.getMaxPlayers(),
                registrationConfig.getAiStartGameEndpoint(),
                registrationConfig.getStartGameEndpoint(),
                registrationConfig.getDescription(),
                registrationConfig.getPrice(),
                registrationConfig.getImage(),
                registrationConfig.getIcon(),
                registrationConfig.getGenre(),
                registrationConfig.getExternalGameUrl(),
                new ArrayList<>(),
                new HashMap<>()
        );

        final var futureRef = new AtomicReference<ScheduledFuture<?>>();

        final var future = taskScheduler.scheduleWithFixedDelay(() -> {
            try {
                if (!urlChecker.isUrlReachable(registrationConfig.getInternalGameUrl())) {
                    log.warn("Game not registered yet; url not reachable internally: {}, external is {}", registrationConfig.getInternalGameUrl(), registrationConfig.getExternalGameUrl());
                    return;
                }

                rabbitTemplate.convertAndSend(
                        properties.getExchangeName(),
                        properties.getRegisterGameBinding(),
                        new RegisterGameMessage(updatedDto)
                );
                log.info("Startup game message sent to RabbitMQ: {}", updatedDto);

                final var f = futureRef.get();
                if (f != null) f.cancel(false);

            } catch (AmqpException e) {
                log.error("Error while trying to register startup game (will retry)", e);
            }
        }, Duration.ofSeconds(5));
        futureRef.set(future);
    }

}
