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
package com.baidubce.services.ses.model;

/**
 * Request object to set feedback. It contains lots of parameters to set.
 */
public class SetFeedbackRequest extends SesRequest {
    /**
     * The type to set.
     */
    private Integer type;
    /**
     * True means enabled, false means disabled.
     */
    private boolean enabled;
    /**
     * The URL of email to set.
     */
    private String email;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SetFeedbackRequest [type=" + type + ", enabled=" + enabled + ", email=" + email + "]";
    }

}
