package com.io.zerei_games_usados_aws.controller.impl;

import com.io.zerei_games_usados_aws.common.GameStockEventLogDTO;
import com.io.zerei_games_usados_aws.controller.GameStockEventLogResource;
import com.io.zerei_games_usados_aws.repository.eventlog.GameEventLogRepository;
import com.io.zerei_games_usados_aws.repository.eventlog.mapper.GameStockEventLogRepositoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GameStockEventLogController implements GameStockEventLogResource {

    //DO NOT DO THIS
    private final GameStockEventLogRepositoryMapper gameStockEventLogRepositoryMapper;
    private final GameEventLogRepository gameEventLogRepository;

    @Override
    public ResponseEntity<List<GameStockEventLogDTO>> getAllGames() {
        return ResponseEntity.ok().body(gameEventLogRepository.findAll()
                .stream()
                .map(gameStockEventLogRepositoryMapper::toGameStockEventLogDTO)
                .toList()
        );
    }
}
