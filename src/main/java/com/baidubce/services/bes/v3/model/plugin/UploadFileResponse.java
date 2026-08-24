package com.baidubce.services.bes.v3.model.plugin;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class UploadFileResponse extends AbstractBceResponse {
    private String url;
    private Long size;
}
