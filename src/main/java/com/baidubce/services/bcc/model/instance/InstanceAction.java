/*
 * Copyright (c) 2014-2019 Baidu.com, Inc. All Rights Reserved
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
 * The action for operating the instance.
 */
public enum  InstanceAction {
    /**
     * The action to start the instance.
     */
    start,

    /**
     * The action to stop the instance.
     */
    stop,

    /**
     * The action to reboot the instance.
     */
    reboot,

    /**
     * The action to change the admin password of the instance.
     */
    changePass,

    /**
     * The action to modify the attribute of the instance.
     */
    modifyAttribute,

    /**
     * The action to rename the bbc instance
     */
    rename,

    /**
     * The action to modify the desc of the instance.
     */
    modifyDesc,

    /**
     * The action to rebuild the instance.
     */
    rebuild,

    /**
     * The action to resize the instance.
     */
    resize,

    /**
     * The action to bind the instance to specified securitygroup.
     */
    bind,

    /**
     * The action to unbind the instance from securitygroup.
     */
    unbind,

    /**
     * The action to purchaseReserved the instance.
     */
    purchaseReserved,

    /**
     * The action to update bcc desc
     */
    updateDesc
}
