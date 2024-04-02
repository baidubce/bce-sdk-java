package com.baidubce.services.bcm.model.application;

import com.baidubce.model.AbstractBceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationInstanceListResponse extends AbstractBceResponse {

    private List<ApplicationInstanceInfo> content;

    private Boolean first;

    private Boolean last;

    private Integer pageElements;

    private Integer pageNumber;

    private Integer pageSize;

    private Integer totalElements;

    private Integer totalPages;

    private String[] orderBy;

    private String[] fields;

    private String query;
}
