package com.baidubce.services.bos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GetBucketInventoryResponse extends BosResponse {
    private String id;
    private String status;
    private List<String> resource;
    private String schedule;
    private int monthlyDate;
    private String includedObjectVersions;
    private Inventory.Destination destination;
}
