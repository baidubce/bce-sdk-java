/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bcc.model.flavor;

import java.util.ArrayList;
import java.util.List;

/**
 * The information of the BCC bidding instance resource.
 */
public class BccBidResources {

    /**
     * The parameter to specified which kind of instance is return.
     * see all of supported instance type in {@link com.baidubce.services.bcc.model.instance.InstanceType}
     */
    private String instanceType;

    private List<BccBidFlavor> flavors = new ArrayList<BccBidFlavor>();

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public List<BccBidFlavor> getFlavors() {
        return flavors;
    }

    public void setFlavors(List<BccBidFlavor> flavors) {
        this.flavors = flavors;
    }

    @Override
    public String toString() {
        return "BccResources{" +
                "instanceType='" + instanceType + "\'" +
                "flavors=" + flavors +
                '}';
    }
}
