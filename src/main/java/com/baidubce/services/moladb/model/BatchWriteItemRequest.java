/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.moladb.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.baidubce.BceClientException;
import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.util.JsonUtils;

/**
 * Represents the input of a BatchWriteItem operation.
 */
public class BatchWriteItemRequest extends AbstractBceRequest {
    private Map<String, List<WriteRequest>> requestItems;

    /**
     * Constructs a new BatchWriteItemRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public BatchWriteItemRequest() {
    }

    /**
     * Return a string representation of request information.
     *
     * @return A string representation of request information.
     */
    public String toString() {
        Map<String, Object> rootObj = new HashMap<String, Object>();
        Map<String, Object> reqItems = new HashMap<String, Object>();
        Iterator<Entry<String, List<WriteRequest>>> iter = this.requestItems.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, List<WriteRequest>> entry = iter.next();
            String tableName = (String) entry.getKey();
            List<WriteRequest> val = (List<WriteRequest>) entry.getValue();
            reqItems.put(tableName, toJsonObj(val));
        }
        rootObj.put(MolaDbConstants.JSON_REQUEST_ITEMS, reqItems);
        return JsonUtils.toJsonString(rootObj);
    }
    
    /**
     * Set the request items for BatchWriteItem operation.
     * 
     * @param requestItems The request items for BatchWriteItem operation.
     * @return Returns a reference to the object so that method calls can be chained together.
     */
    public BatchWriteItemRequest withRequestItems(
            Map<String, List<WriteRequest>> requestItems) {
        this.setRequestItems(requestItems);
        return this; 
    }

    /**
     * Set the request items for BatchWriteItem operation.
     * 
     * @param requestItems The request items for BatchWriteItem operation, the type is Map.
     * @see WriteRequest
     */
    public void setRequestItems(Map<String, List<WriteRequest>> requestItems) {
        this.requestItems = requestItems;
    }
    
    /**
     * Get the request items for BatchWriteItem operation.
     * 
     * @return Returns items for BatchWriteItem operation. the type is Map.
     * @see WriteRequest
     */
    public Map<String, List<WriteRequest>> getRequestItems() {
        return this.requestItems;
    }
    
    @Override
    public BatchWriteItemRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
    
    protected List<Object> toJsonObj(List<WriteRequest> requests) {
        List<Object> objList = new ArrayList<Object>();
        for (Iterator<WriteRequest> iter = requests.iterator(); iter.hasNext();) {
            WriteRequest req = iter.next();
            if (req instanceof DeleteRequest) {
                Map<String, Object> deleteObj = new HashMap<String, Object>();
                deleteObj.put(MolaDbConstants.JSON_DELETE_REQUEST, ((DeleteRequest) req).toJsonObj());
                objList.add(deleteObj);
            } else if (req instanceof PutRequest) {
                Map<String, Object> putObj = new HashMap<String, Object>();
                putObj.put(MolaDbConstants.JSON_PUT_REQUEST, ((PutRequest) req).toJsonObj());
                objList.add(putObj);
            } else {
                throw new BceClientException("Unknow write request:" + req.toString());
            }
        }
        return objList;
    }
}
