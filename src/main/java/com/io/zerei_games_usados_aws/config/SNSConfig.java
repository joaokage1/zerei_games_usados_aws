package com.io.zerei_games_usados_aws.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!local")
@Configuration
public class SNSConfig {

    @Value("{aws.region}")
    private String awsRegion;

    @Value("{aws.sns.stock.topic}")
    private String gameStockChangedTopic;

    @Bean
    public AmazonSNS snsClient() {
        return AmazonSNSClientBuilder.standard()
                .withRegion(awsRegion)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
    }

    @Bean(name = "gameStockChangedTopic")
    public Topic snsGameStockChangedTopic() {
        return new Topic().withTopicArn(gameStockChangedTopic);
    }
}
