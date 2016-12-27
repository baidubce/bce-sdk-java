/*
 * Copyright 2014 Baidu, Inc.
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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * CreateTemplateResponse
 */
public class CreateTemplateResponse extends BaseResponse {

    @JsonInclude(Include.NON_EMPTY)
    private CreateTemplateData data = null;

    public CreateTemplateData getData() {
        return data;
    }

    public void setData(CreateTemplateData data) {
        this.data = data;
    }

    public static class CreateTemplateData {
        String templateId;

        public String getTemplateId() {
            return templateId;
        }

        public void setTemplateId(String templateId) {
            this.templateId = templateId;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("templateId=\"").append(templateId).append("\"");
            sb.append("}");
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SendMessageV2Response{");
        sb.append("requestId=\"").append(this.getRequestId()).append("\"");
        sb.append(", code=\"").append(this.getCode()).append("\"");
        sb.append(", message=\"").append(this.getMessage()).append("\"");
        sb.append(", data=").append(this.data).append("");
        sb.append("}");
        return sb.toString();
    }

}
