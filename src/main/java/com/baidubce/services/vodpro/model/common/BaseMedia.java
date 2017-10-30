package com.baidubce.services.vodpro.model.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * Created on 31/05/2017 1:37 PM
 *
 * @author hejianbin
 */
public class BaseMedia {
    private Path path;

    @JsonIgnore
    private Long spaceId;

    @JsonIgnore
    private Long projectId;

    @JsonIgnore
    private Long mediaId;

    @JsonIgnore
    private Long userId;

    @UtcTime
    private Date createTime;
}
