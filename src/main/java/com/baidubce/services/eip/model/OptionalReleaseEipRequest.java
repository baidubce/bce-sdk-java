/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.eip.model;

import lombok.Getter;
import lombok.Setter;

/**
 * The request for optional releasing eip.
 */
@Getter
@Setter
public class OptionalReleaseEipRequest extends ReleaseEipRequest {
    /**
     * whether to put the specific EIP in the recycle bin (true) or directly delete it (false).
     * The default value is false。
     */
    private boolean releaseToRecycle = false;

    public OptionalReleaseEipRequest withReleaseToRecycle(boolean releaseToRecycle) {
        this.releaseToRecycle = releaseToRecycle;
        return this;
    }

}
