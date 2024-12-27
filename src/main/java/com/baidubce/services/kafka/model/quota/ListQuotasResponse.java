package com.baidubce.services.kafka.model.quota;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

@Data
public class ListQuotasResponse extends AbstractBceResponse {

    private List<Quota> quotas;
}
