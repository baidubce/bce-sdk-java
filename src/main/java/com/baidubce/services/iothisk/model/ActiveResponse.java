/*
 * Copyright 2018 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iothisk.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * Represent the response of HISK device id activation.
 */
public class ActiveResponse extends AbstractBceResponse {

    /**
     *  Device id activation result data, which normally is the corresponding device id.
     */
    private String data;

    /**
     *  Sdk type, which is parsed from active data.
     */
    private int sdkType;

    /**
     * Get the activation result data.
     *
     * @return activation result data.
     */
    public String getData() {
        return data;
    }

    /**
     * Set the activation result data.
     *
     * @param data activation result data.
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Get the sdk type.
     *
     * @return device sdk type.
     */
    public int getSdkType() {
        return sdkType;
    }

    /**
     * Set the sdk type.
     *
     * @param sdkType device sdk type.
     */
    public void setSdkType(int sdkType) {
        this.sdkType = sdkType;
    }

}
