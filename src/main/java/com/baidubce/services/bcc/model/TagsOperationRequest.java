package com.baidubce.services.bcc.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import java.util.List;
import lombok.Data;

@Data
public class TagsOperationRequest extends AbstractBceRequest {
    /**
     * 操作资源类型：bcc/cds/image/snapshot/snapshotchain/bccri
     */
    private String resourceType;

    /**
     * 资源ID列表
     */

    private List<String> resourceIds;


    /**
     * 待绑定标签列表
     */
    private List<TagModel> tags;
    /**
     * 关联资源绑定标签
     */
    private Boolean isRelationTag = false;
    @Override
    public TagsOperationRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
