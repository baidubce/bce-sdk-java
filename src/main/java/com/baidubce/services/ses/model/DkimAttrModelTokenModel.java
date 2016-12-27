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

import java.util.List;

/**
 * The token object of DKIM attribute.
 */
public class DkimAttrModelTokenModel {
    private List<TokenModel> keys;

    public List<TokenModel> getKeys() {
        return keys;
    }

    public void setKeys(List<TokenModel> keys) {
        this.keys = keys;
    }

    @Override
    public String toString() {
        return "DkimAttrModelTokenModel [keys=" + keys + "]";
    }

}
