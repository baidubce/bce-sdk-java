package com.baidubce.services.moladb.model.transform;

import java.io.InputStream;
import java.util.Map;
import java.util.List;
import com.baidubce.BceClientException;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.services.moladb.model.AttributeValue;
import com.baidubce.services.moladb.model.Key;
import com.baidubce.services.moladb.model.QueryResponse;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

public class QueryResponseUnmarshaller implements
        Unmarshaller<QueryResponse, InputStream> {

    private QueryResponse result;

    public QueryResponseUnmarshaller(AbstractBceResponse response) {
        result = (QueryResponse) response;
    }

    @Override
    public QueryResponse unmarshall(InputStream inputStream)
            throws Exception {
        String streamContents = Unmarshallers.readStreamContents(inputStream);
        JsonNode root = JsonUtils.jsonNodeOf(streamContents);
        if (!root.isObject()) {
            throw new BceClientException("input json object:"
                                               + root.toString()
                                               + " is not an object");
        }

        JsonNode itemsObj = root.get(MolaDbConstants.JSON_ITEMS);
        if (itemsObj == null) {
            throw new BceClientException("Invalid response:"
                                               + root.toString()
                                               + " items is not found");
        }
        ItemListUnmarshaller itemsHelper = new ItemListUnmarshaller();
        List<Map<String, AttributeValue>> items = itemsHelper.unmarshall(itemsObj);
        result.setItems(items);

        JsonNode lastKeyObj = root.get(MolaDbConstants.JSON_LAST_EVALUATED_KEY);
        if (lastKeyObj != null) {
            KeyUnmarshaller keyHelper = new KeyUnmarshaller();
            Key lastKey = keyHelper.unmarshall(lastKeyObj); 
            result.setLastEvaluatedKey(lastKey);
        }
        return result;
    }
}
