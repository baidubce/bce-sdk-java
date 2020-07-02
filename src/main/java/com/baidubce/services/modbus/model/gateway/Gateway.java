package com.baidubce.services.modbus.model.gateway;

/**
 * Created by yuanyoujun on 2017/7/31.
 */
public interface Gateway {
    public String getUuid();

    public void setUuid(String uuid);

    public boolean isUseSsl();

    public void setUseSsl(boolean useSsl);

    public String getUsername();

    public void setUsername(String username);

    public String getPassword();

    public void setPassword(String password);

    public String getHost();

    public void setHost(String host);

    public String getCommandTopic();

    public void setCommandTopic(String commandTopic);

    public String getState();

    public void setState(String state);

    public String getDescription();

    public void setDescription(String description);

    public String getCode();

    public void setCode(String code);

    public String getCreateTime();

    public void setCreateTime(String createTime);

    public String getUpdateTime();

    public void setUpdateTime(String updateTime);

    public int getDeviceNum();

    public void setDeviceNum(int deviceNum);
}
