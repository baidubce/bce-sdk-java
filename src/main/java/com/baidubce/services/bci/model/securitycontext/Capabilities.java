/*
 * Copyright (c) 2023 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.bci.model.securitycontext;

import java.util.List;
/**
 * The capabilities of container
 */
public class Capabilities {

    /**
     * The add of capabilities
     */
    private List<String> add;

    /**
     * The drop of capabilities
     */
    private List<String> drop;

    /**
     * The constructor of Capabilities
     */
    public Capabilities() {

    }

    /**
     * The constructor of Capabilities
     * @param add The add of capabilities
     * @param drop The drop of capabilities
     */
    public Capabilities(List<String> add, List<String> drop) {
        this.add = add;
        this.drop = drop;
    }

    public List<String> getAdd() {
        return add;
    }

    public Capabilities setAdd(List<String> add) {
        this.add = add;
        return this;
    }

    public List<String> getDrop() {
        return drop;
    }

    public Capabilities setDrop(List<String> drop) {
        this.drop = drop;
        return this;
    }
}
