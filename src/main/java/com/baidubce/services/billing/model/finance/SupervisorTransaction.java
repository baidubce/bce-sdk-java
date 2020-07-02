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

package com.baidubce.services.billing.model.finance;

import java.math.BigDecimal;

/**
 * balance transaction between supervised and supervisor
 *
 */
public class SupervisorTransaction {

    /**
     * inAccountId
     */
    private String inAccountId;

    /**
     * outAccountId
     */
    private String outAccountId;

    /**
     * trade amount
     */
    private BigDecimal amount;

    /**
     * opt time
     */
    private String createTime;

    /**
     * trade type ：放款/收款
     */
    private String transTypeDesc;

    public String getInAccountId() {
        return inAccountId;
    }

    public void setInAccountId(String inAccountId) {
        this.inAccountId = inAccountId;
    }

    public String getOutAccountId() {
        return outAccountId;
    }

    public void setOutAccountId(String outAccountId) {
        this.outAccountId = outAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTransTypeDesc() {
        return transTypeDesc;
    }

    public void setTransTypeDesc(String transTypeDesc) {
        this.transTypeDesc = transTypeDesc;
    }
}
