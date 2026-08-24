package com.baidubce.services.bes.v3.model.action;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KindItem {
    private String name;
    private String nameCN;
    private String state;
}
