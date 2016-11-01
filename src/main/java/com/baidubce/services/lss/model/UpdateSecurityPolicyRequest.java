/*
 * Copyright (c) 2015 Baidu.com, Inc. All Rights Reserved
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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class UpdateSecurityPolicyRequest extends AbstractBceRequest {

    private String name = null;

    private Auth auth = null;

    private AntiLeech antiLeech = null;

    private Encryption encryption = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UpdateSecurityPolicyRequest withName(String name) {
        this.name = name;
        return this;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public UpdateSecurityPolicyRequest withAuth(Auth auth) {
        this.auth = auth;
        return this;
    }

    public AntiLeech getAntiLeech() {
        return antiLeech;
    }

    public void setAntiLeech(AntiLeech antiLeech) {
        this.antiLeech = antiLeech;
    }

    public UpdateSecurityPolicyRequest withAntiLeech(AntiLeech antiLeech) {
        this.antiLeech = antiLeech;
        return this;
    }

    public Encryption getEncryption() {
        return encryption;
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }

    public UpdateSecurityPolicyRequest withEncryption(Encryption encryption) {
        this.encryption = encryption;
        return this;
    }

    public UpdateSecurityPolicyRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class UpdateSecurityPolicyRequest {\n");
        sb.append("    name: ").append(name).append("\n");
        sb.append("    auth: ").append(auth).append("\n");
        sb.append("    antiLeech: ").append(antiLeech).append("\n");
        sb.append("    encryption: ").append(encryption).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
