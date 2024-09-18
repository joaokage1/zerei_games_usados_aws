package com.io.zerei_games_usados_aws.repository.eventlog.mapper;

import com.io.zerei_games_usados_aws.common.GameStockEventLogDTO;
import com.io.zerei_games_usados_aws.model.GameStockEventLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameStockEventLogRepositoryMapper {

    GameStockEventLogDTO toGameStockEventLogDTO(GameStockEventLog gameStockEventLog);

    GameStockEventLog toGameStockEventLog(GameStockEventLogDTO gameStockEventLogDTO);
}
