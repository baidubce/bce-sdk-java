package com.baidubce.services.eiptp.model;

import com.baidubce.services.eip.model.ListResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The request for listing eiptp.
 */
@Setter
@Getter
public class ListEipTpsResponse extends ListResponse {

    private List<ListEipTpsResponse.Package> packageList;

    @Setter
    @Getter
    public static class Package {
        /**
         * The id of the eiptp.
         */
        private String id;

        /**
         * The deduct policy of the eiptp including "FullTimeDurationPackage" and "TimeDurationPackage".
         */
        private String deductPolicy;

        /**
         * The package type of the eiptp.
         */
        private String packageType;

        /**
         * The status of the eiptp including "RUNNING", "EXPIRED" and "USED_UP".
         */
        private String status;

        /**
         * The capacity of the eiptp.
         */
        private String capacity;

        /**
         * The capacity of the eiptp already used by the user.
         */
        private String usedCapacity;

        /**
         * The eipTp createTime (GMT+8 format).
         */
        private String createTime;

        /**
         * The active time of the eipTp (GMT+8 format).
         */
        private String activeTime;

        /**
         * The expire time of the eipTp (GMT+8 format).
         */
        private String expireTime;
    }
}
