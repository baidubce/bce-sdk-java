/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.bvw.model.matlib;

import static com.baidubce.services.bvw.constants.MatlibConstants.DATE_MINUTE_FORMAT;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bvw.model.matlib.timeline.Timeline;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The request of create videoedit's draft.
 */
@Setter
@Getter
@NoArgsConstructor
public class MatlibTaskRequest extends AbstractBceRequest {
    private String title =  "新建作品-" + DateFormatUtils.format(System.currentTimeMillis(), DATE_MINUTE_FORMAT);

    private String ratio = "16:9";

    private String duration = "0";

    private Timeline timeline = new Timeline();

    private List<MaterialGetResponse> resourceList = Collections.emptyList();

    private String coverBucket;

    private String coverKey;

    private Date lastUpdateTime;



    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
