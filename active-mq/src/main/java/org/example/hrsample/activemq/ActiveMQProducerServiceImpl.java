package org.example.hrsample.activemq;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActiveMQProducerServiceImpl implements ActiveMQProducerService {
    private final String LOG_QUEUE_NAME = "log_queue";

    private final JmsTemplate jmsTemplate;

    @Override
    public void sendMessageToLog(String message) {
        jmsTemplate.convertAndSend(LOG_QUEUE_NAME, message);
    }
}
