/*
 * Copyright (c) 2026 Baidu.com, Inc. All Rights Reserved
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

import com.baidubce.BceConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * response item for credit quota query
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupervisorCreditQuota {
    /**
     * accountId
     */
    private String accountId;
    /**
     * the account type : MASTER_ACCOUNT or SUB_ACCOUNT
     */
    private String accountType;
    /**
     * login name of the account
     */
    private String loginName;
    /**
     * organization unit
     */
    private String ouName;
    /**
     * comment
     */
    private String comment;
    /**
     * Total credit quota (unit: RMB)
     */
    private BigDecimal quota;
    /**
     * Remaining credit quota (unit: RMB) - could be negative if quota is exceeded
     */
    private BigDecimal remainQuota;
    /**
     * Credit start time
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BceConstants.DEFAULT_DATETIME_FORMAT, timezone = "UTC")
    private Date beginTime;
    /**
     * Credit expiration time
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BceConstants.DEFAULT_DATETIME_FORMAT, timezone = "UTC")
    private Date expireTime;

}
