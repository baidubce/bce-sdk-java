/*
 * Copyright 2019 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.dugo.video.model;

/**
 * storage type
 *
 * @Author: shihuike
 * @Date: Created in 2019-08-01 16:16
 */
public enum StorageType {
    ALL(0),
    MAIN_MEM(1),
    BACKUP_MEM(2);

    private int value;

    StorageType(int value) {
        this.value = value;
    }
}
