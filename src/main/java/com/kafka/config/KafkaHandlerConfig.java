package com.kafka.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.converter.Jackson2JavaTypeMapper.TypePrecedence;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
public class KafkaHandlerConfig {

  @Bean
  public RecordMessageConverter messageConverter(ObjectMapper kafkaMessageObjectMapper) {
    StringJsonMessageConverter converter = new StringJsonMessageConverter(kafkaMessageObjectMapper);
    DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
    typeMapper.setTypePrecedence(TypePrecedence.TYPE_ID);
    typeMapper.addTrustedPackages("*");
    converter.setTypeMapper(typeMapper);
    return converter;
  }



  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaJsonListenerContainerFactory(
      ConsumerFactory<String, Object> consumerFactory, RecordMessageConverter messageConverter) {
    ConcurrentKafkaListenerContainerFactory<String, Object> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setMessageConverter(messageConverter);
    factory.setConsumerFactory(consumerFactory);
    factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
    return factory;
  }

}
