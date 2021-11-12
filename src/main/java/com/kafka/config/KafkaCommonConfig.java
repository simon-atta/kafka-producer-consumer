package com.kafka.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableKafka
@Configuration
public class KafkaCommonConfig {


    @Bean
    public SSLConfig sslConfig(
            @Value("${kafka.protocol}") String protocol,
            @Value("${kafka.ssl.keystorelocation:}") String keystoreLocation,
            @Value("${kafka.ssl.keystorepasswordfile:}") String keystorePasswordFile,
            @Value("${kafka.ssl.truststorelocation:}") String truststoreLocation,
            @Value("${kafka.ssl.truststorepasswordfile:}") String trustStorePasswordFile,
            @Value("${kafka.ssl.sslpasswordfile:}") String sslPasswordFile) {


        Map<String, Object> props = new HashMap<>();
        props.put("security.protocol", protocol);
        if ("SSL".equals(protocol)) {
            props.put("ssl.truststore.location", truststoreLocation);
            props.put("ssl.truststore.password", trustStorePasswordFile);
            props.put("ssl.keystore.location", keystoreLocation);
            props.put("ssl.keystore.password", keystorePasswordFile);
            props.put("ssl.key.password", sslPasswordFile);
            props.put("ssl.endpoint.identification.algorithm", "");
            props.put("ssl.keystore.type", "JKS");
            props.put("ssl.truststore.type", "JKS");
            props.put("ssl.enabled.protocols", "TLSv1.2, TLSv1.3");
            props.put("ssl.keymanager.algorithm", "SunX509");
            props.put("ssl.protocol", "TLSv1.3");
            props.put(" ssl.trustmanager.algorithm", "PKIX");
        }

        return new SSLConfig(props);


    }

    @Bean
    public ObjectMapper kafkaMessageObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE,
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper;
    }

}
