package com.io.zerei_games_usados_aws.service.publisher;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.io.zerei_games_usados_aws.common.EventEnvelope;
import com.io.zerei_games_usados_aws.common.GameDTO;
import com.io.zerei_games_usados_aws.common.GameStockEventType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class SNSGameStockEventPublisher implements IGameEventPublisher {

    private final AmazonSNS snsClient;
    private final Topic gameStockChangedTopic;
    private final ObjectMapper objectMapper;


    @Override
    public void publishGameEvent(GameDTO gameDTO, GameStockEventType eventType, String username) {

        log.info("Starting SNS publishing for {}:{}, eventType {}", Objects.isNull(gameDTO.getTitle())? gameDTO.getCode(): gameDTO.getTitle(), gameDTO.getCode(), eventType);
        EventEnvelope envelope = new EventEnvelope();
        envelope.setEventType(eventType);
        envelope.setUsername(username);

        try {
            envelope.setData(objectMapper.writeValueAsString(gameDTO));

            snsClient.publish(gameStockChangedTopic.getTopicArn(), objectMapper.writeValueAsString(envelope));
            log.info("message published");
        } catch (JsonProcessingException e) {
            log.error("Message will not be sent");
        }
    }
}
