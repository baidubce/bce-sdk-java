/*
 * Copyright 2014 Baidu, Inc.
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
 * Represent a Pig application.
 * <p>
 * A Pig application can be configured with property of version.
 */
public class PigApplicationConfig extends ApplicationConfig {
    private static final String PIG_APPLICATION = "pig";

    public PigApplicationConfig() {
        this.setName(PIG_APPLICATION);
    }

    /**
     * Configure the version of Pig.
     * The reference version is as follows:
     * <p>
     * image type |  image version | pig version supported
     * hadoop    |    0.1.0       |    0.11.0
     * hadoop    |    0.2.0       |    0.14.0
     *
     * @param version The version of Pig.
     *
     * @return PigApplicationConfig
     */
    public PigApplicationConfig withVersion(String version) {
        this.setVersion(version);
        return this;
    }
}
