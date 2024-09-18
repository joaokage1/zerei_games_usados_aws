package com.io.zerei_games_usados_aws.controller;

import com.io.zerei_games_usados_aws.common.GameStockEventLogDTO;
import com.io.zerei_games_usados_aws.controller.common.ResourceUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface GameStockEventLogResource {

    @GetMapping(ResourceUtils.RESOURCE_STOCK_EVENT + ResourceUtils.VERSION_1)
    ResponseEntity<List<GameStockEventLogDTO>> getAllGames();
}
