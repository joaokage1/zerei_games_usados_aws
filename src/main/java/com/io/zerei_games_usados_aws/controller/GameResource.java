package com.io.zerei_games_usados_aws.controller;

import com.io.zerei_games_usados_aws.common.GameDTO;
import com.io.zerei_games_usados_aws.controller.common.ResourceUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface GameResource {

    @GetMapping(ResourceUtils.RESOURCE + ResourceUtils.VERSION_1 + "/game/{platform}")
    ResponseEntity<List<GameDTO>> getGameByPlatform(@PathVariable("platform") String platform);

    @GetMapping(ResourceUtils.RESOURCE + ResourceUtils.VERSION_1)
    ResponseEntity<List<GameDTO>> getAllGames();

    @PostMapping(ResourceUtils.RESOURCE + ResourceUtils.VERSION_1)
    @ResponseStatus(value = HttpStatus.CREATED)
    void createGame(@RequestBody GameDTO gameDTO);

    @PutMapping(ResourceUtils.RESOURCE + ResourceUtils.VERSION_1 + "/game/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    void updateGame(@PathVariable("id") Long id, @RequestBody GameDTO gameDTO);

    @DeleteMapping(ResourceUtils.RESOURCE + ResourceUtils.VERSION_1 + "/game/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    void deleteGame(@PathVariable("id") Long id);
}
