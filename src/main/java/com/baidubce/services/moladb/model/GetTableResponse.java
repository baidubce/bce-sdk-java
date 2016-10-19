package com.baidubce.services.moladb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.util.JsonUtils;

/**
 * Represents the output of a GetTable operation.
 */
public class GetTableResponse extends AbstractBceResponse {
    private ProvisionedThroughputDescription provisionedThroughput;
    private String tableName;
    private Long tableSizeInBytes = 0L;
    private String tableStatus;
    private List<KeySchemaElement> keySchema;
    private Long itemCount;
    private Date creationDateTime;
    private List<AttributeDefinition> attributeDefinitions;

    /**
     * Constructs a new GetTableResponse object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public GetTableResponse() {
    }
    

    /**
     * Get all of the attributes for this table.
     * 
     * @return All of the attributes for this table, the type is in AttributesDefinition type.
     * @see AttributeDefinition
     */
    public List<AttributeDefinition> getAttributeDefinitions() {
        return this.attributeDefinitions;
    }
    
    /**
     * Set all of the attributes for this table.
     * 
     * @param attributeDefinitions All of the attributes for this table, the type is in AttributesDefinition type.
     * @see AttributeDefinition
     */
    public void setAttributeDefinitions(List<AttributeDefinition> attributeDefinitions) {
        this.attributeDefinitions = attributeDefinitions;
    }
    
    /**
     * Get the provisioned throughput setting for this table from server side.
     * 
     * @return The provisioned throughput setting for this table from server side.
     */
    public ProvisionedThroughputDescription getProvisionedThroughput() {
        return this.provisionedThroughput;
    }

    /**
     * Set the provisioned throughput setting for this table from server side.
     * 
     * @param other The provisioned throughput setting for this table from server side.
     */
    public void setProvisionedThroughput(ProvisionedThroughputDescription other) {
        this.provisionedThroughput = other;
    }

    /**
     * Get the name of the requested table.
     * 
     * @return The name of the requested table.
     */
    public String getTableName() {
        return this.tableName;
    }

    /**
     * Set the name of the requested table.
     * 
     * @param name The name of the requested table.
     */
    public void setTableName(String name) {
        this.tableName = name;
    }

    /**
     * Set the status of the requested table.
     * 
     * @param status The status of the requested table.
     */
    public void setTableStatus(String status) {
        this.tableStatus = status;
    }

    /**
     * Get the status of the requested table.
     * 
     * @return The status of the requested table.
     */
    public String getTableStatus() {
        return this.tableStatus;
    }

    /**
     * Get the size of this table.
     * 
     * @return The size of this table, the unit is Byte.
     */
    public Long getTableSizeInBytes() {
        return this.tableSizeInBytes;
    }

    /**
     * Get Key information for this table.
     * 
     * @return Key schema for this table.
     * @see KeySchemaElement
     */
    public List<KeySchemaElement> getKeySchema() {
        return this.keySchema;
    }

    /**
     * Set Key information for this table.
     * 
     * @param schema Key schema for this table.
     * @see KeySchemaElement
     */
    public void setKeySchema(List<KeySchemaElement> schema) {
        this.keySchema = schema;
    }

    /**
     * Get the number of Items in this table.
     * 
     * @return The number of Items in this table, the value is in long type.
     */
    public Long getItemCount() {
        return this.itemCount;
    }

    /**
     * Set the number of Items in this table.
     * 
     * @param count The number of Items in this table, the value is in long type.
     */
    public void setItemCount(Long count) {
        this.itemCount = count;
    }

    /**
     * Get the table creation time.
     * 
     * @return The table creation time, the type is in Data type.
     */
    public Date getCreationDateTime() {
        return this.creationDateTime;
    }

    /**
     * Set the table creation time.
     * 
     * @param time The table creation time
     */
    public void setCreationDateTime(Date time) {
        this.creationDateTime = time;
    }

    /**
     * Set the size of this table.
     * 
     * @param size The size of this table, the number type is in long type, and the unit for it is Byte.
     */
    public void setTableSizeInBytes(Long size) {
        this.tableSizeInBytes = size;
    }
    
    /**
     * Return a string representation of get table response information.
     *
     * @return A string representation of get table response information.
     */
    public String toString() {
        String str = "{";
        str += "TableName:\"" + this.tableName + "\",";
        str += "provisionedThroughput:" + JsonUtils.toJsonString(this.provisionedThroughput) + ",";
        str += "KeySchema:" + JsonUtils.toJsonString(this.keySchemaToJson(this.keySchema)) + ",";
        str += "tableStatus:\"" + this.tableStatus + "\",";
        str += "}";
        return str;

    }

    private List<Object> keySchemaToJson(List<KeySchemaElement> schema) {
        List<Object> obj = new ArrayList<Object>();
        for (KeySchemaElement element : schema) {
            obj.add(element.toJsonObj());
        }
        return obj;
    }
}
