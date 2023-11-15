package com.baidubce.services.kafka.model.acl;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class DeleteAclResponse extends AbstractBceResponse {

    private String username;
}
