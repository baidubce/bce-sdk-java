/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.common;

import com.baidubce.internal.InternalRequest;

/**
 * Responsible for handling an HTTP request.
 *
 * @author zhangquan07
 */
public interface HttpRequestHandler {

    boolean handle(InternalRequest internalRequest, BaseBceRequest bceRequest);
}
