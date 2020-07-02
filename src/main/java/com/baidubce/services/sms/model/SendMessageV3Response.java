/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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

import java.util.List;

/**
 * Response object of sending message
 */
public class SendMessageV3Response extends BaseResponse {

    /**
     * The response item list of all mobiles in the request
     */
    List<SendMessageItem> data;

    public void setData(List<SendMessageItem> data) {
        this.data = data;
    }

    public List<SendMessageItem> getData() {
        return data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"requestId\":\"").append(this.getRequestId()).append("\"");
        sb.append(",\"code\":\"").append(this.getCode()).append("\"");
        sb.append(",\"message\":\"").append(this.getMessage()).append("\"");
        if (data != null) {
            sb.append(",\"data\":[");
            for (int i = 0;i < data.size();i++) {
                sb.append(data.get(i).toString());
                if (i != data.size() - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");
        }
        sb.append("}");
        return sb.toString();
    }

}
