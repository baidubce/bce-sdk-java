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
import com.baidubce.services.iotdmp.model.device.ResourceType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NonNull;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateProductInfoRequest extends GenericAccountRequest {

    @NonNull
    private String productName;
    private String productKey;
    private String description;
    @NonNull
    private DeviceType deviceType;
    @NonNull
    private List<String> accessType;
    private List<ResourceType> extensions;
    private String productCategory;
    private ProductType productType;

    public CreateProductInfoRequest(String productName, DeviceType deviceType, List<ProductAccessType> accessType) {
        this.productName = productName;
        this.deviceType = deviceType;
        this.accessType = convertAlias(accessType);
        this.productCategory = "用户自定义";
    }

    public CreateProductInfoRequest(String productName, DeviceType deviceType, String productCategory,
                                    List<ProductAccessType> accessType) {
        this.productName = productName;
        this.deviceType = deviceType;
        this.accessType = convertAlias(accessType);
        this.productCategory = productCategory;
    }

    public CreateProductInfoRequest(String productName, String productKey, String description,
                                    DeviceType deviceType, String productCategory, List<ProductAccessType> accessType,
                                    List<ResourceType> extensions) {
        this.productName = productName;
        this.productKey = productKey;
        this.description = description;
        this.deviceType = deviceType;
        this.accessType = convertAlias(accessType);
        this.extensions = extensions;
        this.productCategory = productCategory;
    }

    public static CreateProductInfoRequest.Builder builder() {
        return new CreateProductInfoRequest.Builder();
    }

    public static class Builder {
        private String productName;
        private String productKey;
        private String description;
        private DeviceType deviceType;
        private String productCategory;
        private List<ProductAccessType> accessType;
        private List<ResourceType> extensions;

        public Builder() { }

        public Builder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder productKey(String productKey) {
            this.productKey = productKey;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder deviceType(DeviceType deviceType) {
            this.deviceType = deviceType;
            return this;
        }

        public Builder productCategory(String productCategory) {
            this.productCategory = productCategory;
            return this;
        }

        public Builder accessType(List<ProductAccessType> accessType) {
            this.accessType = accessType;
            return this;
        }

        public Builder extensions(List<ResourceType> extensions) {
            this.extensions = extensions;
            return this;
        }

        public CreateProductInfoRequest build() {
            return new CreateProductInfoRequest(this.productName, this.productKey, this.description,
                    this.deviceType, this.productCategory, this.accessType, this.extensions);
        }
    }

    public void setAccessType(List<ProductAccessType> accessType) {
        this.accessType = convertAlias(accessType);
    }

    private List<String> convertAlias(List<ProductAccessType> accessTypes) {
        ArrayList<String> aliasAccessTypes = new ArrayList<String>();
        for (ProductAccessType productAccessType : accessTypes) {
            aliasAccessTypes.add(productAccessType.getAlias());
        }
        return aliasAccessTypes;
    }
}
