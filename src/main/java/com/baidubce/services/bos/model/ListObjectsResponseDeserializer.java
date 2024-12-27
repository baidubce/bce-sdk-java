package com.baidubce.services.bos.model;

import com.baidubce.http.Headers;
import com.baidubce.model.User;
import com.baidubce.util.DateUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ListObjectsResponseDeserializer extends BeanDeserializer {
    public ListObjectsResponseDeserializer() {
        super(createBeanDeserializer(ListObjectsResponse.class), true);
    }

    public ListObjectsResponseDeserializer(Class<? extends ListObjectsResponse> beanClass) {
        super(createBeanDeserializer(beanClass), true);
    }

    @Override
    public ListObjectsResponse deserialize(JsonParser parser, DeserializationContext ctx, Object bean)
            throws IOException, JsonProcessingException {
        JsonNode node = parser.getCodec().readTree(parser);
        ListObjectsResponse listObjectsResponse = (ListObjectsResponse) bean;
        if (node.has("name")) {
            listObjectsResponse.setBucketName(node.get("name").asText());
        }
        if (node.has("prefix")) {
            listObjectsResponse.setPrefix(node.get("prefix").asText());
        }
        if (node.has("delimiter")) {
            listObjectsResponse.setDelimiter(node.get("delimiter").asText());
        }
        if (node.has("marker")) {
            listObjectsResponse.setMarker(node.get("marker").asText());
        }
        if (node.has("nextMarker")) {
            listObjectsResponse.setNextMarker(node.get("nextMarker").asText());
        }
        if (node.has("maxKeys")) {
            listObjectsResponse.setMaxKeys(node.get("maxKeys").asInt());
        }
        if (node.has("isTruncated")) {
            listObjectsResponse.setTruncated(node.get("isTruncated").asBoolean());
        }

        if (node.has("contents")) {
            JsonNode contentsNode = node.get("contents");
            List<BosObjectSummary> contents = new ArrayList<BosObjectSummary>();
            for (Iterator<JsonNode> it = contentsNode.elements(); it.hasNext(); ) {
                JsonNode content = it.next();
                BosObjectSummary bosObjectSummary = new BosObjectSummary();
                bosObjectSummary.setBucketName(listObjectsResponse.getBucketName());
                if (content.has("key")) {
                    bosObjectSummary.setKey(content.get("key").asText());
                }
                if (content.has("lastModified") && !content.get("lastModified").asText().isEmpty()) {
                    bosObjectSummary.setLastModified(DateUtils.parseAlternateIso8601Date(content.get("lastModified").asText()));
                }
                if (content.has("eTag")) {
                    bosObjectSummary.setETag(content.get("eTag").asText());
                }
                if (content.has("size")) {
                    bosObjectSummary.setSize(content.get("size").asLong());
                }
                User owner = new User();
                if (content.has("owner")) {
                    if (content.get("owner").has("id")) {
                        owner.setId(content.get("owner").get("id").asText());
                    }
                    if (content.get("owner").has("displayName")) {
                        owner.setDisplayName(content.get("owner").get("displayName").asText());
                    }
                }
                bosObjectSummary.setOwner(owner);
                if (content.has("storageClass")) {
                    bosObjectSummary.setStorageClass(content.get("storageClass").asText());
                }
                if (content.has("userMeta")) {
                    Map<String, String> userMeta = new HashMap<String, String>();
                    for (Iterator<String> userMetaIterator = content.get("userMeta").fieldNames(); userMetaIterator.hasNext(); ) {
                        String name = userMetaIterator.next();
                        String userMetaKey = name;
                        if (name.startsWith(Headers.BCE_USER_METADATA_PREFIX)) {
                            userMetaKey = name.substring(Headers.BCE_USER_METADATA_PREFIX.length());
                        }
                        userMeta.put(userMetaKey, content.get("userMeta").get(name).asText());
                    }
                    bosObjectSummary.setUserMeta(userMeta);
                }
                contents.add(bosObjectSummary);
            }
            listObjectsResponse.setContents(contents);
        }

        if (node.has("commonPrefixes")) {
            JsonNode commonPrefixesNode = node.get("commonPrefixes");
            List<String> commonPrefixes = new ArrayList<String>();
            List<BosPrefixInfo> commonPrefixesWithExtMeta = new ArrayList<BosPrefixInfo>();
            for (Iterator<JsonNode> it = commonPrefixesNode.elements(); it.hasNext(); ) {
                JsonNode commonPrefix = it.next();
                if (commonPrefix.has("prefix")) {
                    commonPrefixes.add(commonPrefix.get("prefix").asText());
                }
                BosPrefixInfo bosPrefixInfo = new BosPrefixInfo();
                if (commonPrefix.has("prefix")) {
                    bosPrefixInfo.setPrefix(commonPrefix.get("prefix").asText());
                }
                if (commonPrefix.has("lastModified") && !commonPrefix.get("lastModified").asText().isEmpty()) {
                    bosPrefixInfo.setLastModified(DateUtils.parseAlternateIso8601Date(commonPrefix.get("lastModified").asText()));
                }
                if (commonPrefix.has("userMeta")) {
                    Map<String, String> userMeta = new HashMap<String, String>();
                    for (Iterator<String> userMetaIterator = commonPrefix.get("userMeta").fieldNames(); userMetaIterator.hasNext(); ) {
                        String name = userMetaIterator.next();
                        String userMetaKey = name;
                        if (name.startsWith(Headers.BCE_USER_METADATA_PREFIX)) {
                            userMetaKey = name.substring(Headers.BCE_USER_METADATA_PREFIX.length());
                        }
                        userMeta.put(userMetaKey, commonPrefix.get("userMeta").get(name).asText());
                    }
                    bosPrefixInfo.setUserMeta(userMeta);
                }
                commonPrefixesWithExtMeta.add(bosPrefixInfo);
            }
            listObjectsResponse.setCommonPrefixes(commonPrefixes);
            listObjectsResponse.setCommonPrefixesWithExtMeta(commonPrefixesWithExtMeta);
        }

        return listObjectsResponse;
    }

    private static BeanDeserializer createBeanDeserializer(Class<?> listObjectsResponse){
        try {
            ObjectMapper mapper = new ObjectMapper();
            DefaultDeserializationContext defctx = (DefaultDeserializationContext) mapper.getDeserializationContext();
            DefaultDeserializationContext ctxt = defctx.createInstance(mapper.getDeserializationConfig(), null, null);
            JavaType type = ctxt.constructType(listObjectsResponse);
            BasicBeanDescription beanDesc = (BasicBeanDescription) ctxt.getConfig().introspect(type);
            BeanDeserializerFactory factory = (BeanDeserializerFactory) ctxt.getFactory();
            BeanDeserializer beanDeserializer = (BeanDeserializer) factory.buildBeanDeserializer(ctxt, type, beanDesc);
            beanDeserializer.resolve(ctxt);
            return beanDeserializer;
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}