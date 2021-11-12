package com.kafka.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<String, Object> producerFactory(
            ObjectMapper kafkaMessageObjectMapper,
            SSLConfig sslConfig,
            @Value("${kafka.endpoints}") String endpoints) {
        Map<String, Object> configProps = new HashMap<>();
        configProps.putAll(sslConfig.getProps());
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, endpoints);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        JsonSerializer objectSerializer = new JsonSerializer(kafkaMessageObjectMapper);

        return new DefaultKafkaProducerFactory<>(configProps, null, objectSerializer);
    }


    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(
            ProducerFactory<String, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

}
