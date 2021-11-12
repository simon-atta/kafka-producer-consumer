
package com.kafka.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EventRequest {

    private String eventSource;

    private String buCode;

    private String buType;

    private String traceId;

    private Body body;

}
