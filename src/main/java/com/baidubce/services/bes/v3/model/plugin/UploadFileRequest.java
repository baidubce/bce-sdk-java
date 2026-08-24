package com.baidubce.services.bes.v3.model.plugin;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

import java.io.File;

@Data
public class UploadFileRequest extends AbstractBesV3Request {
    private String clusterId;
    private File file;
    private String type;
    private String subtype;
}
