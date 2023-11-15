/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.bvw.model.matlib;

import java.util.Date;

import com.baidubce.model.AbstractBceResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class MatlibTaskGetResponse extends AbstractBceResponse {

    private Long id;

    private String resMaterialId;

    private String userId;

    private String title;

    private String status;

    private String errorMessage;

    private String coverUrl ;

    private Date lastUpdateTime;

}
