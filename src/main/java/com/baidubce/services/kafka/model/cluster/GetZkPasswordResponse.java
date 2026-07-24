/*
 * Copyright (C) 2024 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.kafka.model.cluster;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class GetZkPasswordResponse extends AbstractBceResponse {

    private String username;

    private String password;
}
