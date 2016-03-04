package com.baidubce.services.moladb.model.transform;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.baidubce.BceClientException;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.services.moladb.model.GetInstanceResponse;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

public class GetInstanceResponseUnmarshaller implements Unmarshaller<GetInstanceResponse, InputStream> {

    private GetInstanceResponse result;

    public GetInstanceResponseUnmarshaller(AbstractBceResponse response) {
        result = (GetInstanceResponse) response;
    }

    @Override
    public GetInstanceResponse unmarshall(InputStream inputStream)
            throws Exception {
        String streamContents = Unmarshallers.readStreamContents(inputStream);

        JsonNode root = JsonUtils.jsonNodeOf(streamContents);
        if (!root.isObject()) {
            throw new BceClientException("input json object:"
                                               + root.toString()
                                               + " is not an object");
        }

        JsonNode tableObj = root.get(MolaDbConstants.JSON_TABLENAMES);
        String desc = root.get(MolaDbConstants.JSON_DESCRIPTION).asText();
        String name = root.get(MolaDbConstants.JSON_NAME).asText();
        result.setDescription(desc);
        result.setInstanceName(name);

        List<String> tableNames = new ArrayList<String>();
        Iterator<JsonNode> tableList = tableObj.elements();
        while (tableList.hasNext()) {
            JsonNode table = tableList.next();
            tableNames.add(table.asText());
        }
        result.setTableNames(tableNames);
        return result;
    }
}