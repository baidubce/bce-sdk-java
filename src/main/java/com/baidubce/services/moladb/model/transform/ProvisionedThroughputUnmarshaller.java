package com.baidubce.services.moladb.model.transform;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.baidubce.BceClientException;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.services.moladb.model.ProvisionedThroughputDescription;
import com.fasterxml.jackson.databind.JsonNode;

public class ProvisionedThroughputUnmarshaller implements
        Unmarshaller<ProvisionedThroughputDescription, JsonNode> {

    @Override
    public ProvisionedThroughputDescription unmarshall(JsonNode jsonObj) throws Exception {
        if (!jsonObj.isObject()) {
            throw new BceClientException(
                    "input json object is not an object");
        }
        ProvisionedThroughputDescription provision = new ProvisionedThroughputDescription();
        try {
            JsonNode decTimeObj = jsonObj.get(MolaDbConstants.JSON_LAST_DECREASE_TIME);
            JsonNode incTimeObj = jsonObj.get(MolaDbConstants.JSON_LAST_INCREASE_TIME);
            JsonNode decTodayObj = jsonObj.get(MolaDbConstants.JSON_NUM_DECREASE_TODAY);
            JsonNode readCapObj = jsonObj.get(MolaDbConstants.JSON_READ_CAPACITY_UNITS);
            JsonNode writeCapObj = jsonObj.get(MolaDbConstants.JSON_WRITE_CAPACITY_UNITS);
            DateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            if (decTimeObj != null) {
                provision.setLastDescreaseDateTime(dateParser.parse(decTimeObj.asText()));
            }
            if (incTimeObj != null) {
                provision.setLastIncreaseDateTime(dateParser.parse(incTimeObj.asText()));
            }
            if (decTodayObj != null) {
                provision.setNumberOfDecreasesToday(decTodayObj.asInt());
            }
            provision.setReadCapacityUnits(readCapObj.asLong());
            provision.setWriteCapacityUnits(writeCapObj.asLong());
        } catch (Exception e) {
            throw new BceClientException("Invalid responseContent:" + jsonObj.toString() 
                                         + " meet exception:" + e.toString());
        }
        return provision;
    }
}
