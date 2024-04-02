package com.baidubce.services.bcm.model.application;

import com.baidubce.model.AbstractBceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationInfoUpdateResponse extends AbstractBceResponse {

    private Long id;

    private String name;

    private String description;

    private String type;

    private String alias;

    private String userId;
}
