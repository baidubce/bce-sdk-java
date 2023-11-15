/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.as.model.asgroup;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

/**
 * @Author zhangzhongyang
 * @Date 2020/12/8 3:40 下午
 */
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagInfo {
    private String tagKey;
    private String tagValue;
}
