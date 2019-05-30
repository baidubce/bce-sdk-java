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
package com.baidubce.services.bcc.model.volume;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * the request for changing cds's attribute
 */
public class ModifyCdsAttrRequest extends AbstractBceRequest  {

    /**
     * specify the cds's id
     */
    private String cdsId;

    /**
     * specify the cds's new name
     */
    private String cdsName;

    /**
     * cds's new description
     */
    private String desc;

    /**
     * configure cds's id for the request
     * @param cdsId String cds's id
     * @return ModifyCdsAttrRequest
     */
    public ModifyCdsAttrRequest withCdsId(String cdsId) {
        this.cdsId = cdsId;
        return this;
    }

    /**
     * configure cds's name for the request
     * @param cdsName String
     * @return ModifyCdsAttrRequest
     */
    public ModifyCdsAttrRequest withCdsName(String cdsName) {
        this.cdsName = cdsName;
        return this;
    }

    /**
     * configure cds's description for the request
     * @param desc String
     * @return ModifyCdsAttrRequest
     */
    public ModifyCdsAttrRequest withDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String getCdsName() {
        return cdsName;
    }

    public void setCdsName(String cdsName) {
        this.cdsName = cdsName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCdsId() {
        return cdsId;
    }

    public void setCdsId(String cdsId) {
        this.cdsId = cdsId;
    }

    @Override
    public String toString() {
        return "ModifyCdsAttrRequest{cdsName='" + cdsName
                + "', desc='" + desc
                + "', cdsId='" + cdsId
                + "'}";
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ModifyCdsAttrRequest with credentials.
     */
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
