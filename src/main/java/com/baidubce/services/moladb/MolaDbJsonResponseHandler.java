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
package com.baidubce.services.moladb;

import java.io.InputStream;
import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.moladb.model.BatchGetItemResponse;
import com.baidubce.services.moladb.model.BatchWriteItemResponse;
import com.baidubce.services.moladb.model.CreateInstanceResponse;
import com.baidubce.services.moladb.model.CreateTableResponse;
import com.baidubce.services.moladb.model.DeleteInstanceResponse;
import com.baidubce.services.moladb.model.DeleteItemResponse;
import com.baidubce.services.moladb.model.DeleteTableResponse;
import com.baidubce.services.moladb.model.GetInstanceResponse;
import com.baidubce.services.moladb.model.GetTableResponse;
import com.baidubce.services.moladb.model.GetItemResponse;
import com.baidubce.services.moladb.model.ListInstancesResponse;
import com.baidubce.services.moladb.model.ListTablesResponse;
import com.baidubce.services.moladb.model.PutItemResponse;
import com.baidubce.services.moladb.model.QueryResponse;
import com.baidubce.services.moladb.model.UpdateItemResponse;
import com.baidubce.services.moladb.model.UpdateTableResponse;
import com.baidubce.services.moladb.model.transform.BatchGetItemResponseUnmarshaller;
import com.baidubce.services.moladb.model.transform.BatchWriteItemResponseUnmarshaller;
import com.baidubce.services.moladb.model.transform.DescribeTableUnmarshaller;
import com.baidubce.services.moladb.model.transform.GetInstanceResponseUnmarshaller;
import com.baidubce.services.moladb.model.transform.GetItemResponseUnmarshaller;
import com.baidubce.services.moladb.model.transform.ListInstancesResponseUnmarshaller;
import com.baidubce.services.moladb.model.transform.ListTablesResponseUnmarshaller;
import com.baidubce.services.moladb.model.transform.QueryResponseUnmarshaller;

/**
 * Moladb Implementation of HttpResponseHandler. Relies on a Json unmarshaller for
 * handling the responseContent.
 */
public class MolaDbJsonResponseHandler implements HttpResponseHandler {

    /** The JSON unmarshaller to use when handling the responseContent */
    public MolaDbJsonResponseHandler() {

    }

    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        boolean ret = false;
        InputStream content = httpResponse.getContent();
        do {
            ret = true;
            if (response.getClass() == PutItemResponse.class
                    || response.getClass() == CreateInstanceResponse.class
                    || response.getClass() == DeleteInstanceResponse.class
                    || response.getClass() == DeleteItemResponse.class
                    || response.getClass() == UpdateItemResponse.class
                    || response.getClass() == CreateTableResponse.class
                    || response.getClass() == DeleteTableResponse.class
                    || response.getClass() == UpdateTableResponse.class) {
                break;
            } else if (response.getClass() == GetItemResponse.class) {
                GetItemResponseUnmarshaller unmarshaller = new GetItemResponseUnmarshaller(response);
                unmarshaller.unmarshall(content);
                break;
            } else if (response.getClass() == BatchGetItemResponse.class) {
                BatchGetItemResponseUnmarshaller unmarshaller = new BatchGetItemResponseUnmarshaller(response);
                unmarshaller.unmarshall(content);
                break;
            } else if (response.getClass() == BatchWriteItemResponse.class) {
                BatchWriteItemResponseUnmarshaller unmarshaller = new BatchWriteItemResponseUnmarshaller(response);
                unmarshaller.unmarshall(content);
                break;
            } else if (response.getClass() == ListTablesResponse.class) {
                ListTablesResponseUnmarshaller unmarshaller = new ListTablesResponseUnmarshaller(response);
                unmarshaller.unmarshall(content);
                break;
            } else if (response.getClass() == GetTableResponse.class) {
                DescribeTableUnmarshaller unmarshaller = new DescribeTableUnmarshaller(response);
                unmarshaller.unmarshall(content);
                break;
            } else if (response.getClass() == QueryResponse.class) {
                QueryResponseUnmarshaller unmarshaller = new QueryResponseUnmarshaller(response);
                unmarshaller.unmarshall(content);
                break;
            } else if (response.getClass() == GetInstanceResponse.class) {
                GetInstanceResponseUnmarshaller unmarshaller = new GetInstanceResponseUnmarshaller(response);
                unmarshaller.unmarshall(content);
                break;
            } else if (response.getClass() == ListInstancesResponse.class) {
                ListInstancesResponseUnmarshaller unmarshaller = new ListInstancesResponseUnmarshaller(response);
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