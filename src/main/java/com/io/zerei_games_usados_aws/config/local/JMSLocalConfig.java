package com.io.zerei_games_usados_aws.config.local;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.amazonaws.services.sns.util.Topics;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import jakarta.jms.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.destination.DynamicDestinationResolver;


@Configuration
@EnableJms
@Slf4j
@Profile("local")
public class JMSLocalConfig {

    @Value("{aws.region}")
    private String awsRegion;

    private final AmazonSQS sqsClient;

    public JMSLocalConfig(AmazonSNS snsClient, Topic gameStockChangedTopic) {

        sqsClient = AmazonSQSClient.builder()
                .withEndpointConfiguration(new AwsClientBuilder
                        .EndpointConfiguration("http://localhost:4566", Regions.US_EAST_1.getName()))
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();

        String stockChangedQueueV1 = sqsClient.createQueue(new CreateQueueRequest("stock_changed_queue_v1")).getQueueUrl();

        log.info("SQS Queue URL:{}", gameStockChangedTopic);
        Topics.subscribeQueue(snsClient, sqsClient, gameStockChangedTopic.getTopicArn(), stockChangedQueueV1);
    }

    @Bean
    AmazonSQS sqsClient() {
        return sqsClient;
    }

    @Bean
    DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        SQSConnectionFactory sqsConnectionFactory = new SQSConnectionFactory(
                new ProviderConfiguration(), sqsClient);

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(sqsConnectionFactory);
        factory.setDestinationResolver(new DynamicDestinationResolver());
        factory.setConcurrency("2");
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);

        return factory;
    }
}
