package be.kdg.ipj3.tictactoebackend.infrastructure.gameState.ai;

import be.kdg.ipj3.tictactoebackend.infrastructure.gameState.ai.dtos.AiAnswerDto;
import be.kdg.ipj3.tictactoebackend.infrastructure.gameState.ai.dtos.GameStateDtoAI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class ExternalAiCatalogTest {
    private GameStateDtoAI emptyGameStateDto;


    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private RestClient restClientMock;

    @InjectMocks
    private ExternalAiCatalog sut;

    @BeforeEach
    void setUp() {
        String[][] emptyBoardForAi = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                emptyBoardForAi[i][j] = "0";
            }
        }
        this.emptyGameStateDto = new GameStateDtoAI(emptyBoardForAi, "-1");
    }

    @Test
    void testAskForMove_SuccessfulResponse() {
        // Arrange
        AiAnswerDto responseDto = new AiAnswerDto(1, 1, 1);

        RestClient.RequestBodyUriSpec uriSpecMock = Mockito.mock(RestClient.RequestBodyUriSpec.class);
        RestClient.RequestBodySpec bodySpecMock = Mockito.mock(RestClient.RequestBodySpec.class);
        RestClient.ResponseSpec responseSpecMock = Mockito.mock(RestClient.ResponseSpec.class);

        Mockito.when(restClientMock.post()).thenReturn(uriSpecMock);
        Mockito.when(uriSpecMock.uri(anyString())).thenReturn(bodySpecMock);
        Mockito.when(bodySpecMock.body(emptyGameStateDto)).thenReturn(bodySpecMock);
        Mockito.when(bodySpecMock.retrieve()).thenReturn(responseSpecMock);
        Mockito.when(responseSpecMock.body(AiAnswerDto.class)).thenReturn(responseDto);

        // Act
        Optional<AiAnswerDto> result = sut.askForMove(emptyGameStateDto);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(responseDto, result.get());
        Mockito.verify(uriSpecMock).uri("/ai-move");
        Mockito.verify(bodySpecMock).body(emptyGameStateDto);
    }

    @Test
    void testAskForMove_NullResponse() {
        // Arrange
        // 1. Create mocks for the intermediate steps of the RestClient fluent API
        RestClient.RequestBodyUriSpec uriSpecMock = Mockito.mock(RestClient.RequestBodyUriSpec.class);
        RestClient.RequestBodySpec bodySpecMock = Mockito.mock(RestClient.RequestBodySpec.class);
        RestClient.ResponseSpec responseSpecMock = Mockito.mock(RestClient.ResponseSpec.class);

        // 2. Link the mocks together to form the chain
        Mockito.when(restClientMock.post()).thenReturn(uriSpecMock);
        Mockito.when(uriSpecMock.uri(anyString())).thenReturn(bodySpecMock);
        Mockito.when(bodySpecMock.body(emptyGameStateDto)).thenReturn(bodySpecMock);
        Mockito.when(bodySpecMock.retrieve()).thenReturn(responseSpecMock);
        Mockito.when(responseSpecMock.body(AiAnswerDto.class)).thenReturn(null);


        // Act
        Optional<AiAnswerDto> result = sut.askForMove(emptyGameStateDto);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testAskForMove_HttpStatusCodeException() {
        // Arrange
        Mockito.lenient().when(restClientMock
                        .post()
                        .uri(any(String.class))
                        .body(any())
                        .retrieve()
                        .body(AiAnswerDto.class))
                .thenAnswer(invocation -> {
                    throw new HttpStatusCodeException(HttpStatus.INTERNAL_SERVER_ERROR) {
                    };
                });

        // Act
        Optional<AiAnswerDto> result = sut.askForMove(emptyGameStateDto);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testAskForMove_ResourceAccessException() {
        // Arrange
        Mockito.lenient().when(restClientMock
                        .post()
                        .uri(any(String.class))
                        .body(any())
                        .retrieve()
                        .body(AiAnswerDto.class))
                .thenAnswer(InvocationOnMock -> {
                    throw new ResourceAccessException("AI Service Unavailable");
                });

        // Act
        Optional<AiAnswerDto> result = sut.askForMove(emptyGameStateDto);

        // Assert
        assertTrue(result.isEmpty());
    }
}