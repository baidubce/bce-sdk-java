package com.baidubce.services.bes.v3.model.schedule;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class DeleteScheduleResponse extends AbstractBceResponse {
    private Boolean success;
}
