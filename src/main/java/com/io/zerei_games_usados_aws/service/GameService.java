package com.io.zerei_games_usados_aws.service;

import com.io.zerei_games_usados_aws.common.GameDTO;
import com.io.zerei_games_usados_aws.common.GameStockEventType;
import com.io.zerei_games_usados_aws.repository.mapper.GameRepositoryMapper;
import com.io.zerei_games_usados_aws.repository.GameRepository;
import com.io.zerei_games_usados_aws.service.publisher.IGameEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameRepositoryMapper gameRepositoryMapper;
    private final IGameEventPublisher eventPublisher;

    public void createGame(GameDTO gameDTO) {
        log.info("Creating game: {} - code: {}- stock:{}",
                gameDTO.getTitle(), gameDTO.getCode(), gameDTO.getStock());
        gameRepository.save(gameRepositoryMapper.toGame(gameDTO));

        eventPublisher.publishGameEvent(gameDTO, GameStockEventType.GAME_STOCK_CREATED, "SYSTEM");
    }

    public List<GameDTO> getGameByPlatform(String gamePlatform) {
        log.info("Getting all games from {}", gamePlatform);

        return gameRepository.findByPlatform(gamePlatform, orderByStock())
                .stream()
                .map(gameRepositoryMapper::toGameDTO)
                .toList();
    }

    public List<GameDTO> getAllGames() {
        log.info("Getting all games");
        return gameRepository.findAll(orderByStock())
                .stream()
                .map(gameRepositoryMapper::toGameDTO)
                .toList();
    }

    private static Sort orderByStock() {
        return Sort.by(Sort.Direction.ASC, "stock");
    }

    public void deleteGame(Long id) {
        log.info("Removing game {} from database", id);

        var game = gameRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        gameRepository.deleteById(id);

        eventPublisher.publishGameEvent(gameRepositoryMapper.toGameDTO(game), GameStockEventType.GAME_STOCK_DELETED, "SYSTEM");
    }

    public void updateGame(Long id, GameDTO gameDTO) {
        var game = gameRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        log.info("Updating game: {}- code: {}", gameDTO.getTitle(), gameDTO.getCode());

        var newGameAttributes = gameRepositoryMapper.toGame(gameDTO);
        newGameAttributes.setId(game.getId());

        gameRepository.save(newGameAttributes);

        eventPublisher.publishGameEvent(gameRepositoryMapper.toGameDTO(game), GameStockEventType.GAME_STOCK_UPDATED, "SYSTEM");
    }
}
