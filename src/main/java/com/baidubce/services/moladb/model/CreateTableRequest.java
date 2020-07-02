package com.baidubce.services.moladb.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.util.JsonUtils;

/**
 * Represents the input of a CreateTable operation.
 */
public class CreateTableRequest extends AbstractBceRequest {

    private String tableName;
    private List<KeySchemaElement> keySchema;
    private ProvisionedThroughput provisionedThroughput;
    private List<AttributeDefinition> attributeDefinitions;

    /**
     * Constructs a new CreateTableRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public CreateTableRequest() {
    }

    /**
     * Constructs a new CreateTableRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     *
     * @param name The name of the table to create.
     */
    public CreateTableRequest(String name) {
        this.tableName = name;
    }

    /**
     * Get the name of the table to create.
     *
     * @return The name of the table to create.
     */
    public String getTableName() {
        return this.tableName;
    }

    /**
     * Set the name of the table to create.
     *
     * @param tableName The name of the table to create.
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Set the name of the table to create.
     *
     * @param tableName The name of the table to create.
     * @return A reference to this object so that method calls can be chained together.
     */
    public CreateTableRequest withTableName(String tableName) {
        this.setTableName(tableName);
        return this;
    }

    /**
     * Add attributes with value type and name when creating a table.
     *
     * @param attributeDefinitions A list of attributes with name and value for creating a table, the type is
     * List&lt;AttributeDefinition&gt;.
     * @see AttributeDefinition
     */
    public void setAttributeDefinitions(List<AttributeDefinition> attributeDefinitions) {
        this.attributeDefinitions = attributeDefinitions;
    }

    /**
     * Add attributes with value type and name when creating a table.
     *
     * @param attributeDefinitions A list of attributes with name and value for creating a table, the type is
     * List&lt;AttributeDefinition&gt;.
     * @return A reference to this object so that method calls can be chained together.
     * @see AttributeDefinition
     */
    public CreateTableRequest withAttributeDefinitions(List<AttributeDefinition> attributeDefinitions) {
        this.setAttributeDefinitions(attributeDefinitions);
        return this;
    }

    /**
     * Get the attribute definitions.
     *
     * @return The attribute definitions.
     * @see AttributeDefinition
     */
    public List<AttributeDefinition> getAttributeDefinitions() {
        return this.attributeDefinitions;
    }

    /**
     * Set the Key information for creating a table, contains hashkey or hashkey and rangekey.
     * The type is List&lt;KeySchemaElement&gt; type.
     *
     * @param keySchema The Key information for creating a table, contains hashkey or hashkey and rangekey.
     * The type is List<KeySchemaElement> type.
     * @see KeySchemaElement
     */
    public void setKeySchema(List<KeySchemaElement> keySchema) {
        this.keySchema = keySchema;
    }

    /**
     * Set the Key information for creating a table, contains hashkey or hashkey and rangekey.
     * The type is List&lt;KeySchemaElement&gt; type.
     *
     * @param keySchema The Key information for creating a table, contains hashkey or hashkey and rangekey.
     * The type is List&lt;KeySchemaElement&gt; type.
     * @return A reference to this object so that method calls can be chained together.
     * @see KeySchemaElement
     */
    public CreateTableRequest withKeySchema(List<KeySchemaElement> keySchema) {
        this.setKeySchema(keySchema);
        return this;
    }

    /**
     * Get the Key information for creating a table, contains hashkey or hashkey and rangekey.
     * The type is List&lt;KeySchemaElement&gt; type.
     *
     * @return The Key information for creating a table, contains hashkey or hashkey and rangekey.
     * The type is List&lt;KeySchemaElement&gt; type.
     * @see KeySchemaElement
     */
    public List<KeySchemaElement> getKeySchema() {
        return this.keySchema;
    }

    /**
     * Set the privisioned throughput when creating a table.
     *
     * @param provision The privisioned throughtput information when creating a table.
     * @see ProvisionedThroughput
     */
    public void setProvisionedThroughput(ProvisionedThroughput provision) {
        this.provisionedThroughput = provision;
    }

    /**
     * Set the provisioned throughput when creating a table.
     *
     * @param provision The provisioned throughtput information when creating a table.
     * @return A reference to this object so that method calls can be chained together.
     * @see ProvisionedThroughput
     */
    public CreateTableRequest withProvisionedThroughput(ProvisionedThroughput provision) {
        this.setProvisionedThroughput(provision);
        return this;
    }

    /**
     * Get the privisioned throughput when creating a table.
     *
     * @return The privisioned throughtput information when creating a table.
     * @see ProvisionedThroughput
     */
    public ProvisionedThroughput getProvisionedThroughput() {
        return this.provisionedThroughput;
    }

    /**
     * Return a string representation of request information.
     *
     * @return A string representation of request information.
     */
    public String toString() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put(MolaDbConstants.JSON_ATTRIBUTE_DEFINITIONS,
                this.attributeDefinitionsToJson(this.attributeDefinitions));
        result.put(MolaDbConstants.JSON_KEY_SCHEMA, this.keySchemaToJson(this.keySchema));
        result.put(MolaDbConstants.JSON_PROVISION_THROUGHPUT,
                this.provisionedThroughput.toJsonObj());
        result.put(MolaDbConstants.JSON_TABLENAME, this.tableName);
        return JsonUtils.toJsonString(result);
    }

    @Override
    public CreateTableRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    private List<Object> keySchemaToJson(List<KeySchemaElement> schema) {
        List<Object> obj = new ArrayList<Object>();
        for (KeySchemaElement element : schema) {
            obj.add(element.toJsonObj());
        }
        return obj;
    }

    private List<Object> attributeDefinitionsToJson(List<AttributeDefinition> attributes) {
        List<Object> obj = new ArrayList<Object>();
        for (AttributeDefinition element : attributes) {
            obj.add(element.toJsonObj());
        }
        return obj;
    }
}