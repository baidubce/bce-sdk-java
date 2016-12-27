/*
 * Copyright 2014 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.moladb.model;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import com.baidubce.util.JsonUtils;

/**
 * Represents the value for an attribute.
 */
public class AttributeValue {
    public static final String ATTRIBUTE_TYPE_NUMBER = "N";
    public static final String ATTRIBUTE_TYPE_STRING = "S";
    public static final String ATTRIBUTE_TYPE_BINARY = "B";

    private String attributeType;
    private String attributeValue;

    /**
     * Constructs a new AttributeValue object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public AttributeValue() {
        attributeType = null;
        attributeValue = "";
    }

    /**
     * Constructs a new AttributeValue object and init the value type as String
     * 
     * @param s The initial value of AttributeValue
     */
    public AttributeValue(String s) {
        attributeType = AttributeValue.ATTRIBUTE_TYPE_STRING;
        attributeValue = s;
    }
    
    /**
     * Constructs a new AttributeValue object and init the value type as Number
     * 
     * @param d The initial value of AttributeValue
     */
    public AttributeValue(double d) {
        attributeType = AttributeValue.ATTRIBUTE_TYPE_NUMBER;
        attributeValue = Double.toString(d);
    }
    
    /**
     * Constructs a new AttributeValue object and init the value type as Number 
     * 
     * @param n The initial value of AttributeValue
     */
    public AttributeValue(long n) {
        attributeType = AttributeValue.ATTRIBUTE_TYPE_NUMBER;
        attributeValue = Long.toString(n);
    }
    
    /**
     * Constructs a new AttributeValue object and init the value type as Binary 
     * 
     * @param buffer The input byte buffer for AttributeValue
     */
    public AttributeValue(byte[] buffer) {
        this.setB(buffer);
    }

    /**
     * The method return the attribute value in byte array.
     * Note: This method will decode the attribute value with base64 first.
     *
     * @return Attribute value in byte array.
     */
    public byte[] getB() {
        return Base64.decodeBase64(this.attributeValue);
    }

    /**
     * The method set value type as binary and set the value with input parameter.
     * Input byte array will be encoded with base64.
     * a reference to this object so that method calls can be chained
     * together.
     *
     * @param buffer The value to be set.
     * @return A reference to this object so that method calls can be chained together
     */
    public AttributeValue withB(byte[] buffer) {
        this.setB(buffer);
        return this;
    }
    
    /**
     * The method set value type as binary and set the value with input parameter.
     * Note: this method will encode the byte array with base64 first.
     *
     * @param buffer The value to be set.
     */
    public void setB(byte[] buffer) {
        setValue(AttributeValue.ATTRIBUTE_TYPE_BINARY,
                 Base64.encodeBase64String(buffer));
    }

    /**
     * The method return the attribute value in String.
     *
     * @return Attribute value in String.
     */
    public String getN() {
        return this.attributeValue;
    }
    
    /**
     * The method return the attribute value in Long.
     *
     * @return Attribute value in long.
     */
    public long getLong() {
        return Long.parseLong(this.attributeValue);
    }
    
    /**
     * The method return the attribute value in Double.
     *
     * @return Attribute value in double.
     */
    public double getDouble() {
        return Double.parseDouble(this.attributeValue);
    }
    
    /**
     * The method set value type as number and set the value with input parameter.
     *
     * @param n The value to be set.
     * @return A reference to this object so that method calls can be chained together
     */
    public AttributeValue withN(String n) {
        this.setN(n);
        return this;
    }
    
    /**
     * The method set value type as number and set the value with input parameter.
     *
     * @param n The value to be set.
     * @return A reference to this object so that method calls can be chained together
     */
    public AttributeValue withN(long n) {
        this.setN(Long.toString(n));
        return this;
    }
    
    /**
     * The method set value type as number and set the value with input parameter.
     *
     * @param n The value to be set.
     * @return A reference to this object so that method calls can be chained together
     */
    public AttributeValue withN(double n) {
        this.setN(Double.toString(n));
        return this;
    }
    
    /**
     * The method set value type as number and set the value with input parameter.
     *
     * @param n The value to be set.
     */
    public void setN(String n) {
        this.setValue(AttributeValue.ATTRIBUTE_TYPE_NUMBER, n);
    }
    
    /**
     * The method set value type as number and set the value with input parameter.
     *
     * @param n The value to be set.
     */
    public void setN(long n) {
        this.setValue(AttributeValue.ATTRIBUTE_TYPE_NUMBER, Long.toString(n));
    }
    
    /**
     * The method set value type as number and set the value with input parameter.
     *
     * @param n The value to be set.
     */
    public void setN(double n) {
        this.setValue(AttributeValue.ATTRIBUTE_TYPE_NUMBER, Double.toString(n));
    }
    
    /**
     * The method return the attribute value in String.
     *
     * @return The attribute value in String.
     */
    public String getS() {
        return this.attributeValue;
    }

    /**
     * The method set value type as String and set the value with input parameter.
     *
     * @param s The value to be set.
     * @return A reference to this object so that method calls can be chained together
     */
    public AttributeValue withS(String s) {
        this.setS(s);
        return this;
    }
    
    /**
     * The method set value type as String and set the value with input parameter.
     *
     * @param s The value to be set.
     */
    public void setS(String s) {
        setValue(AttributeValue.ATTRIBUTE_TYPE_STRING, s);
    }

    /**
     * The method return the attribute value type.
     *
     * @return Attribute value type.
     */
    public String getAttributeType() {
        return attributeType;
    }
    
    /**
     * The method return the attribute value.
     *
     * @return Attribute value type.
     */
    public String getAttributeValue() {
        return attributeValue;
    }
    
    /**
     * Return a string representation of the object.
     *
     * @return A string representation of the object.
     */
    public String toString() {
        return JsonUtils.toJsonString(this.toJsonObj());
    }

    /**
     * The method set value type and value with input parameter.
     * If type is "B" (binary), the corresponding value MUST be encoded with base64.
     *
     * @param type The attribute type to be set.
     * @param value The value to be set.
     */
    public void setValue(String type, String value) {
        attributeType = type;
        attributeValue = value;
    }

    protected Map<String, String> toJsonObj() {
        Map<String, String> jsonObj = new HashMap<String, String>();
        jsonObj.put(attributeType, attributeValue);
        return jsonObj;
    }
}
