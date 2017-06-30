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

package com.baidubce.services.cdn.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yixing
 *
 */
public class ListDomainsResponse extends CdnResponse {
    private List<Domain> domains = new ArrayList<Domain>();
    private String marker;
    private boolean isTruncated;
    private String nextMarker;
    
    /**
     * @return domains
     */
    public List<Domain> getDomains() {
        return domains;
    }
    
    /**
     * @param domains
     */
    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }
    
    /**
     * @return marker
     */
    public String getMarker() {
        return marker;
    }
    
    /**
     * @param marker
     */
    public void setMarker(String marker) {
        this.marker = marker;
    }
    
    /**
     * @return isTruncated
     */
    public boolean isTruncated() {
        return isTruncated;
    }
    
    /**
     * @param isTruncated
     */
    public void setTruncated(boolean isTruncated) {
        this.isTruncated = isTruncated;
    }
    
    /**
     * @return nextMarker
     */
    public String getNextMarker() {
        return nextMarker;
    }
    
    /**
     * @param nextMarker
     */
    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }
}
