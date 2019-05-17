package com.leone.boot.kafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leone.boot.common.utils.RandomValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author leone
 * @since 2018-12-26
 **/
@Slf4j
@Component
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private Long offset = 0L;

    /**
     * @param topic
     * @param count
     */
    public String send(String topic, Integer count) throws Exception {
        for (int i = 0; i < count; i++) {
            // Message<Map> message = new Message<>(offset, RandomValue.randomUser(), new Date());
            String message = RandomValue.randomMessage();
            offset++;
            // String content = objectMapper.writeValueAsString(message);
            kafkaTemplate.send(topic, message);
            Thread.sleep(500);
            log.info("send to: {} message: {} ", topic, message);
        }
        return "send to " + topic + " count: " + count;
    }

}
