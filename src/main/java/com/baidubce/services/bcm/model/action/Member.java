package com.baidubce.services.bcm.model.action;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private String type;
    private String id;
    private String name = "";
}