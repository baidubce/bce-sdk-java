package com.baidubce.services.vodpro.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created on 2017/8/17 15:43.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TriggerStatus {

    private String vcaStatus;

    private String vcrStatus;

    private String mpcStatus;
}
