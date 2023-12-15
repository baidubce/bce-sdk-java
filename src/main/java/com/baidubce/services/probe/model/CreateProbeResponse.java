package com.baidubce.services.probe.model;

import com.baidubce.model.AbstractBceResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateProbeResponse extends AbstractBceResponse {
    private String probeId;
}
