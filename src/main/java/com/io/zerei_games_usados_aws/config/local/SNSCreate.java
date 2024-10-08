package com.io.zerei_games_usados_aws.config.local;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.Topic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Profile("local")
@Configuration
public class SNSCreate {

    private final AmazonSNS snsClient;
    private final String gameStockChangedTopic;

    public SNSCreate() {
        this.snsClient = AmazonSNSClient.builder()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:4566", Regions.US_EAST_1.getName()))
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();

        CreateTopicRequest createTopicRequest = new CreateTopicRequest("stock_changed_v1");
        gameStockChangedTopic = this.snsClient.createTopic(createTopicRequest).getTopicArn();

        log.info("SNS topic ARN{}", gameStockChangedTopic);
    }

    @Bean
    public AmazonSNS snsClient() {
        return snsClient;
    }

    @Bean(name = "gameStockChangedTopic")
    public Topic snsGameStockChangedTopic() {
        return new Topic().withTopicArn(gameStockChangedTopic);
    }

}
