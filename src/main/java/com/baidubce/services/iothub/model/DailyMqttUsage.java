package com.baidubce.services.iothub.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.joda.time.LocalDate;

import java.io.IOException;

public class DailyMqttUsage {

    private static class LocalDateSerializer extends JsonDeserializer<LocalDate> {

        @Override
        public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException {
            return new LocalDate(jsonParser.getValueAsString());
        }
    }

    @JsonDeserialize(using = LocalDateSerializer.class)
    private LocalDate date;

    private MqttUsage usage;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public MqttUsage getUsage() {
        return usage;
    }

    public void setUsage(MqttUsage usage) {
        this.usage = usage;
    }
}
