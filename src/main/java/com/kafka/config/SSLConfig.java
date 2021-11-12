package com.kafka.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class SSLConfig {

    private Map<String, Object> props;

}
