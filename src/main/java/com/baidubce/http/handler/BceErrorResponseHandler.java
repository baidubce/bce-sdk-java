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
package com.baidubce.http.handler;

import java.io.InputStream;

import org.apache.http.HttpStatus;

import com.baidubce.BceErrorResponse;
import com.baidubce.BceServiceException;
import com.baidubce.BceServiceException.ErrorType;
import com.baidubce.http.BceHttpResponse;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.util.JsonUtils;

/**
 * HTTP error response handler for Baidu BCE responses.
 */
public class BceErrorResponseHandler implements HttpResponseHandler {
    
    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        if (httpResponse.getStatusCode() / 100 == HttpStatus.SC_OK / 100) {
            // not an error
            return false;
        }
        BceServiceException bse = null;
        InputStream content = httpResponse.getContent();
        if (content != null) {
            /*
             * content-length is not set in the error respond message of the media service
             */
//            if (response.getMetadata().getContentLength() > 0) {
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content));
//            String line;
//            while ((line = bufferedReader.readLine()) != null){
//                System.out.println(line);
//            }
            BceErrorResponse bceErrorResponse = JsonUtils.loadFrom(content, BceErrorResponse.class);
            if (bceErrorResponse.getMessage() != null) {
                bse = new BceServiceException(bceErrorResponse.getMessage());
                bse.setErrorCode(bceErrorResponse.getCode());
                bse.setRequestId(bceErrorResponse.getRequestId());
            }
            //            }
            content.close();
        }
        if (bse == null) {
            bse = new BceServiceException(httpResponse.getStatusText());
            bse.setRequestId(response.getMetadata().getBceRequestId());
        }
        bse.setStatusCode(httpResponse.getStatusCode());
        if (bse.getStatusCode() >= 500) {
            bse.setErrorType(ErrorType.Service);
        } else {
            bse.setErrorType(ErrorType.Client);
        }
        throw bse;
    }

}
