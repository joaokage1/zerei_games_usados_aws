DROP DATABASE IF EXISTS aws_project01_db;

CREATE DATABASE aws_project01_db;

USE aws_project01_db;

DROP TABLE IF EXISTS game;

CREATE TABLE game (
    id int not null auto_increment,
    title varchar(32) not null,
    description text,
    developer varchar(32) not null,
    publisher varchar(32) not null,
    classification varchar(32) not null,
    metacritic int,
    price decimal(15,2) not null,
    launch_date date not null,
    time_to_beat varchar(32),
    game_condition varchar(32) not null,
    platform varchar(32) not null,
    code varchar(8) not null,
    stock varchar(32) not null,
    primary key(id)
);

SET character_set_client = utf8;
SET character_set_connection = utf8;
SET character_set_results = utf8;
SET collation_connection = utf8_general_ci;

INSERT INTO game(
title,
description,
developer,
publisher,
classification,
metacritic,
price,
launch_date,
time_to_beat,
game_condition,
platform,
code,
stock)
VALUES(
'Death Stranding',
null,
'Kojima Productions',
'Kojima Productions',
'16+',
82, 71.91,
"2019-11-08",
'40h 59m',
'USED_AS_NEW',
'PS4',
'ds-01',
'AVAILABLE'
);