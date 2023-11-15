/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * The request for creating new volume.
 */
@Data
public class CreateVolumeResponse extends AbstractBceResponse {
    /**
     * List of the id of volumes created.
     */
    private List<String> volumeIds;

    private List<Volume> volumes;

    @Data
    private static class Volume {
        private String volumeId;
        private String volumeUuid;
        private String name;
        private Integer sizeGb;
    }

}
