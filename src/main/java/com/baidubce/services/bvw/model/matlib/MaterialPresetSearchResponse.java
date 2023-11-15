/*
 * Copyright 2019-2021 Baidu, Inc.
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
package com.baidubce.services.bvw.model.matlib;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * The response of searching material from material library.
 */
@Data
public class MaterialPresetSearchResponse extends AbstractBceResponse {

    private List<PresetResponseWrapper> result;

    private int pageNo = 1;
    private int pageSize = 0;
    private long totalCount = 0L;

    @Data
    public static class PresetResponseWrapper {
        private String type;
        private List<MaterialPresetGetResponse> addons;
    }

}
