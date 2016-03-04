
package com.baidubce.services.moladb.model;

import java.util.HashMap;
import java.util.Map;
import com.baidubce.services.moladb.MolaDbConstants;
import com.baidubce.util.JsonUtils;

/**
 * Represents the provision throughput numbers for a specified table.
 */
public class ProvisionedThroughput {
    private Long readCapacityUnits = 0L;
    private Long writeCapacityUnits = 0L;

    /**
     * Constructs a new ProvisionThroughput object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public ProvisionedThroughput() {
    }

    /**
     * Get the number of read capacity for this table.
     *
     * @return The number of read capacity for this table, the type is in long type.
     */
    public Long getReadCapacityUnits() {
        return this.readCapacityUnits;
    }
    
    /**
     * Set the number of read capacity for this table.
     *
     * @param capacity The number of read capacity for this table, the type is in long type.
     * @return A reference to this object so that method calls can be chained together.
     */
    public ProvisionedThroughput withReadCapacityUnits(Long capacity) {
        this.setReadCapacityUnits(capacity);
        return this;
    }

    /**
     * Set the number of read capacity for this table.
     *
     * @param capacity The number of read capacity for this table, the type is in long type.
     */
    public void setReadCapacityUnits(Long capacity) {
        readCapacityUnits = capacity;
    }

    /**
     * Set the number of write capacity for this table.
     *
     * @param capacity The number of write capacity for this table, the type is in long type.
     * @return A reference to this object so that method calls can be chained together.
     */
    public ProvisionedThroughput withWriteCapacityUnits(Long capacity) {
        this.setWriteCapacityUnits(capacity);
        return this;
    }
    
    /**
     * Set the number of write capacity for this table.
     *
     * @param capacity The number of write capacity for this table, the type is in long type.
     */
    public void setWriteCapacityUnits(Long capacity) {
        writeCapacityUnits = capacity;
    }
    
    /**
     * Get the number of write capacity for this table.
     *
     * @return The number of write capacity for this table, the type is in long type.
     */
    public Long getWriteCapacityUnits() {
        return this.writeCapacityUnits;
    }
    
    /**
     * Return a string representation of the object.
     *
     * @return A string representation of the object.
     */
    public String toString() {
        return JsonUtils.toJsonString(this.toJsonObj());
    }
    
    protected Object toJsonObj() {
        Map<String, Long> result = new HashMap<String, Long>();
        if (this.readCapacityUnits > 0) {
            result.put(MolaDbConstants.JSON_READ_CAPACITY_UNITS, readCapacityUnits);
        }
        if (this.writeCapacityUnits > 0) {
            result.put(MolaDbConstants.JSON_WRITE_CAPACITY_UNITS, writeCapacityUnits);
        }
        return result;
    }
}