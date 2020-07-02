/*
 * Copyright 2018-2019 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.vms.model;

import java.util.Map;
import java.util.TreeMap;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * <p>
 * Contains parameters to lauch a voice call. user must specify the target
 * number and the call's show number.
 * </p>
 * <p>
 * The <strong>type</strong> is only support 0 for now means only text template
 * can supported. So user must use Console to initiate text template first then
 * can make call.
 * </p>
 * <p>
 * Template parameters is some key-value pairs and the key must be pre-setted on
 * Console.
 * </p>
 */
@lombok.Getter
@lombok.Setter
public class LaunchCallRequest extends AbstractBceRequest {
    private String calledShowNumber;
    private String calledNumber;
    private int type = 0;
    private String templateId;
    private Map<String, String> templateParameters = new TreeMap<String, String>();
    private String userDefinedTag;
    private int playTimes = 1;
    private int voiceVolume = 10;

    @Override
    public LaunchCallRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public LaunchCallRequest addTemplateParameter(String name, String value) {
        templateParameters.put(name, value);
        return this;
    }
}
