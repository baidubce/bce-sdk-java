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
package com.baidubce.services.bcc.model.instance;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

public class BccPriceResponse extends AbstractBceResponse {
    private List<SpecIdPrice> price;

    public List<SpecIdPrice> getPrice() {
        return price;
    }

    public void setPrice(List<SpecIdPrice> price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BccPriceResponse{" +
                "price=" + price +
                '}';
    }
}
