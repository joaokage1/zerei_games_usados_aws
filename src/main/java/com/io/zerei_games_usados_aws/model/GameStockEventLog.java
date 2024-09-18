package com.io.zerei_games_usados_aws.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import com.io.zerei_games_usados_aws.common.GameStockEventType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Objects;


@NoArgsConstructor
@DynamoDBTable(tableName = "game_stock_events")
public class GameStockEventLog {

    @Id
    private GameEventKey gameEventKey;

    @Getter
    @Setter
    @DynamoDBTypeConvertedEnum
    private GameStockEventType eventType;

    @Getter
    @Setter
    private long ttl;
    @Getter
    @Setter
    private String data;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private long timestamp;

    @DynamoDBHashKey(attributeName = "pk")
    public String getPk() {
        return this.gameEventKey != null ? this.gameEventKey.getPk() : null;
    }

    public void setPk(String pk) {
        if (Objects.isNull(this.gameEventKey)) {
            this.gameEventKey = new GameEventKey();
        }

        this.gameEventKey.setPk(pk);
    }

    @DynamoDBRangeKey(attributeName = "sk")
    public String getSk() {
        return this.gameEventKey != null ? this.gameEventKey.getSk() : null;
    }

    public void setSk(String sk) {
        if (Objects.isNull(this.gameEventKey)) {
            this.gameEventKey = new GameEventKey();
        }

        this.gameEventKey.setSk(sk);
    }
}
