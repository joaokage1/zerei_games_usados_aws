package com.io.zerei_games_usados_aws.service.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.io.zerei_games_usados_aws.common.EventEnvelope;
import com.io.zerei_games_usados_aws.common.GameDTO;
import com.io.zerei_games_usados_aws.common.SNSMessage;
import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameStockEventConsumer implements IGameEventConsumer {

    private final ObjectMapper objectMapper;

    @Override
    @JmsListener(destination = "${aws.sqs.queue.stock.events.name}")
    public void receiveGameEvent(TextMessage textMessage) throws JMSException, JsonProcessingException {

        SNSMessage snsMessage = objectMapper.readValue(textMessage.getText(), SNSMessage.class);
        EventEnvelope envelope = objectMapper.readValue(snsMessage.getMessage(), EventEnvelope.class);
        GameDTO gameDTO = objectMapper.readValue(envelope.getData(), GameDTO.class);

        log.info("SNS Message through SQS: {}- EventType: {}", gameDTO, envelope.getEventType());
    }
}
