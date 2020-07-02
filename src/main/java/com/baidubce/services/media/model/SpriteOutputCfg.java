/*
 * Copyright 2020 Baidu, Inc.
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

package com.baidubce.services.media.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @description: thumbnail sprite output configuration.
 **/

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpriteOutputCfg {
    @Min(1)
    @Max(100)
    private Integer rows = 10;

    @Min(1)
    @Max(100)
    private Integer columns = 10;

    @Min(0)
    @Max(1000)
    private Integer margin = 0;

    @Min(0)
    @Max(1000)
    private Integer padding = 0;

    private Boolean keepCellPic = true;

    @Size(min = 1, max = 100)
    private String spriteKeyTag = "-SPRITE-";
}