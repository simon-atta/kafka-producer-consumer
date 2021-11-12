
package com.kafka.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderInfo {

    private List<Object> customerDistributionUnit;
    private List<Deviation> deviation;
    private String orderStatus;
    private String receiverReference;
    private Requester requester;
    private String serviceProviderReference;
    private Object statusReason;
    private String statusUpdateTime;


}
