package com.io.zerei_games_usados_aws.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {
    private Long id;
    private String title;
    private String description;
    private String developer;
    private String publisher;
    private String classification;
    private Integer metacritic;
    private Double price;
    private LocalDate launchDate;
    private String timeToBeat;
    private GameCondition condition;
    private GamePlatform platform;
    private String code;
    private GameStock stock;
}
