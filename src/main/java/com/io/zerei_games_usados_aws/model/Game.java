package com.io.zerei_games_usados_aws.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"code"})
        }
)
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 32, nullable = false)
    private String title;

    private String description;

    @Column(length = 32, nullable = false)
    private String developer;

    @Column(length = 32, nullable = false)
    private String publisher;

    @Column(length = 32, nullable = false)
    private String classification;

    private Integer metacritic;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private LocalDate launchDate;

    @Column(length = 32)
    private String timeToBeat;

    @Column(name = "game_condition", nullable = false)
    private String condition;

    @Column(nullable = false)
    private String platform;

    @Column(length = 8, nullable = false)
    private String code;

    @Column(nullable = false)
    private String stock;
}
