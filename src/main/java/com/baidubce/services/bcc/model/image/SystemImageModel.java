package com.baidubce.services.bcc.model.image;

import lombok.Data;

/**
 * @Author: lilu24
 * @Date: 2023-09-04
 */

@Data
public class SystemImageModel {

    private String imageId;
    private String imageName;
    private String osType;
    private String osVersion;
    private String osArch;
    private String osName;
    private String osLang;
    private int minSizeInGiB;
}
