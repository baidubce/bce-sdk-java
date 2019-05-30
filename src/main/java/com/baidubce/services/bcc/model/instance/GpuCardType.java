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
package com.baidubce.services.bcc.model.instance;

/**
 * The optional gpu card type for gpu instance.
 */
public enum GpuCardType {
    // NVIDIA Tesla P4
    P4,

    // NVIDIA Tesla P40
    P40,

    // NVIDIA Tesla K40
    K40,

    // NVIDIA Deep Learning Development Card
    DLCard;


    public static boolean isExists(GpuCardType gpuCardType) {
        if (null == gpuCardType) {
            return false;
        }
        for (GpuCardType cardType : values()) {
            if (cardType == gpuCardType) {
                return true;
            }
        }
        return false;
    }
}
