package com.baidubce.services.oos.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KVModel {
    private String key;
    private Object value;
}
