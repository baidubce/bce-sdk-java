package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.JsonObject;

import java.util.List;

/**
 * The origin pool detail returned by the query interface.
 */
public class OriginPoolInfo extends JsonObject {
    /**
     * The index id.
     */
    private Integer id;

    /**
     * The user id.
     */
    private String uid;

    /**
     * The site name.
     */
    private String site;

    /**
     * The origin pool name.
     */
    private String name;

    /**
     * The origin pool id.
     */
    private String originPoolId;

    /**
     * The origin pool status. "ON" means in use, "OFF" means not used.
     */
    private String status;

    /**
     * The origin count.
     */
    private Integer count;

    /**
     * The origin configurations.
     */
    private List<OriginConfig> originConfig;

    /**
     * The reference instances of the origin pool.
     */
    private List<Instance> instance;

    /**
     * The extension field.
     */
    private List<Object> slb;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return site
     */
    public String getSite() {
        return site;
    }

    /**
     * @param site
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return originPoolId
     */
    public String getOriginPoolId() {
        return originPoolId;
    }

    /**
     * @param originPoolId
     */
    public void setOriginPoolId(String originPoolId) {
        this.originPoolId = originPoolId;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return originConfig
     */
    public List<OriginConfig> getOriginConfig() {
        return originConfig;
    }

    /**
     * @param originConfig
     */
    public void setOriginConfig(List<OriginConfig> originConfig) {
        this.originConfig = originConfig;
    }

    /**
     * @return instance
     */
    public List<Instance> getInstance() {
        return instance;
    }

    /**
     * @param instance
     */
    public void setInstance(List<Instance> instance) {
        this.instance = instance;
    }

    /**
     * @return slb
     */
    public List<Object> getSlb() {
        return slb;
    }

    /**
     * @param slb
     */
    public void setSlb(List<Object> slb) {
        this.slb = slb;
    }
}
