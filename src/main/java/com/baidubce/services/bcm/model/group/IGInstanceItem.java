package com.baidubce.services.bcm.model.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by dongjiawei on 2023/12/12.
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IGInstanceItem {
    private String itemName;
    private String itemAlias;
    private String itemValue;
    private boolean itemIdentitable;
    private String itemDimension;
}