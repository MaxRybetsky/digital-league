package org.example.hrsample.activemq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ActiveMQConsumerFromLogQueue implements ActiveMQLogConsumer {
    private final String LOG_QUEUE_NAME = "log_queue";

    @Override
    @JmsListener(destination = LOG_QUEUE_NAME)
    public void writeMessageToLog(String message) {
        log.debug("Info from queue:" + message);
    }
}
