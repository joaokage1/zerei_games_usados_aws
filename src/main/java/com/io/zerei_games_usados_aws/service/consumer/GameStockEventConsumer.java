package com.io.zerei_games_usados_aws.service.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.io.zerei_games_usados_aws.common.EventEnvelope;
import com.io.zerei_games_usados_aws.common.GameDTO;
import com.io.zerei_games_usados_aws.common.SNSMessage;
import com.io.zerei_games_usados_aws.model.GameStockEventLog;
import com.io.zerei_games_usados_aws.repository.eventlog.GameEventLogRepository;
import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameStockEventConsumer implements IGameEventConsumer {

    private final ObjectMapper objectMapper;
    private final GameEventLogRepository gameEventLogRepository;

    @Override
    @JmsListener(destination = "${aws.sqs.queue.stock.events.name}")
    public void receiveGameEvent(TextMessage textMessage) throws JMSException, JsonProcessingException {

        SNSMessage snsMessage = objectMapper.readValue(textMessage.getText(), SNSMessage.class);
        EventEnvelope envelope = objectMapper.readValue(snsMessage.getMessage(), EventEnvelope.class);
        GameDTO gameDTO = objectMapper.readValue(envelope.getData(), GameDTO.class);

        log.info("SNS Message through SQS: {}- EventType: {}", gameDTO, envelope.getEventType());

        GameStockEventLog gameStockEventLog = buildGameEventLog(envelope, gameDTO);
        gameEventLogRepository.save(gameStockEventLog);
    }

    private GameStockEventLog buildGameEventLog(EventEnvelope envelope, GameDTO gameDTO) {
        long timestamp = Instant.now().toEpochMilli();

        GameStockEventLog gameStockEventLog = new GameStockEventLog();
        gameStockEventLog.setPk(gameDTO.getCode());
        gameStockEventLog.setSk(envelope.getEventType() + "_" + timestamp);
        gameStockEventLog.setEventType(envelope.getEventType());
        gameStockEventLog.setData(envelope.getData());
        gameStockEventLog.setTimestamp(timestamp);
        gameStockEventLog.setTtl(Instant.now().plus(Duration.ofMinutes(10)).getEpochSecond());
        gameStockEventLog.setUsername(envelope.getUsername());

        return gameStockEventLog;
    }
}
