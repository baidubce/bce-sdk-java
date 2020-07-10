/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.billing.model.finance;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

/**
 * response model for balance query
 **/
public class SupervisorBalanceResponse extends AbstractBceResponse {

    private List<SupervisorBalance> result;

    public List<SupervisorBalance> getResult() {
        return result;
    }

    public void setResult(List<SupervisorBalance> result) {
        this.result = result;
    }
}
