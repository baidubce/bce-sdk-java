package com.baidubce.services.bcm.model.application;

import com.baidubce.model.AbstractBceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author gongjiaming
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDataListResponse extends AbstractBceResponse {
    private List<ApplicationInfoResponse> content;

    private Boolean last;

    private Integer totalElements;

    private Integer totalPages;

    private Boolean first;

    private Integer number;

    private Integer size;

    private Integer numberOfElements;

    private Sort sort;

    private Pageable pageable;


}
