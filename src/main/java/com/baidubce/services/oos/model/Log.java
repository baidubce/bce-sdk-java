package com.baidubce.services.oos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    private String timestamp;
    private String level;
    private String msg;
    private Map<String, String> tags;
}
