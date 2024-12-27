package com.baidubce.services.kafka.model.cluster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Controller {

    private Integer brokerId;

    private String changeTime;

    private String publicIp;

    private String internalIp;
}
