/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.bvw.model.matlib;

import com.baidubce.model.AbstractBceResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Setter
@Getter
public class MatlibTaskResponse extends AbstractBceResponse {

    private Long id;
    private Error error;
}
