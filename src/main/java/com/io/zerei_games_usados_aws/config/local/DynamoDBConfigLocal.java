package com.io.zerei_games_usados_aws.config.local;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.BillingMode;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.io.zerei_games_usados_aws.repository.eventlog.GameEventLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;

@Slf4j
@Configuration
@Profile("local")
@EnableDynamoDBRepositories(basePackages = "com.io.zerei_games_usados_aws.repository.eventlog")
public class DynamoDBConfigLocal {

    private final AmazonDynamoDB amazonDynamoDB;

    public DynamoDBConfigLocal() {
        this.amazonDynamoDB = AmazonDynamoDBClient.builder()
                .withEndpointConfiguration(new AwsClientBuilder
                        .EndpointConfiguration("http://localhost:4566", Regions.US_EAST_1.getName()))
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();

        var dynamoDB = new DynamoDB(amazonDynamoDB);

        var attributeDefinitions = new ArrayList<AttributeDefinition>();
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("pk").withAttributeType(ScalarAttributeType.S));
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("sk").withAttributeType(ScalarAttributeType.S));

        var keySchemaElements = new ArrayList<KeySchemaElement>();
        keySchemaElements.add(new KeySchemaElement().withAttributeName("pk").withKeyType(KeyType.HASH));
        keySchemaElements.add(new KeySchemaElement().withAttributeName("sk").withKeyType(KeyType.RANGE));

        var createTableRequest = new CreateTableRequest()
                .withTableName("game_stock_events")
                .withKeySchema(keySchemaElements)
                .withAttributeDefinitions(attributeDefinitions)
                .withBillingMode(BillingMode.PAY_PER_REQUEST);

        var table = dynamoDB.createTable(createTableRequest);

        try {
            table.waitForActive();
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    @Bean
    @Primary
    public DynamoDBMapperConfig dynamoDBMapperConfig() {
        return DynamoDBMapperConfig.DEFAULT;
    }

    @Bean
    @Primary
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig dynamoDBMapperConfig) {
        return new DynamoDBMapper(amazonDynamoDB, dynamoDBMapperConfig);
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return this.amazonDynamoDB;
    }
}
