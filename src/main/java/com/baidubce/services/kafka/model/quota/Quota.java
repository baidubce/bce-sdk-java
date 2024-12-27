package com.baidubce.services.kafka.model.quota;

import lombok.Data;

@Data
public class Quota {

    private String username;

    private Boolean userDefault;

    private String clientId;

    private Boolean clientDefault;

    private Long producerByteRate;

    private Long consumerByteRate;
}
