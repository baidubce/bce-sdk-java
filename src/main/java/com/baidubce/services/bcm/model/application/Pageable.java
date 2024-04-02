package com.baidubce.services.bcm.model.application;

import lombok.Data;

/**
 * @author gongjiaming
 */
@Data
public class Pageable {
        private Sort sort;
        private Integer pageSize;

        private Integer pageNo;

        private Integer offset;

        private Boolean unpaged;

        private Boolean paged;
}
