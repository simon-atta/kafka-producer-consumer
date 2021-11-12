
package com.kafka.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Body {

    private FulfillmentUnitInfo fulfillmentUnitInfo;
    private OrderInfo orderInfo;

}
