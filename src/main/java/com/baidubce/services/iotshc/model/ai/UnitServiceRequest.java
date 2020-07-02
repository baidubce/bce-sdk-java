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
package com.baidubce.services.iotshc.model.ai;

import com.baidubce.model.GenericAccountRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * unit service request
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UnitServiceRequest extends GenericAccountRequest {

    private String fc;
    private String pk;
    private String ak;
    private String query;

    // optional parameter
    private String sessionId = null;
    private String custom = "";
    private boolean detail = false;

    public UnitServiceRequest(String fc, String pk, String ak, String query) {
        this.fc = fc;
        this.pk = pk;
        this.ak = ak;
        this.query = query;
    }
}
