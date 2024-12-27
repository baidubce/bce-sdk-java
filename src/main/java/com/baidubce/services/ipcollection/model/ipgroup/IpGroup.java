package com.baidubce.services.ipcollection.model.ipgroup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * ip group
 */
@Getter
@Setter
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class IpGroup {
    private String ipGroupId;
    private String name;
    private String description;
    private String ipVersion;
    private List<String> ipSetIds;
    private Integer bindedInstanceNum;
}
