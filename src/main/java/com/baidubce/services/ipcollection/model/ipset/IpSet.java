package com.baidubce.services.ipcollection.model.ipset;

import com.baidubce.services.ipcollection.model.TemplateIpAddressInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * ip set
 */
@Getter
@Setter
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class IpSet {
    private String ipSetId;
    private String name;
    private String description;
    private String ipVersion;
    private List<TemplateIpAddressInfo> ipAddressInfo;
    private Integer bindedInstanceNum;
}
