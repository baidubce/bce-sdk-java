package com.baidubce.services.bes.v2.model.renew;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * 服务端在部分场景下会把 result 返回为空字符串（"result":""）而非 Result 对象或 null。
 * Jackson 默认无法把空字符串 coerce 成对象类型，这里把空字符串场景归一化为 null。
 */
public class AutoRenewRuleCreateResultDeserializer extends JsonDeserializer<AutoRenewRuleCreateResponse.Result> {
    @Override
    public AutoRenewRuleCreateResponse.Result deserialize(JsonParser jsonParser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        if (node == null || node.isNull() || (node.isTextual() && node.asText().isEmpty())) {
            return null;
        }
        return jsonParser.getCodec().treeToValue(node, AutoRenewRuleCreateResponse.Result.class);
    }
}
