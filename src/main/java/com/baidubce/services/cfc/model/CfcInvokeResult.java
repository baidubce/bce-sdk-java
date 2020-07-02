/*
 * Copyright  2019 Baidu, Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.cfc.model;

import com.baidubce.services.cfc.CfcInputStream;

import java.io.Closeable;
import java.io.IOException;

/**
 * Contains the data returned by baidu CFC from the invoke call.
 * The result is the return of the user's function performed by baidu CFC.
 */
public class CfcInvokeResult implements Closeable {

    /**
     * Represent the HTTP response of CFC service call.
     */
    private CfcInputStream Content;

    /**
     * represent the meta data of CFC service call.
     */
    private CfcMetaData objectMetadata = new CfcMetaData();

    /**
     * Get the meta data of CFC service call.
     * @return The objectMetadata of CFC
     */
    public CfcMetaData getObjectMetadata() {
        return objectMetadata;
    }

    /**
     * Set the meta data of CFC service call.
     * @param objectMetadata The meta data of CFC service call.
     */
    public void setObjectMetadata(CfcMetaData objectMetadata) {
        this.objectMetadata = objectMetadata;
    }

    /**
     * Get the Content of CFC service call.
     * @return The Content of CFC service call.
     */
    public CfcInputStream getContent() {
        return Content;
    }

    /**
     * Set the Content of CFC service call.
     * @param content The Content of CFC service call.
     */
    public void setContent(CfcInputStream content) {
        Content = content;
    }

    @Override
    public void close() throws IOException {
        if (this.getContent() != null) {
            this.getContent().close();
        }
    }
}
