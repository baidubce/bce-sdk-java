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
public class IpACL extends JsonObject {
    List<String> blackList;
    List<String> whiteList;
    
    /**
     * @return blackList
     */
    public List<String> getBlackList() {
        return blackList;
    }
    
    /**
     * @param blackList
     */
    public void setBlackList(List<String> blackList) {
        this.blackList = blackList;
    }
    
    /**
     * @param entry
     * @return returns this object
     */
    public IpACL addBlackList(String entry) {
        if (blackList == null) {
            blackList = new ArrayList<String>();
        }
        blackList.add(entry);
        return this;
    }
    
    /**
     * @return whiteList
     */
    public List<String> getWhiteList() {
        return whiteList;
    }
    
    /**
     * @param whiteList
     */
    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }
    
    /**
     * @param entry
     * @return returns this object
     */
    public IpACL addWhiteList(String entry) {
        if (whiteList == null) {
            whiteList = new ArrayList<String>();
        }
        whiteList.add(entry);
        return this;
    }
}
