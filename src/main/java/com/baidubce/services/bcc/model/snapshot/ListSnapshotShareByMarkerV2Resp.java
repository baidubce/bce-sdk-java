package com.baidubce.services.bcc.model.snapshot;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

public class ListSnapshotShareByMarkerV2Resp extends AbstractBceResponse {

    /**
     * true表示后面还有数据，false表示已经是最后一页
     */
    private Boolean isTruncated;

    /**
     * 标记查询的起始位置
     */
    private String marker;

    /**
     * 每页包含的最大数量
     */
    private Integer maxKeys;

    /**
     * 获取下一页所需要传递的marker值。当isTruncated为false时，该域不出现
     */
    private String nextMarker;

    /**
     * 共享快照列表
     */
    private List<SnapshotShareUO> result;

    @Data
    static class SnapshotShareUO {

        /**
         * 源快照ID
         */
        private String sourceSnapshotId;

        /**
         * 源快照uuid
         */
        private String sourceSnapshotUuid;

        /**
         * 共享快照ID
         */
        private String snapshotId;

        /**
         * 共享方用户ID
         */
        private String sourceAccountId;

        /**
         * 接收方用户ID
         */
        private String accountId;

        /**
         * 快照类型
         */
        private String snapshotType;

        /**
         * 共享快照名称
         */
        private String name;

        /**
         * 快照大小
         */
        private Long sizeInGB;

        /**
         * 快照共享时间
         */
        private LocalDateTime shareTime;

        /**
         * 共享快照描述
         */
        private String desc;

        /**
         * 共享状态
         * UnShare -- 未被共享
         * Sharing -- 共享中
         */
        private String shareStatus;

        /**
         * 加密的密钥对
         */
        private String encryptKey;

        /**
         * true -- 已删除
         * false -- 未删除
         * 源快照是否已被删除
         */
        private Boolean isSourceDeleted;
    }

}
