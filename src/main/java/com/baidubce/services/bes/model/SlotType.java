/**
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
package com.baidubce.services.bes.model;

public enum SlotType {
    // 老套餐
    CALCULATE_V1("calculate_v1"), // CPU 4核, 内存 32G, SSD 云磁盘100G
    CALCULATE_V2("calculate_v2"), // CPU 16核, 内存 64G, SSD 云磁盘200G
    CALCULATE_V3("calculate_v3"), // CPU 32核, 内存 128G, SSD 云磁盘500G
    STORAGE_V1("storage_v1"), // CPU 4核, 内存 16G, SSD 云磁盘500G
    STORAGE_V2("storage_v2"), // CPU 8核, 内存 32G, SSD 云磁盘1024G
    STORAGE_V3("storage_v3"), // CPU 16核, 内存 64G, SSD 云磁盘2048G

    // 新套餐（es_node）
    // 通用型
    BES_G3_CPU_2_MEM_8("bes.g3.c2m8"), // CPU 2核, 内存 8G
    BES_G3_CPU_4_MEM_16("bes.g3.c4m16"), // CPU 4核, 内存 16G
    BES_G3_CPU_8_MEM_32("bes.g3.c8m32"), // CPU 8核, 内存 32G
    BES_G3_CPU_16_MEM_64("bes.g3.c16m64"), // CPU 16核, 内存 64G
    BES_G3_CPU_32_MEM_128("bes.g3.c32m128"), // CPU 32核, 内存 128G

    // 计算型
    BES_C3_CPU_2_MEM_4("bes.c3.c2m4"), // CPU 2核, 内存 4G
    BES_C3_CPU_4_MEM_8("bes.c3.c4m8"), // CPU 4核, 内存 8G
    BES_C3_CPU_8_MEM_16("bes.c3.c8m16"), // CPU 8核, 内存 16G
    BES_C3_CPU_16_MEM_32("bes.c3.c16m32"), // CPU 16核, 内存 32G
    BES_C3_CPU_32_MEM_64("bes.c3.c32m64"), // CPU 32核, 内存 64G

    // 密集计算型
    BES_IC3_CPU_4_MEM_4("bes.ic3.c4m4"), // CPU 4核, 内存 4G
    BES_IC3_CPU_8_MEM_8("bes.ic3.c8m8"), // CPU 8核, 内存 8G
    BES_IC3_CPU_12_MEM_12("bes.ic3.c12m12"), // CPU 12核, 内存 12G
    BES_IC3_CPU_16_MEM_16("bes.ic3.c16m16"), // CPU 16核, 内存 16G

    // 内存型
    BES_M3_CPU_2_MEM_16("bes.m3.c2m16"), // CPU 2核, 内存 16G
    BES_M3_CPU_4_MEM_32("bes.m3.c4m32"), // CPU 4核, 内存 32G
    BES_M3_CPU_8_MEM_64("bes.m3.c8m64"), // CPU 8核, 内存 64G

    // 新套餐（kibana）
    // 通用型
    KIBANA_G3_CPU_2_MEM_8("bes.g3.c2m8"), // CPU 2核, 内存 8G
    KIBANA_G3_CPU_4_MEM_16("bes.g3.c4m16"), // CPU 4核, 内存 16G
    KIBANA_G3_CPU_8_MEM_32("bes.g3.c8m32"), // CPU 8核, 内存 32G

    // 计算型
    KIBANA_C3_CPU_1_MEM_2("bes.c3.c1m2"), // CPU 1核, 内存 2G (免费套餐)
    KIBANA_C3_CPU_2_MEM_4("bes.c3.c2m4"), // CPU 2核, 内存 4G
    KIBANA_C3_CPU_4_MEM_8("bes.c3.c4m8"), // CPU 4核, 内存 8G
    KIBANA_C3_CPU_8_MEM_16("bes.c3.c8m16"); // CPU 8核, 内存 16G

    private String slotType;

    SlotType(String slotType) {
        this.slotType = slotType;
    }

    public String getSlotType() {
        return slotType;
    }
}
