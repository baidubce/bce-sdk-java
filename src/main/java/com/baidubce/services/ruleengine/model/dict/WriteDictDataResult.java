/*
 * Copyright 2019 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law * or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.ruleengine.model.dict;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Class for write dict data result
 *
 * Created by liudawei02 on 19/4/3.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WriteDictDataResult extends AbstractBceResponse {
    private String result;
    private int success;
    private List<String> errKey = new LinkedList<String>();
    private List<String> errValue = new LinkedList<String>();
}
