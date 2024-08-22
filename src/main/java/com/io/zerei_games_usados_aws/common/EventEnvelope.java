package com.io.zerei_games_usados_aws.common;

import lombok.Data;

@Data
public class EventEnvelope {

    private GameStockEventType eventType;
    private String data;
    private String username;
}
