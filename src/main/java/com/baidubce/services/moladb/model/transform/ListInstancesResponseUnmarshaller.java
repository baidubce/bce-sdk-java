package com.baidubce.services.moladb.model.transform;

import java.util.List;
import java.util.ArrayList;
import java.io.InputStream;
import java.util.Iterator;
import com.baidubce.BceClientException;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.services.moladb.model.ListInstancesResponse;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

public class ListInstancesResponseUnmarshaller implements Unmarshaller<ListInstancesResponse, InputStream>  {

    private ListInstancesResponse result;

    public ListInstancesResponseUnmarshaller(AbstractBceResponse response) {
        result = (ListInstancesResponse) response;
    }

    @Override
    public ListInstancesResponse unmarshall(InputStream inputStream) throws Exception {
        String streamContents = Unmarshallers.readStreamContents(inputStream);

        JsonNode root = JsonUtils.jsonNodeOf(streamContents);
        if (!root.isObject()) {
            throw new BceClientException("input json object:"
                                               + root.toString()
                                               + " is not an object");
        }

        JsonNode insObj = root.get(MolaDbConstants.JSON_INSTANCE_NAMES);
        List<String> names = new ArrayList<String>();
        if (insObj != null) {
            Iterator<JsonNode> instanceList = insObj.elements();
            while (instanceList.hasNext()) {
                JsonNode ins = instanceList.next();
                names.add(ins.asText());
            }
        }
        result.setInstanceNames(names);
        return result;
    }
}
