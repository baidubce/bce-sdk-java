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
package com.baidubce.services.cfc;

import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

/**
 * The input stream for CFC service calls.
 */
public class CfcInputStream extends FilterInputStream {
    private CloseableHttpResponse httpResponse;

    public CfcInputStream(InputStream in, CloseableHttpResponse httpResponse) {
        super(in);
        this.httpResponse = httpResponse;
    }

    public void close() throws IOException {
        this.httpResponse.close();
        try {
            super.close();
        } catch (SocketException e) {
            // expected from some implementations because the stream is closed
        }
    }
}
