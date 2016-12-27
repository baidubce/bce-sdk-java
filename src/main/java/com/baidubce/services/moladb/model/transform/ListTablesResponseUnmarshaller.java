package com.baidubce.services.moladb.model.transform;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.baidubce.BceClientException;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.services.moladb.model.ListTablesResponse;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

public class ListTablesResponseUnmarshaller implements
        Unmarshaller<ListTablesResponse, InputStream> {

    private ListTablesResponse result = null;

    public ListTablesResponseUnmarshaller(AbstractBceResponse ret) {
        this.result = (ListTablesResponse) ret;
    }

    @Override
    public ListTablesResponse unmarshall(InputStream stream) throws Exception {

        String streamContents = Unmarshallers.readStreamContents(stream);
        if (streamContents.isEmpty()) {
            return null;
        }

        JsonNode jsonObj = JsonUtils.jsonNodeOf(streamContents);

        if (!jsonObj.isObject()) {
            throw new BceClientException("json node:" + jsonObj.toString() + "is not object");
        }
        JsonNode tablesObj = jsonObj.get(MolaDbConstants.JSON_TABLENAMES);
        if (tablesObj != null) {
            Iterator<JsonNode> namesObj = tablesObj.elements();
            List<String> names = new ArrayList<String>();
            while (namesObj.hasNext()) {
                names.add(namesObj.next().asText());
            }
            result.setTableNames(names);
        }
        return result;
    }
}
