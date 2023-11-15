/*
 * Copyright 2016 Baidu, Inc.
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

package com.baidubce.services.cdn.model.domain;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.cdn.model.OriginPeer;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

/**
 * Created by sunyixing on 16/1/9.
 * Updated by changxing01 on 22/4/11
 */
public class CreateDomainRequest extends AbstractBceRequest {
    
    private List<OriginPeer> origin;
    private String domain;
    private String defaultHost;
    private String form;
    private Boolean follow302;

    /**
     * @return defaultHost
     */
    public String getDefaultHost() {
        return defaultHost;
    }

    /**
     * @param defaultHost default back source host
     */
    public void setDefaultHost(String defaultHost) {
        this.defaultHost = defaultHost;
    }

    /**
     * @param defaultHost default back source host
     * @return this object
     */
    public CreateDomainRequest withDefaultHost(String defaultHost) {
        this.defaultHost = defaultHost;
        return this;
    }

    /**
     * @return form
     */
    public String getForm() {
        return form;
    }

    /**
     * @param form business type
     */
    public void setForm(String form) {
        this.form = form;
    }

    /**
     * @param form business type
     * @return this object
     */
    public CreateDomainRequest withForm(String form) {
        this.form = form;
        return this;
    }

    /**
     * @return domain
     */
    public String getDomain() {
        return domain;
    }
    
    /**
     * @param domain the domain name
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }
    
    /**
     * @param domain the domain name
     * @return returns this object
     */
    public CreateDomainRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    /**
     * @return origin list
     */
    public List<OriginPeer> getOrigin() {
        return origin;
    }
    
    /**
     * @param origin origin list
     */
    public void setOrigin(List<OriginPeer> origin) {
        this.origin = origin;
    }
    
    /**
     * @param origin 
     * @return returns this object
     */
    public CreateDomainRequest withOrigin(List<OriginPeer> origin) {
        this.origin = origin;
        return this;
    }
    
    /**
     * @param peer
     * @return returns this object
     */
    public CreateDomainRequest addPeer(OriginPeer peer) {
        origin.add(peer);
        return this;
    }
    
    /**
     * @param peer
     * @return returns this object
     */
    public CreateDomainRequest addPeer(String peer) {
        OriginPeer op = new OriginPeer().withPeer(peer);
        origin.add(op);
        return this;
    }

    /**
     * 是否开启回源follow 302
     * @return
     */
    public Boolean getFollow302() {
        return follow302;
    }

    /**
     * 是否开启回源follow 302
     * @return
     */
    public void setFollow302(Boolean follow302) {
        this.follow302 = follow302;
    }

    /**
     * 是否开启回源follow 302
     * @return returns this object
     */
    public CreateDomainRequest withFollow302(Boolean follow302) {
        this.follow302 = follow302;
        return this;
    }
    
    /**
     *  (non-Javadoc)
     * @see com.baidubce.model.AbstractBceRequest#withRequestCredentials(com.baidubce.auth.BceCredentials)
     */
    @Override
    public CreateDomainRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     *  (non-Javadoc)
     * @see Object#toString()
     */
    @Override
    public String toString() {
        try {
            return JsonUtils.toJsonPrettyString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
    
}
