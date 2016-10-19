package com.baidubce.services.moladb.model;

import java.util.HashMap;
import java.util.Map;
import com.baidubce.services.moladb.MolaDbConstants;


/**
 * Represents an attribute with name and value type for a table.
 */
public class AttributeDefinition {
    private String attributeName;
    private String attributeType;
    
    /**
     * Constructs a new AttributeDefinition object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public AttributeDefinition() {
    }

    /**
     * The method set the attribute name for a new attribute.
     * 
     * @param name The name of the attribute.
     */
    public void setAttributeName(String name) {
        this.attributeName = name;
    }

    /**
     * The method set the attribute name for a new attribute.
     * 
     * @param name The name of the attribute.
     */
    public AttributeDefinition withAttributeName(String name) {
        this.setAttributeName(name);
        return this;
    }

    /**
     * The method get the attribute name of a new attribute.
     * 
     * @return The name of the attribute.
     */
    public String getAttributeName() {
        return this.attributeName;
    }
    
    /**
     * The method set the attribute value type for a new attribute.
     * 
     * @param type The value type of the attribute, the value is "N","S", or "B".
     */
    public void setAttributeType(String type) {
        this.attributeType = type;
    }

    /**
     * The method set the attribute value type for a new attribute.
     * 
     * @param type The value type of the attribute, the value is "N","S", or "B".
     */
    public AttributeDefinition withAttributeType(String type) {
        this.setAttributeType(type);
        return this;
    }

    /**
     * The method get the attribute value type of a new attribute.
     * 
     * @return The value type of the attribute, the value is "N","S", or "B".
     */
    public String getAttributeType() {
        return this.attributeType;
    }

    protected Object toJsonObj() {
        if (this.attributeName == null) {
            throw new NullPointerException("Attribute name must be provided "
                    + "when definition an attribute in table");
        }
        if (this.attributeType == null) {
            throw new NullPointerException("Attribute type must be provided "
                    + "when definition an attribute in table");
        }
        Map<String, String> obj = new HashMap<String, String>();
        obj.put(MolaDbConstants.JSON_ATTRIBUTE_NAME, this.attributeName);
        obj.put(MolaDbConstants.JSON_ATTRIBUTE_TYPE, this.attributeType);
        return obj;
    }
}
