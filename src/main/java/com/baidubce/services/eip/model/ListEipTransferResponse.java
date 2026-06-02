
package com.baidubce.services.eip.model;

import com.baidubce.common.BaseBceResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListEipTransferResponse extends BaseBceResponse  {
    /**
     * 迁移资源列表。
     */
    private List<TransferEipVo> transferList;

    /**
     * 标记查询的起始位置，若结果列表为空，此项不存在
     */
    private String marker;

    /**
     * true表示后面还有数据，false表示已经是最后一页
     */
    private Boolean isTruncated;

    /**
     * 获取下一页所需要传递的marker值。当isTruncated为false时，该域不出现
     */
    private String nextMarker;

    /**
     * 每页包含的最大数量
     */
    private Integer maxKeys;

    public ListEipTransferResponse setTransferList(List<TransferEipVo> transferList) {
        this.transferList = transferList;
        return this;
    }

    public List<TransferEipVo> getTransferList() {
        return this.transferList;
    }

    public ListEipTransferResponse setMarker(String marker) {
        this.marker = marker;
        return this;
    }

    public String getMarker() {
        return this.marker;
    }

    public ListEipTransferResponse setIsTruncated(Boolean isTruncated) {
        this.isTruncated = isTruncated;
        return this;
    }

    public Boolean getIsTruncated() {
        return this.isTruncated;
    }

    public ListEipTransferResponse setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
        return this;
    }

    public String getNextMarker() {
        return this.nextMarker;
    }

    public ListEipTransferResponse setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
        return this;
    }

    public Integer getMaxKeys() {
        return this.maxKeys;
    }

    @Override
    public String toString() {
        return "ListEipTransferResponse{"
                + "transferList=" + transferList + "\n"
                + "marker=" + marker + "\n"
                + "isTruncated=" + isTruncated + "\n"
                + "nextMarker=" + nextMarker + "\n"
                + "maxKeys=" + maxKeys + "\n"
                + "}";
    }




}