/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iotdmp.model.platform;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.baidubce.model.GenericAccountRequest;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateRuleChainExternalDestinationRequestV2 extends GenericAccountRequest {
    private RuleChainExternalDestinationType type;
    private String description;
    private RuleChainExternalDestinationArgs args;

    public CreateRuleChainExternalDestinationRequestV2(RuleChainExternalDestinationArgs args, String description) {
        this.type = args.getType();
        this.description = description;
        this.args = args;
    }

    public CreateRuleChainExternalDestinationRequestV2(RuleChainExternalDestinationArgs args) {
        this.type = args.getType();
        this.args = args;
    }

    private void setType(RuleChainExternalDestinationType type) {
        this.type = type;
    }
}
