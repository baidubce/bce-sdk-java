package com.baidubce.services.bcm.model.alarmhouse;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmListRequest extends AbstractBceRequest {
    private String userId;
    private String alarmType;

    private Integer pageNo;
    private Integer pageSize;

    private Long startTime;
    private Long endTime;

    private String scope;
    private String resourceType;
    private List<String> states;
    private String sort = "startTime";
    private Boolean ascending = false;
    private String level;
    private String region;
    private String alarmAliasName;
    private ResourceKV resource;
    private List<ResourceKV> resources;

    /**
     * {@inheritDoc}
     *
     * 重写父类的方法，实现对请求凭证的设置。
     *
     * @param credentials BCE凭证信息
     * @return 当前对象自身，以便链式调用
     */
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
