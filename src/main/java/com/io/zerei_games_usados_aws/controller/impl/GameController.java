package com.io.zerei_games_usados_aws.controller.impl;

import com.io.zerei_games_usados_aws.common.GameDTO;
import com.io.zerei_games_usados_aws.controller.GameResource;
import com.io.zerei_games_usados_aws.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GameController implements GameResource {

    private final GameService gameService;

    @Override
    public ResponseEntity<List<GameDTO>> getGameByPlatform(String gamePlatform) {
        return ResponseEntity.ok().body(gameService.getGameByPlatform(gamePlatform));
    }

    @Override
    public ResponseEntity<List<GameDTO>> getAllGames() {
        return ResponseEntity.ok().body(gameService.getAllGames());
    }

    @Override
    public void createGame(GameDTO gameDTO) {
        gameService.createGame(gameDTO);
    }

    @Override
    public void updateGame(Long id, GameDTO gameDTO) {
        gameService.updateGame(id, gameDTO);
    }

    @Override
    public void deleteGame(Long id) {
        gameService.deleteGame(id);
    }
}
