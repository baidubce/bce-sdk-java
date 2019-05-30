/*
 * Copyright 2015 Baidu, Inc.
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
package com.baidubce.services.bmr.model;

/**
 * Represent a Ranger applicaton.
 * 
 * A Ranger application can be configured with property of version.
 */
public class RangerApplicationConfig extends ApplicationConfig {
    private static final String Ranger_APPLICATION = "ranger";

    public RangerApplicationConfig() {
        this.setName(Ranger_APPLICATION);
    }

    /**
     * Configure the version of Ranger.
     * The reference version is as follows:
     *
     *     image type |  image version | Ranger version supported
     *      hadoop    |    1.2.0       |    0.11
     *
     * @param version The version of Ranger.
     * @return RangerApplicationConfig
     */
    public RangerApplicationConfig withVersion(String version) {
        this.setVersion(version);
        return this;
    }
}
