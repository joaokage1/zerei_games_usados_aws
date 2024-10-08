package com.io.zerei_games_usados_aws.service.publisher;

import com.io.zerei_games_usados_aws.common.GameDTO;
import com.io.zerei_games_usados_aws.common.GameStockEventType;

public interface IGameEventPublisher {

    default void publishGameEvent(GameDTO gameDTO, GameStockEventType eventType, String username) {
    }
}
