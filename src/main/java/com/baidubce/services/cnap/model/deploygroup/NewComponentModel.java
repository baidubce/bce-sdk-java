/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.cnap.model.deploygroup;

/**
 * The model for component.
 */
public class NewComponentModel {
    /**
     * The name of component.
     * eg. lama or config.
     */
    private String name;

    /**
     * The params, eg ConfigCompnonetModel or LamaConfigModel.
     */
    private Object params;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public NewComponentModel withName(String name) {
        this.setName(name);
        return this;
    }

    public NewComponentModel withParams(Object params) {
        this.setParams(params);
        return this;
    }
}
