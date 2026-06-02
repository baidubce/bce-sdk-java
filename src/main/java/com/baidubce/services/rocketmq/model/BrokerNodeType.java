package com.baidubce.services.rocketmq.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.io.IOException;

@Getter
@JsonSerialize(using = BrokerNodeType.BrokerNodeTypeSerializer.class)
@JsonDeserialize(using = BrokerNodeType.BrokerNodeTypeDeserializer.class)
public enum BrokerNodeType {
    ROCKETMQ_G5_2C_8G("rocketmq.g5.c2m8"),
    ROCKETMQ_G5_4C_16G("rocketmq.g5.c4m16"),
    ROCKETMQ_G5_8C_32G("rocketmq.g5.c8m32"),
    ROCKETMQ_G5_16C_64G("rocketmq.g5.c16m64"),
    ROCKETMQ_G5_24C_96G("rocketmq.g5.c24m96"),
    ROCKETMQ_G5_32C_128G("rocketmq.g5.c32m128")
    ;

    private final String value;

    BrokerNodeType(String value) {
        this.value = value;
    }

    public static BrokerNodeType fromValue(String value) {
        for (BrokerNodeType t : values()) {
            if (t.getValue().equals(value)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Unknown BrokerNodeType: " + value);
    }

    static class BrokerNodeTypeSerializer extends JsonSerializer<BrokerNodeType> {

        @Override
        public void serialize(BrokerNodeType nodeType, JsonGenerator gen, SerializerProvider p)
                throws IOException {
            gen.writeString(nodeType.getValue());
        }
    }

    static class BrokerNodeTypeDeserializer extends JsonDeserializer<BrokerNodeType> {
        @Override
        public BrokerNodeType deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
            return BrokerNodeType.fromValue(p.getText());
        }
    }
}
