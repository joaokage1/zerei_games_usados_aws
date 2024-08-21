package com.io.zerei_games_usados_aws.repository;

import com.io.zerei_games_usados_aws.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

    Optional<Game> findByCode(String code);

    List<Game> findByPlatform(String platform);

    List<Game> findAll();
}
