package com.baidubce.services.bcm.model.action;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionNotification {
    private String receiver;
    private String type;
}