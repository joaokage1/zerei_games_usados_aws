package com.io.zerei_games_usados_aws.common;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameStockEventLogDTO {

    private GameStockEventType eventType;
    private String data;
    private String username;
    private long timestamp;
}
