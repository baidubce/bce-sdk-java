package com.baidubce.services.moladb.model.transform;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.baidubce.BceClientException;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.services.moladb.model.AttributeDefinition;
import com.baidubce.services.moladb.model.GetTableResponse;
import com.baidubce.services.moladb.model.KeySchemaElement;
import com.baidubce.services.moladb.model.ProvisionedThroughputDescription;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

public class DescribeTableUnmarshaller implements
        Unmarshaller<GetTableResponse, InputStream> {

    private GetTableResponse result;

    public DescribeTableUnmarshaller(AbstractBceResponse response) {
        result = (GetTableResponse) response;
    }

    @Override
    public GetTableResponse unmarshall(InputStream inputStream)
            throws Exception {
        String streamContents = Unmarshallers.readStreamContents(inputStream);

        JsonNode root = JsonUtils.jsonNodeOf(streamContents);
        if (!root.isObject()) {
            throw new BceClientException("input json object:"
                                               + root.toString()
                                               + " is not an object");
        }

        JsonNode tableObj = root;
        JsonNode attrListObj = tableObj.get(MolaDbConstants.JSON_ATTRIBUTE_DEFINITIONS);
        List<AttributeDefinition> attrDef = this.parseAttributesDefinistion(attrListObj);

        JsonNode keySchemaNode = tableObj.get(MolaDbConstants.JSON_KEY_SCHEMA);
        this.parseKeySchema(keySchemaNode, result);

        JsonNode provisionNode = tableObj.get(MolaDbConstants.JSON_PROVISION_THROUGHPUT);
        this.parseProvision(provisionNode, result);

        result.setAttributeDefinitions(attrDef);
        String time = tableObj.get(MolaDbConstants.JSON_CREATE_DATE_TIME).asText();
        DateFormat formarter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = formarter.parse(time);
        result.setCreationDateTime(date);
        result.setItemCount(tableObj.get(MolaDbConstants.JSON_ITEM_COUNT).asLong());
        result.setTableName(tableObj.get(MolaDbConstants.JSON_TABLENAME).asText());
        result.setTableStatus(tableObj.get(MolaDbConstants.JSON_TABLE_STATUS).asText());
        result.setTableSizeInBytes(tableObj.get(MolaDbConstants.JSON_TABLE_SIZE_IN_B).asLong());
        return result;
    }

    private void parseProvision(JsonNode provisionNode,
            GetTableResponse result) throws Exception {
        ProvisionedThroughputDescription provision = new ProvisionedThroughputUnmarshaller().unmarshall(provisionNode);
        result.setProvisionedThroughput(provision);
    }

    private void parseKeySchema(JsonNode keyNode, GetTableResponse result)
            throws Exception {
        List<KeySchemaElement> keySchema = new KeySchemaUnmarshaller().unmarshall(keyNode);
        result.setKeySchema(keySchema);
    }
    
    private List<AttributeDefinition> parseAttributesDefinistion(JsonNode attrListObj) {
        if (!attrListObj.isArray()) {
            throw new BceClientException("Invalid json responseContent. attributeDefinitions is not an array.");
        }
        List<AttributeDefinition> attributes = new ArrayList<AttributeDefinition>();
        Iterator<JsonNode> attrList = attrListObj.elements();
        while (attrList.hasNext()) {
            JsonNode attrObj = attrList.next();
            String name = attrObj.get(MolaDbConstants.JSON_ATTRIBUTE_NAME).asText();
            String type = attrObj.get(MolaDbConstants.JSON_ATTRIBUTE_TYPE).asText();
            AttributeDefinition attr = new AttributeDefinition();
            attr.withAttributeName(name).withAttributeType(type);
            attributes.add(attr);
        }
        return attributes;
    }
}