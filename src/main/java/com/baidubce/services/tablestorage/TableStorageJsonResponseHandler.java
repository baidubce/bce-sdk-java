/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.tablestorage;

import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.tablestorage.model.BatchDeleteRowResponse;
import com.baidubce.services.tablestorage.model.BatchGetRowResponse;
import com.baidubce.services.tablestorage.model.BatchPutRowResponse;
import com.baidubce.services.tablestorage.model.CreateInstanceResponse;
import com.baidubce.services.tablestorage.model.CreateTableResponse;
import com.baidubce.services.tablestorage.model.DeleteRowResponse;
import com.baidubce.services.tablestorage.model.DropInstanceResponse;
import com.baidubce.services.tablestorage.model.DropTableResponse;
import com.baidubce.services.tablestorage.model.GetRowResponse;
import com.baidubce.services.tablestorage.model.ListInstanceResponse;
import com.baidubce.services.tablestorage.model.ListKeyRangesResponse;
import com.baidubce.services.tablestorage.model.ListTablesResponse;
import com.baidubce.services.tablestorage.model.PutRowResponse;
import com.baidubce.services.tablestorage.model.ScanResponse;
import com.baidubce.services.tablestorage.model.ShowInstanceResponse;
import com.baidubce.services.tablestorage.model.ShowTableResponse;
import com.baidubce.services.tablestorage.model.ShowTableStateResponse;
import com.baidubce.services.tablestorage.model.UpdateTableResponse;
import com.baidubce.services.tablestorage.model.transform.BatchGetRowResponseUnmarshaller;
import com.baidubce.services.tablestorage.model.transform.GetRowResponseUnmarshaller;
import com.baidubce.services.tablestorage.model.transform.ListInstanceResponseUnmarshaller;
import com.baidubce.services.tablestorage.model.transform.ListKeyRangesResponseUnmarshaller;
import com.baidubce.services.tablestorage.model.transform.ListTablesResponseUnmarshaller;
import com.baidubce.services.tablestorage.model.transform.ScanResponseUnmarshaller;
import com.baidubce.services.tablestorage.model.transform.ShowInstanceResponseUnmarshaller;
import com.baidubce.services.tablestorage.model.transform.ShowTableResponseUnmarshaller;
import com.baidubce.services.tablestorage.model.transform.ShowTableStateResponseUnmarshaller;

import java.io.InputStream;

/**
 * TableStorage Implementation of HttpResponseHandler. Relies on a Json unmarshaller for
 * handling the responseContent.
 */
public class TableStorageJsonResponseHandler implements HttpResponseHandler {

    /**
     * Constructs a TableStorage response handler
     */
    public TableStorageJsonResponseHandler() {

    }

    /**
     * Handle these responses returned by TableStorage.
     *
     * @param httpResponse http response returned by TableStorage.
     * @param response abstract bce response returned by TableStorage.
     * @return return whether the response has been handled well.
     * @throws Exception
     */
    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        boolean ret = true;
        InputStream content = httpResponse.getContent();
        do {
            if (response.getClass() == CreateTableResponse.class
                    || response.getClass() == UpdateTableResponse.class
                    || response.getClass() == DropTableResponse.class
                    || response.getClass() == PutRowResponse.class
                    || response.getClass() == BatchPutRowResponse.class
                    || response.getClass() == DeleteRowResponse.class
                    || response.getClass() == BatchDeleteRowResponse.class
                    || response.getClass() == CreateInstanceResponse.class
                    || response.getClass() == DropInstanceResponse.class) {
                break;
            }
            if (response.getClass() == ShowInstanceResponse.class) {
                ShowInstanceResponseUnmarshaller unmarshaller = new ShowInstanceResponseUnmarshaller(response);
                unmarshaller.unmarshall(content);
                break;
            }
            if (response.getClass() == ListInstanceResponse.class) {
                ListInstanceResponseUnmarshaller unmarshaller = new ListInstanceResponseUnmarshaller(response);
                unmarshaller.unmarshall(content);
                break;
            }
            if (response.getClass() == ListTablesResponse.class) {
                ListTablesResponseUnmarshaller unmarshaller = new ListTablesResponseUnmarshaller(response);
                unmarshaller.unmarshall(content);
                break;
            }
            if (response.getClass() == ShowTableResponse.class) {
                ShowTableResponseUnmarshaller unmarshaller = new ShowTableResponseUnmarshaller(response);
                unmarshaller.unmarshall(content);
                break;
            }
            if (response.getClass() == ShowTableStateResponse.class) {
                ShowTableStateResponseUnmarshaller unmarshaller = new ShowTableStateResponseUnmarshaller(response);
                unmarshaller.unmarshall(content);
                break;
            }
            if (response.getClass() == ListKeyRangesResponse.class) {
                ListKeyRangesResponseUnmarshaller unmarshaller = new ListKeyRangesResponseUnmarshaller(response);
                unmarshaller.unmarshall(content);
                break;
            }
            if (response.getClass() == GetRowResponse.class) {
                GetRowResponseUnmarshaller unmarshaller = new GetRowResponseUnmarshaller(response);
                unmarshaller.unmarshall(content);
                break;
            }
            if (response.getClass() == BatchGetRowResponse.class) {
                BatchGetRowResponseUnmarshaller unmarshaller = new BatchGetRowResponseUnmarshaller(response);
                unmarshaller.unmarshall(content);
                break;
            }
            if (response.getClass() == ScanResponse.class) {
                ScanResponseUnmarshaller unmarshaller = new ScanResponseUnmarshaller(response);
                unmarshaller.unmarshall(content);
                break;
            }
            ret = false;
        } while (false);

        if (content != null) {
            content.close();
        }

        return ret;
    }
}
