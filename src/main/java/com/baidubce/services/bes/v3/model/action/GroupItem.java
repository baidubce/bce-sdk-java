package com.baidubce.services.bes.v3.model.action;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupItem {
    private String groupName;
    private String groupNameCN;
    private String state;
    private Boolean analysis;
    private String diagnosis;
    private String diagnosisCN;
}
