/*
 * Copyright 2019 Baidu, Inc.
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
package com.baidubce.services.vod.model;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Gets a part of the properties of specific media resource managed by VOD service.
 */
@Data
@ToString
public class GetPartMediaResourceResponse extends AbstractBceResponse {

    /**
     * The status of media resource. Possible status are: SUCCESS FAILED PENDING RUNNING
     */
    private String status;

    /**
     * The result of get a part of the properties of specific media
     */
    private List<?> result;

}
