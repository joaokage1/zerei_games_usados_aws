package com.io.zerei_games_usados_aws.service;

import com.io.zerei_games_usados_aws.common.GameDTO;
import com.io.zerei_games_usados_aws.repository.mapper.GameRepositoryMapper;
import com.io.zerei_games_usados_aws.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameRepositoryMapper gameRepositoryMapper;

    public void createGame(GameDTO gameDTO) {
        gameRepository.save(gameRepositoryMapper.toGame(gameDTO));
    }

    public List<GameDTO> getGameByPlatform(String gamePlatform) {

        return gameRepository.findByPlatform(gamePlatform)
                .stream()
                .map(gameRepositoryMapper::toGameDTO)
                .toList();
    }

    public List<GameDTO> getAllGames() {
        return gameRepository.findAll()
                .stream()
                .map(gameRepositoryMapper::toGameDTO)
                .toList();
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    public void updateGame(Long id, GameDTO gameDTO) {
        var game = gameRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        var newGameAttributes = gameRepositoryMapper.toGame(gameDTO);
        newGameAttributes.setId(game.getId());

        gameRepository.save(newGameAttributes);
    }
}
