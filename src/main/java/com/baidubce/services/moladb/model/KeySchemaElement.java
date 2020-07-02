package com.baidubce.services.moladb.model;

import java.util.HashMap;
import java.util.Map;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.util.JsonUtils;

/**
 * Represents the key schema for a table. A key schema can be a hashkey, or a hashkey and a rangekey.
 * For a hashkey or a rangekey, there is an attribute name and corresponding attribute value for it.
 */
public class KeySchemaElement {
    public static final String HASH_KEY_TYPE = MolaDbConstants.JSON_HASH_KEY;
    public static final String RANGE_KEY_TYPE = MolaDbConstants.JSON_RANGE_KEY;
    private String attributeName;
    private String keyType;

    /**
     * Constructs a new KeySchemaElement object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public KeySchemaElement() {
    }
    
    /**
     * Constructs a new KeySchemaElement object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     *
     * @param attributeName The attribute name for a key.
     * @param keyType The type of the key, it is hashKey or rangeKey.
     */
    public KeySchemaElement(String attributeName, String keyType) {
        this.attributeName = attributeName;
        this.keyType = keyType;
    }

    /**
     * Set the attribute name for the key schema.
     *
     * @param name The attribute name for a key to be set.
     */
    public void setAttributeName(String name) {
        this.attributeName = name;
    }
    
    /**
     * Set the attribute name for the key schema.
     *
     * @param name The attribute name for a key to be set.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     */
    public KeySchemaElement withAttributeName(String name) {
        this.setAttributeName(name);
        return this;
    }
    
    /**
     * Get the attribute name for the key schema.
     *
     * @return The attribute name for a key.
     */
    public String getAttributeName() {
        return this.attributeName;
    }
    
    /**
     * Set the key type for the key schema.
     *
     * @param type The key type name for the key schema.
     * @throws IllegalArgumentException
     * if the key type is not HASH_KEY_TYPE or RANGE_KEY_TYPE.
     */
    public void setKeyType(String type) {
        this.keyType = type;
    }

    /**
     * Set the key type for the key schema.
     *
     * @param type The key type name for the key schema.
     * @return Returns a reference to this object so that method calls can be chained together.
     */
    public KeySchemaElement withKeyType(String type) {
        this.setKeyType(type);
        return this;
    }
    
    /**
     * Get the key type for the key schema.
     *
     * @return The key type name for the key schema.
     */
    public String getKeyType() {
        return this.keyType;
    }

    protected Object toJsonObj() {
        if (this.keyType == null) {
            throw new NullPointerException(
                    "Invalid key schema element. Key type is null");
        }
        if (this.attributeName == null) {
            throw new NullPointerException(
                    "Invalid key schema element. Attribute name is null");
        }

        Map<String, String> obj = new HashMap<String, String>();
        obj.put(MolaDbConstants.JSON_ATTRIBUTE_NAME, this.attributeName);
        obj.put(MolaDbConstants.JSON_KEY_TYPE, this.keyType);
        return obj;
    }

    /**
     * Return a string representation of the object.
     *
     * @return A string representation of the object.
     */
    public String toString() {
        return JsonUtils.toJsonString(this.toJsonObj());
    }
}
