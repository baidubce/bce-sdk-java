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
package com.baidubce.services.bec.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * The core keywords which were used in list selection scene.
 * It is not recommended to amend the class unless if it is necessary.
 */
@Data
public class ListRequest extends AbstractBceRequest {

    /**
     * The keyword type of the query instance.
     */
    public String keywordType;

    /**
     * The keyword of the query instance.
     */
    public String keyword;

    /**
     * Page number of display page.
     */
    public int pageNo;

    /**
     * The number of services on the display page.
     */
    public int pageSize;

    /**
     * Sort, ascending/descending.
     */
    public String order;

    /**
     * Sort field.
     */
    public String orderBy;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ListRequest with credentials.
     */
    public ListRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
