package com.baidubce.services.bcc.model.instance;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EhcCluster {
    /**
     * The id of ehcCluster.
     */
    private String ehcClusterId;
    /**
     * The name of the ehcCluster.
     */
    private String name;
    /**
     * The description of the instance.
     */
    private String description;
    /**
     * the name of available zone
     */
    private String zoneName;
    /**
     * The time when the instance was created
     */
    private Date createdTime;
    /**
     * List of id of instances created.
     */
    private List<String> instanceIds;
    /**
     * List of id of reserved created.
     */
    private List<String> reservedInstanceIds;
}
