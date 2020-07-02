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
package com.baidubce.services.iotshc.model.deivce;

import com.baidubce.services.iotshc.model.CommonListRequest;
import lombok.Data;

/**
 * list device information request
 */
@Data
public class DeviceInfoListRequest extends CommonListRequest {

    private String fc;
    private String pk;
    private String state;

    public DeviceInfoListRequest(String fc, String pk) {
        this.fc = fc;
        this.pk = pk;
    }
}
