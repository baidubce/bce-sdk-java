package com.baidubce.services.vodpro.model.common;

import com.baidubce.BceServiceException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.google.common.collect.ImmutableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 16/06/2017 3:29 PM
 *
 * @author hejianbin
 */
@JsonSerialize(using = Path.PathSerializer.class, typing = JsonSerialize.Typing.STATIC)
@JsonDeserialize(using = Path.PathDeserializer.class)
public class Path {

    private String me;
    private String origin;
    @JsonIgnore
    private Path parent;
    private String last;
    private boolean dir;

    public String getMe() {
        return me;
    }

    public Path(String content) {
        if (content == null) {
            content = "";
        }
        if (content.length() > 255) {
            throw new BceServiceException("Content is too long: " + content);
        }
        origin = content.trim();
        String[] temp = origin.split("/");
        List<String> elements = new ArrayList<String>();
        for (String s : temp) {
            if (!s.trim().isEmpty()) {
                elements.add(s.trim());
            }
        }

        ImmutableList<String> tokens = ImmutableList.copyOf(elements);

        if (tokens.isEmpty()) {
            this.me = "";
            this.origin = me;
            this.dir = true;
            this.last = me;
            return;
        }

        for (String s : tokens) {
            if (me == null) {
                me = String.format("%s", s);
            } else {
                me = String.format("%s/%s", me, s);
            }
        }

        dir = origin.isEmpty() || origin.charAt(origin.length() - 1) == '/';
        last = tokens.get(tokens.size() - 1);
        parent = new Path(tokens.subList(0, tokens.size() - 1));
    }

    private Path(ImmutableList<String> dirs) {
        if (dirs == null || dirs.isEmpty()) {
            this.me = "";
            this.origin = me;
            this.dir = true;
            this.last = me;
        } else {
            for (String s : dirs) {
                this.me = String.format("%s/%s", me, s);
            }
            this.origin = me;
            this.last = dirs.get(dirs.size() - 1);
            this.dir = true;
            this.parent = new Path(dirs.subList(0, dirs.size() - 1));
        }
    }

    public static class PathSerializer extends StdSerializer<Path> {
        public PathSerializer() {
            this(null);
        }

        public PathSerializer(Class<Path> t) {
            super(t);
        }

        @Override
        public void serialize(Path path, JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(path.getMe());
        }
    }

    public static class PathDeserializer extends StdDeserializer<Path> {
        public PathDeserializer() {
            this(null);
        }

        public PathDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Path deserialize(JsonParser jsonParser,
                                DeserializationContext deserializationContext)
                throws IOException, JsonProcessingException {
            String content = jsonParser.getCodec().readValue(jsonParser, String.class);
            return new Path(content);
        }
    }
}
