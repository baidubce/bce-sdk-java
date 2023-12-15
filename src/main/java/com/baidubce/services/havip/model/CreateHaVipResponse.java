/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.havip.model;

import com.baidubce.model.AbstractBceResponse;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateHaVipResponse extends AbstractBceResponse {
    private String haVipId;
}
