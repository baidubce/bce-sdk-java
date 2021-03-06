/*
 * Copyright 2016 Baidu, Inc.
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
package com.baidubce.services.iothub.model;

/**
 * Represent the request to list all principal in specified thing.
 */
public class ListPrincipalsRequest extends BaseRequest {
    private String thingName;

    public String getThingName() {
        return thingName;
    }

    public void setThingName(String thingName) {
        this.thingName = thingName;
    }

    public ListPrincipalsRequest withEndpointName(String endpointName) {
        this.setEndpointName(endpointName);
        return this;
    }
    public ListPrincipalsRequest withThingName(String thingName) {
        this.thingName = thingName;
        return this;
    }
}
