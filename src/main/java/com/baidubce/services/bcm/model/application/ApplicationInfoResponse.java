package com.baidubce.services.bcm.model.application;

import com.baidubce.model.AbstractBceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gongjiaming
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationInfoResponse extends AbstractBceResponse {
    private Long id;

    private String name;

    private String description;

    private String type;

    private String userId;

    private String alias;
}
