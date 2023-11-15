/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.as.model.asgroup;

import lombok.Data;

import java.util.List;

/**
 * @Author zhangzhongyang
 * @Date 2020/12/2 3:47 下午
 */
@Data
public class AssignTagInfo {
    private boolean relationTag = false;
    private List<TagInfo> tags;
}
