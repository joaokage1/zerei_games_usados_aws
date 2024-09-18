package com.io.zerei_games_usados_aws.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class GameEventKey {

    private String pk;
    private String sk;

    @DynamoDBHashKey(attributeName = "pk")
    public String getPk() {
        return pk;
    }

    @DynamoDBRangeKey(attributeName = "sk")
    public String getSk() {
        return sk;
    }

}
