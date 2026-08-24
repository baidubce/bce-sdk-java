package com.baidubce.services.bes.v3.model.cluster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoRenew {

    private Boolean renew = false;

    private Integer renewTime;

    private String renewTimeUnit;
}