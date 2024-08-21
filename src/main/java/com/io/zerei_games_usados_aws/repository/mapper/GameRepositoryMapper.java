package com.io.zerei_games_usados_aws.repository.mapper;

import com.io.zerei_games_usados_aws.common.GameDTO;
import com.io.zerei_games_usados_aws.model.Game;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameRepositoryMapper {

    GameDTO toGameDTO(Game game);

    Game toGame(GameDTO gameDTO);
}
