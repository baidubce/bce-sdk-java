package com.baidubce.services.bos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Specifies a BucketMirroringConfiguration, use to setting bos bucket mirroring.
 * (https://cloud.baidu.com/doc/BOS/s/Ylha9sbks)
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BucketMirroringConfiguration {
    private String mode;
    private String sourceUrl;
    private String backSourceUrl;
    private String resource;
    private String prefix;
    private String suffix;
    private String fixedKey;
    private String version;
    private String prefixReplace;
    private String passQueryString;
    private List<CustomHeader> customHeaders;
    private String storageClass;
    private String allHeader;
    private List<String> ignoreHeaders;
    private List<String> passHeaders;
}
