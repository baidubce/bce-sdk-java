package com.baidubce.services.kafka.model.acl;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

@Data
public class ListAclResponse extends AbstractBceResponse {

    private List<Acl> acls;
}
