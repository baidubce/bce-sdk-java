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
package com.baidubce.services.iotdmp.model.product;

import com.baidubce.model.GenericAccountRequest;
import com.baidubce.services.iotdmp.model.device.DeviceType;
import com.baidubce.services.iotdmp.model.extension.ability.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductByModelRequest extends GenericAccountRequest {
    private String productName;
    private String productKey;
    private String description;
    private DeviceType deviceType;
    private List<ProductAccessType> accessType;
    private String productCategory;
    private List<ServiceType> extensions = Collections.emptyList();
}
