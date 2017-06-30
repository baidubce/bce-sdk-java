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
package com.baidubce.services.modbus.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Represent the request for Data description.
 */
public class DataDescRequest extends AbstractBceRequest {

    private String parserObjectUuid;

    private String state; // ENABLED, DISABLED

    private Integer addressStart;

    public String getParserObjectUuid() {
        return parserObjectUuid;
    }

    public void setParserObjectUuid(String parserObjectUuid) {
        this.parserObjectUuid = parserObjectUuid;
    }

    public DataDescRequest withParserObjectUuid(String parserObjectUuid) {
        this.parserObjectUuid = parserObjectUuid;
        return this;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public DataDescRequest withState(String state) {
        this.state = state;
        return this;
    }

    public Integer getAddressStart() {
        return addressStart;
    }

    public void setAddressStart(Integer addressStart) {
        this.addressStart = addressStart;
    }

    public DataDescRequest withAddressStart(Integer addressStart) {
        this.addressStart = addressStart;
        return this;
    }

    @Override
    public DataDescRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
