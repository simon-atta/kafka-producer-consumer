package com.kafka.service.consumer;


import com.kafka.config.StoreConfig;
import com.kafka.model.EventRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class ConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    private final StoreConfig storeConfig;

    public ConsumerService(StoreConfig storeConfig) {
        this.storeConfig = storeConfig;
    }

    @KafkaListener(topics = "${selling.topics.name}", groupId = "${selling.topics.group.id}")
    public void consume(EventRequest message) {

        if(validateRequest(message))
            System.out.print(Integer.parseInt(message.getBody().getOrderInfo().getRequester().getOrderReference()));


        logger.info(String.format("$$$$ => Consumed message: %s", message.getEventSource()));
    }


    private boolean validateRequest(EventRequest message) {
        return message.getBody().getOrderInfo().getRequester().getOrderSource().contains("ISELL")
                && validateStore(message.getBuCode());
    }

    private boolean validateStore(String buCode) {
        Optional<String> storeOptional = storeConfig.getConfig().stream().filter(store
                -> store.equals(buCode)).findAny();

        return storeOptional.isPresent();
    }


}
