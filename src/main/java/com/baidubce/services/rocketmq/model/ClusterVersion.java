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
@JsonSerialize(using = ClusterVersion.ClusterVersionSerializer.class)
@JsonDeserialize(using = ClusterVersion.ClusterVersionDeserializer.class)
public enum ClusterVersion {
    V4_9_8("4.9.8"),
    ;

    private final String value;

    ClusterVersion(String value) {
        this.value = value;
    }

    public static ClusterVersion fromValue(String value) {
        for (ClusterVersion v : values()) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("Unknown BrokerNodeType: " + value);
    }

    static class ClusterVersionSerializer extends JsonSerializer<ClusterVersion> {

        @Override
        public void serialize(ClusterVersion clusterVersion, JsonGenerator gen, SerializerProvider p)
                throws IOException {
            gen.writeString(clusterVersion.getValue());
        }
    }

    static class ClusterVersionDeserializer extends JsonDeserializer<ClusterVersion> {
        @Override
        public ClusterVersion deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
            return ClusterVersion.fromValue(p.getText());
        }
    }
}
