package com.baidubce.services.moladb.model;

import java.util.Date;

/**
 * Represents the provision throughput numbers for a specified table.
 */
public class ProvisionedThroughputDescription {
    private Long readCapacityUnits = 0L;
    private Long writeCapacityUnits = 0L;
    private Date lastDescreaseDateTime;
    private Date lastIncreaseDateTime;
    private Integer numberOfDecreasesToday = 0;

    /**
     * Constructs a new ProvisionedThroughputDescription object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     */
    public ProvisionedThroughputDescription() {
    }

    /**
     * Get the time of last decreasing provision throughput setting operation.
     *
     * @return The time of last decreasing provision throughput setting operation, the type is in Date type.
     */
    public Date getLastDescreaseDateTime() {
        return this.lastDescreaseDateTime;
    }

    /**
     * Set the time of last decreasing provision throughput setting operation.
     *
     * @param time The time of last decreasing provision throughput setting operation, the type is Date.
     */
    public void setLastDescreaseDateTime(Date time) {
        this.lastDescreaseDateTime = time;
    }

    /**
     * Get the time of last increasing provision throughput setting operation.
     *
     * @return The time of last increasing provision throughput setting operation, the type is in Date type.
     */
    public Date getLastIncreaseDateTime() {
        return this.lastIncreaseDateTime;
    }

    /**
     * Set the time of last increasing provision throughput setting operation.
     *
     * @param time The time of last increasing provision throughput setting operation, the type is Date.
     */
    public void setLastIncreaseDateTime(Date time) {
        this.lastIncreaseDateTime = time;
    }

    /**
     * Get the number of decreasing provision throughput setting operation today.
     *
     * @return The number of decreasing provision throughput setting operation today.
     */
    public Integer getNumberOfDecreasesToday() {
        return this.numberOfDecreasesToday;
    }

    /**
     * Set the number of decreasing provision throughput setting operation today.
     *
     * @param count The number of decreasing provision throughput setting operation today.
     */
    public void setNumberOfDecreasesToday(Integer count) {
        this.numberOfDecreasesToday = count;
    }

    /**
     * Get the number of read capacity for this table.
     *
     * @return The number of read capacity for this table, the type is in long type.
     */
    public long getReadCapacityUnits() {
        return this.readCapacityUnits;
    }

    /**
     * Get the number of write capacity for this table.
     *
     * @return The number of write capacity for this table, the type is in long type.
     */
    public long getWriteCapacityUnits() {
        return this.writeCapacityUnits;
    }


    /**
     * Set the number of read capacity for this table.
     *
     * @param capacity The number of read capacity for this table, the type is in long type.
     * @throws IllegalArgumentException
     *          if the number of write capacity is not great than 0.
     */
    public void setReadCapacityUnits(Long capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("read capacity:"
                                               + capacity.toString()
                                               + " must great than 0");
        }
        readCapacityUnits = capacity;
    }


    /**
     * Set the number of write capacity for this table.
     *
     * @param capacity The number of write capacity for this table, the type is in long type.
     * @throws IllegalArgumentException
     *          if the number of write capacity is not great than 0.
     */
    public void setWriteCapacityUnits(Long capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("write capacity:"
                                               + capacity.toString()
                                               + " must great than 0");
        }
        writeCapacityUnits = capacity;
    }
}