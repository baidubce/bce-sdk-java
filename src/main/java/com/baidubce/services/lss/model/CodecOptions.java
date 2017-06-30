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

package com.baidubce.services.lss.model;

public class CodecOptions {
    private String profile = null;

    /**
     * Returns the profile.
     *
     * @return the profile
     **/
    public String getProfile() {
        return profile;
    }

    /**
     * Sets the profile
     *
     * @param profile the profile
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }

    /**
     * Sets the profile and returns this object.
     *
     * @param profile the profile
     * @return this object
     */
    public CodecOptions withProfile(String profile) {
        this.profile = profile;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CodecOptions {\n");
        sb.append("    profile: ").append(profile).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
