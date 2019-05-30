/*
 * Copyright 2014 Baidu, Inc.
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
package com.baidubce.services.bos.model;

/**
 *  For Bos Bucket Lifecycle.
 *  Operation actions performed on the resource.
 *  Required Parameters: actionName.
 *  Optional Parameters: actionStorageClass.
 */
public class Action {

    /**
     * The performed operation name.
     * Values are Transition, DeleteObject, AbortMultipartUpload.
     */
    private String name;

    /**
     * Object storage type.
     * When the action is Transition, it can be set to STANDARD_IA or COLD,
     * indicating that the transition from the original storage type to low,
     * frequency storage or cold storage.
     */
    private String storageClass;

    /**
     * Gets the name of the bucket Lifecycle, just for Lifecycle json
     * @return the name of the bucket Lifecycle.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the bucket Lifecycle, just for Lifecycle json
     * @param name of the bucket Lifecycle.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the name of the Action.
     * @param name The name of the Action
     * @return this object
     */
    public Action withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Gets the storageClass of the bucket Lifecycle, just for Lifecycle json
     * @return the storageClass of the bucket Lifecycle.
     */
    public String getStorageClass() {
        return storageClass;
    }

    /**
     * Sets the storageClass of the bucket Lifecycle Action,just for Lifecycle json
     * @param storageClass The storageClass if the Bucket Lifecycel Action.
     */
    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    /**
     * sets the storageClass of the Bucket Lifecycle Action
     * @param storageClass The storageClass if the Bucket Lifecycel Action.
     * @return this object
     */
    public Action withStorageClass(String storageClass) {
        this.storageClass = storageClass;
        return this;
    }

    @Override
    public String toString() {
        return "Action{" + "name='" + name + '\'' + ", storageClass='" + storageClass + '\'' + '}';
    }
}
