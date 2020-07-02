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
package com.baidubce.services.bcc.model.instance;

/**
 * The optional flag for instance related renew.
 */
public enum RelatedRenewFlagType {

    /**
     * Only renew the prepaid CDS which is associated with the BCC instance.
     */
    CDS,

    /**
     * Only renew the prepaid EIP which is associated with the BCC instance.
     */
    EIP,

    /**
     * Only renew the prepaid MKT which is associated with the BCC instance.
     */
    MKT,

    /**
     * Only renew the prepaid CDS、EIP which are associated with the BCC instance.
     */
    CDS_EIP,

    /**
     * Only renew the prepaid CDS、MKT which are associated with the BCC instance.
     */
    CDS_MKT,

    /**
     * Only renew the prepaid EIP、MKT which are associated with the BCC instance.
     */
    EIP_MKT,

    /**
     * Only renew the prepaid CDS、EIP、MKT which are associated with the BCC instance.
     */
    CDS_EIP_MKT;

    public static boolean isExists(RelatedRenewFlagType relatedRenewFlagType) {
        if (null == relatedRenewFlagType) {
            return false;
        }
        for (RelatedRenewFlagType unitType : values()) {
            if (unitType == relatedRenewFlagType) {
                return true;
            }
        }
        return false;
    }
}
