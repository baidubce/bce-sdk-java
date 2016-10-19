package com.baidubce.services.moladb.model.transform;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.baidubce.BceClientException;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.services.moladb.model.KeySchemaElement;
import com.fasterxml.jackson.databind.JsonNode;

public class KeySchemaUnmarshaller implements
        Unmarshaller<List<KeySchemaElement>, JsonNode> {

    @Override
    public List<KeySchemaElement> unmarshall(JsonNode jsonObj) throws Exception {
        if (!jsonObj.isArray()) {
            throw new BceClientException(
                    "input json object is not an array");
        }
        List<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
        Iterator<JsonNode> keyList = jsonObj.elements();
        while (keyList.hasNext()) {
            JsonNode keyObj = keyList.next();
            String name = keyObj.get(MolaDbConstants.JSON_ATTRIBUTE_NAME).asText();
            String type = keyObj.get(MolaDbConstants.JSON_KEY_TYPE).asText();
            KeySchemaElement element = new KeySchemaElement();
            element.setAttributeName(name);
            element.setKeyType(type);
            keySchema.add(element);
        }
        return keySchema;
    }
}
