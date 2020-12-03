/**
 * Copyright 2020 Baidu, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.bes.model;

import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.io.StringWriter;

public class BesGetRenewListRequest extends AbstractBesRequest {
    @JsonProperty
    private String order;
    @JsonProperty
    private String orderBy;
    @JsonProperty
    private int pageNo;
    @JsonProperty
    private int pageSize;
    @JsonProperty
    private int daysToExpiration = -1; // 最近多少天过期，<0为不限制

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getDaysToExpiration() {
        return daysToExpiration;
    }

    public void setDaysToExpiration(int daysToExpiration) {
        this.daysToExpiration = daysToExpiration;
    }

    public String toJson() throws IOException {
        StringWriter writer = new StringWriter();

        JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("order", order);
        jsonGenerator.writeStringField("orderBy", orderBy);
        jsonGenerator.writeNumberField("pageNo", pageNo);
        jsonGenerator.writeNumberField("pageSize", pageSize);
        jsonGenerator.writeNumberField("daysToExpiration", daysToExpiration);
        jsonGenerator.writeEndObject();
        jsonGenerator.close();

        return writer.toString();
    }
}