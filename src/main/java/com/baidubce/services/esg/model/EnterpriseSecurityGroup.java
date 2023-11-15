/*
 * Copyright (c) 2014-2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.esg.model;

import com.baidubce.services.bcc.model.TagModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * The detail model for describing enterprise security group.
 */
@Getter
@Setter
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class EnterpriseSecurityGroup {

    /**
     * The id of the enterprise security group.
     */
    private String id;

    /**
     * The name of the enterprise security group.
     */
    private String name;

    /**
     * The description of the enterprise security group.
     */
    private String desc;

    /**
     * The created time of the enterprise security group.
     */
    private  String createdTime;

    /**
     * The list of rules which describe how the enterprise security group works.
     */
    private List<EnterpriseSecurityGroupRule> rules;

    /**
     * The list of tags defined by users themselves.
     * they can summarize or classify the main content of the enterprise security group.
     */
    private List<TagModel> tags;
}
