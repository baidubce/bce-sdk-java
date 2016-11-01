/*
 * Copyright (c) 2016 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.sms.model;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.sms.SmsConstant;

/**
 * BaseResponse
 */
public class BaseResponse extends AbstractBceResponse {

    // requestId(equals messageId)

    private String requestId;

    // return code, success: 1000
    private String code;

    // return message description
    private String message;

    /**
     * return success true not means message is received success
     *
     * @return true: indicate request is success, or fail
     */
    public boolean isSuccess() {
        return SmsConstant.SUCCESS.equals(this.getCode());
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
