package com.baidubce.services.bcm.model.siteonce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiteOnceAgent {
    private boolean whiteUser;
    private Set<SiteAgent> siteAgents;
}
