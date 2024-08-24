package com.io.zerei_games_usados_aws.service.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;

public interface IGameEventConsumer {

    void receiveGameEvent(TextMessage textMessage) throws JMSException, JsonProcessingException;
}
